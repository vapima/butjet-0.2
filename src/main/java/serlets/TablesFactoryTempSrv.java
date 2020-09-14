package serlets;

import dao.DaoFactory;
import dao.Impl.BdCreatorJDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet("/tf")
public class TablesFactoryTempSrv extends HttpServlet {
    Logger log = Logger.getLogger(TablesFactoryTempSrv.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BdCreatorJDBC bdCreatorJDBC = DaoFactory.createDB();
        try {
            bdCreatorJDBC.creatPersons();
            bdCreatorJDBC.creatAccs();
            bdCreatorJDBC.creatPlans();
        } catch (SQLException e) {
            log.info(e.toString());
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(500);
            PrintWriter writer = resp.getWriter();
            writer.println("Unknown error. " + e.toString());
        }
    }
}
