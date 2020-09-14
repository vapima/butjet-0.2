package serlets;

import dao.DaoFactory;
import dao.PersonDAO;
import exeptions.AccExeption;
import exeptions.PersonExeption;
import exeptions.PlanExeption;
import model.Acc;
import model.Person;
import model.Plan;
import org.json.JSONException;
import org.json.JSONObject;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;


@WebServlet("/person/*")
public class PersonSrv extends HttpServlet {
    PersonDAO personDAO = DaoFactory.createPersonDao();
    Logger log = Logger.getLogger(PersonSrv.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            if (req.getPathInfo().replace("/", "").equals("token")) {
                JSONObject body = JSONService.getBody(req);
                String name = body.getString("name");
                String password = body.getString("password");
                Person person = new PersonFactory().getPerson(name);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
                PrintWriter writer = resp.getWriter();
                if (PasswordHash.verifyHash(password, person.getPassword())) {
                    writer.println(new JSONObject().put("token", new TokenService().create(person)).toString());
                } else throw new PersonExeption("Login or password invalid.");
            } else {
                String token = req.getParameter("token");
                Person person = new PersonFactory().getPerson(new TokenService().decode(token));
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.setStatus(200);
                PrintWriter writer = resp.getWriter();
                writer.println(SerialFactory.createJsonSer().go(person));
            }
        } catch (JSONException | PersonExeption e) {
            log.info(e.toString());
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(400);
            PrintWriter writer = resp.getWriter();
            writer.println(e.getMessage());
            throw new ServletException(e);

        } catch (Exception e) {
            log.info(e.toString());
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(500);
            PrintWriter writer = resp.getWriter();
            writer.println("Unknown error. " + e.toString());
            //throw new ServletException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            JSONObject body = JSONService.getBody(req);
            String name = body.getString("name");
            String password = body.getString("password");
            Person person = new PersonFactory().getNewPerson(name, password);
            Integer i = personDAO.insert(person);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            PrintWriter writer = resp.getWriter();
            writer.println(SerialFactory.createJsonSer().go(new PersonFactory().getPerson(i)));
        } catch (JSONException | PersonExeption e) {
            log.info(e.toString());
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(400);
            PrintWriter writer = resp.getWriter();
            writer.println(e.getMessage());
        } catch (Exception e) {
            log.info(e.toString());
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(500);
            PrintWriter writer = resp.getWriter();
            writer.println("Unknown error. " + e.toString());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String token = req.getParameter("token");
            Person person = new PersonFactory().getPerson(new TokenService().decode(token));
            JSONObject body = JSONService.getBody(req);
            String password = body.getString("password");
            person.setPassword(PasswordHash.getHash(password));
            Integer i = personDAO.update(person);
            if (i != 1) {
                throw new AccExeption("So many fields you are updated. Danger.");
            }
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            PrintWriter writer = resp.getWriter();
            writer.println(SerialFactory.createJsonSer().go(personDAO.takeById(person.getId())));
        } catch (JSONException | PersonExeption e) {
            log.info(e.toString());
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(400);
            PrintWriter writer = resp.getWriter();
            writer.println(e.getMessage());
        } catch (Exception e) {
            log.info(e.toString());
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(500);
            PrintWriter writer = resp.getWriter();
            writer.println("Unknown error. " + e.toString());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try { //не популярная операция )))
            String token = req.getParameter("token");
            Person person = new PersonFactory().getPerson(new TokenService().decode(token));
            if (person == null) {
                throw new PlanExeption("Person is noy found.");
            }
            for (Acc a : DaoFactory.createAccDao().takeAllByForeignKey(person.getId())) {
                DaoFactory.createAccDao().delete(a);
            }
            for (Plan p : person.getPlans()) {
                DaoFactory.createPlanDao().delete(p);
            }
            Integer i = personDAO.delete(person);
            if (i != 1) {
                throw new AccExeption("So many fields you are deleted. Danger.");
            }
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            PrintWriter writer = resp.getWriter();
            writer.println("{ \"status\":\"deleted\" }");
        } catch (JSONException | PersonExeption e) {
            log.info(e.toString());
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(400);
            PrintWriter writer = resp.getWriter();
            writer.println(e.getMessage());
        } catch (Exception e) {
            log.info(e.toString());
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(500);
            PrintWriter writer = resp.getWriter();
            writer.println("Unknown error. " + e.toString());
        }
    }
}
