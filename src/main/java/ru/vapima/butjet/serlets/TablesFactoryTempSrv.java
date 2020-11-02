package ru.vapima.butjet.serlets;

import ru.vapima.butjet.dao.DaoFactory;
import ru.vapima.butjet.dao.impl.BdCreatorJDBC;

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
        BdCreatorJDBC bdCreatorJDBC = new DaoFactory().createDB();
        try { bdCreatorJDBC.creatPersons();} catch (SQLException e) { log.info(e.toString()); }
        try { bdCreatorJDBC.creatAccs();} catch (SQLException e) { log.info(e.toString()); }
        try { bdCreatorJDBC.creatPlans();} catch (SQLException e) { log.info(e.toString()); }
        try { bdCreatorJDBC.creatAccRecs();} catch (SQLException e) { log.info(e.toString()); }

    }
}
