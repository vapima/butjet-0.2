package serlets;

import exeptions.PersonExeption;
import model.Person;
import org.json.JSONException;
import service.MyRdnMonth;
import service.PersonFactory;
import service.TokenService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet("/person/rdn")
public class RdnSrv extends HttpServlet {
    Logger log = Logger.getLogger(RdnSrv.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String token = req.getParameter("token");
            Person person = new PersonFactory().getPerson(new TokenService().decode(token));
            MyRdnMonth myRdnMonth = new MyRdnMonth(person);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            PrintWriter writer = resp.getWriter();
            writer.println("{ \"rdn\":" + myRdnMonth.getRdn() + "}");
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
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

}
