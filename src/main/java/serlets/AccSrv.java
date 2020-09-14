package serlets;

import dao.AccDAO;
import dao.DaoFactory;
import exeptions.AccExeption;
import exeptions.PersonExeption;
import exeptions.PlanExeption;
import model.Acc;
import model.Person;
import org.json.JSONException;
import org.json.JSONObject;
import service.JSONService;
import service.PersonFactory;
import service.SerialFactory;
import service.TokenService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@WebServlet("/acc/*")
public class AccSrv extends HttpServlet {
    AccDAO accDAO = DaoFactory.createAccDao();
    Logger log = Logger.getLogger(AccSrv.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String token = req.getParameter("token");
            Person person = new PersonFactory().getPerson(new TokenService().decode(token));
            Integer idAcc = Integer.parseInt(req.getPathInfo().replace("/", ""));
            Acc acc = accDAO.takeById(idAcc);
            if (!acc.getPersonId().equals(person.getId())) {
                throw new PersonExeption("This is not your account. Bad ass.");
            }
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            PrintWriter writer = resp.getWriter();
            writer.println(SerialFactory.createJsonSer().go(acc));
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String token = req.getParameter("token");
            Person person = new PersonFactory().getPerson(new TokenService().decode(token));
            JSONObject body = JSONService.getBody(req);
            String name = body.getString("name");
            Integer balance = body.getInt("balance");
            Boolean isActive = body.getBoolean("isActive");
            Acc acc = new Acc(0, name, balance, LocalDateTime.now(), isActive, person.getId());
            Integer i = accDAO.insert(acc);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            PrintWriter writer = resp.getWriter();
            writer.println(SerialFactory.createJsonSer().go(accDAO.takeById(i)));
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String token = req.getParameter("token");
            Person person = new PersonFactory().getPerson(new TokenService().decode(token));
            Integer idAcc = Integer.parseInt(req.getPathInfo().replace("/", ""));
            JSONObject body = JSONService.getBody(req);
            String name = body.getString("name");
            Integer balance = body.getInt("balance");
            Boolean isActive = body.getBoolean("isActive");
            Acc acc = accDAO.takeById(idAcc);
            if (!acc.getPersonId().equals(person.getId())) {
                throw new PersonExeption("This is not your account. Bad ass.");
            }
            acc.setName(name);
            acc.setBalance(balance);
            acc.setActive(isActive);
            Integer i = accDAO.update(acc);
            if (i != 1) {
                throw new AccExeption("So many fields you are updated. Danger.");
            }
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            PrintWriter writer = resp.getWriter();
            writer.println(SerialFactory.createJsonSer().go(accDAO.takeById(acc.getId())));
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String token = req.getParameter("token");
            Person person = new PersonFactory().getPerson(new TokenService().decode(token));
            Integer idAcc = Integer.parseInt(req.getPathInfo().replace("/", ""));
            Acc acc = accDAO.takeById(idAcc);
            if (acc == null) {
                throw new PlanExeption("Account is noy found.");
            }
            if (!acc.getPersonId().equals(person.getId())) {
                throw new PersonExeption("This is not your account. Bad ass.");
            }
            Integer i = accDAO.delete(acc);
            if (i != 1) {
                throw new AccExeption("So many fields you are deleted. Danger.");
            }
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            PrintWriter writer = resp.getWriter();
            writer.println("{ \"status\":\"deleted\" }");
        } catch (JSONException | AccExeption e) {
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
