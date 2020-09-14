package serlets;

import dao.DaoFactory;
import dao.PlanDAO;
import exeptions.PersonExeption;
import exeptions.PlanExeption;
import model.Person;
import model.Plan;
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
import java.time.LocalDate;
import java.util.logging.Logger;

@WebServlet("/plan/*")
public class PlanSrv extends HttpServlet {
    PlanDAO planDAO = DaoFactory.createPlanDao();
    Logger log = Logger.getLogger(PlanSrv.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer idPlan = Integer.parseInt(req.getPathInfo().replace("/", ""));
            String token = req.getParameter("token");
            Person person = new PersonFactory().getPerson(new TokenService().decode(token));
            Plan plan = planDAO.takeById(idPlan);
            if (!plan.getIdPerson().equals(person.getId())) {
                throw new PersonExeption("This is not your plan. Bad ass.");
            }
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            PrintWriter writer = resp.getWriter();
            writer.println(SerialFactory.createJsonSer().go(plan));
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String token = req.getParameter("token");
            Person person = new PersonFactory().getPerson(new TokenService().decode(token));
            JSONObject body = JSONService.getBody(req);
            String name = body.getString("name");
            Integer balance = body.getInt("balance");
            log.info(LocalDate.now().toString());
            LocalDate localDate = LocalDate.parse(body.getString("date"));
            Plan plan = new Plan(0, name, balance, localDate, person.getId()); //NEED TEST PARSE TO LOCALDATE
            Integer i = planDAO.insert(plan);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            PrintWriter writer = resp.getWriter();
            writer.println(SerialFactory.createJsonSer().go(planDAO.takeById(i)));
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
            Integer idPlan = Integer.parseInt(req.getPathInfo().replace("/", ""));
            JSONObject body = JSONService.getBody(req);
            String name = body.getString("name");
            Integer balance = body.getInt("balance");
            LocalDate localDate = LocalDate.parse(body.getString("date"));
            Plan plan = planDAO.takeById(idPlan);
            if (!plan.getIdPerson().equals(person.getId())) {
                throw new PersonExeption("This is not your account. Bad ass.");
            }
            plan.setName(name);
            plan.setBalance(balance);
            plan.setDateExpiration(localDate);
            Integer i = planDAO.update(plan);
            if (i != 1) {
                throw new PlanExeption("So many fields you are updated. Danger.");
            }
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            PrintWriter writer = resp.getWriter();
            writer.println(SerialFactory.createJsonSer().go(planDAO.takeById(plan.getId())));
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
            Integer idPlan = Integer.parseInt(req.getPathInfo().replace("/", ""));
            Plan plan = planDAO.takeById(idPlan);
            if (plan == null) {
                throw new PlanExeption("Plan is not found.");
            }
            if (!plan.getIdPerson().equals(person.getId())) {
                throw new PersonExeption("This is not your account. Bad ass.");
            }
            Integer i = planDAO.delete(plan);
            if (i != 1) {
                throw new PlanExeption("So many fields you are deleted. Danger.");
            }
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            PrintWriter writer = resp.getWriter();
            writer.println("{ \"status\":\"deleted\" }");
        } catch (JSONException | PlanExeption e) {
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
