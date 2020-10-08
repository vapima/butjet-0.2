package ru.vapima.butjet.serlets;

import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.answers.acc.GetRdn;
import ru.vapima.butjet.answers.person.*;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.service.Responser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;


@WebServlet("/person/*")
public class PersonSrv extends HttpServlet {
    Logger log = Logger.getLogger(PersonSrv.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Config config = new Config(req, resp);
        if (req.getPathInfo().replace("/", "").equals("token")) {
            Answer answer = () -> "{}";
            try {
                config.getResp().setHeader("token", new GetTokenPerson(config).run());
            } catch (PersonExeption | AccExeption | SQLException | PlanExeption personExeption) {
                log.info("Fail givin token.");
            }
            new Responser(answer, config).doResponse();
        } else    if (req.getPathInfo().replace("/", "").equals("rdn")) {
           Answer answer=new GetRdn(config);
            new Responser(answer, config).doResponse();
        } else {
            new Responser(new GetPerson(config), config).doResponse();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Config config = new Config(req, resp);
        Answer answer = new PostPerson(config);
        new Responser(answer, config).doResponse();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        Config config = new Config(req, resp);
        Answer answer = new PutPerson(config);
        new Responser(answer, config).doResponse();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Config config = new Config(req, resp);
        Answer answer = new DeletePerson(config);
        new Responser(answer, config).doResponse();
    }
}
