package ru.vapima.butjet.serlets;

import org.json.JSONObject;
import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.answers.Core;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.AccRec;
import ru.vapima.butjet.service.Responser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/accrec/*")
public class AccRecSrv extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        Config config = new Config(req, resp);
        Answer answer=()->"Input error. possible /all/ or /id";
        if (config.getReq().getPathInfo().startsWith("/all/")) {
            answer=()->new Core<AccRec>(config).getList(config.getDaoFactory().createAccRecDao());
        } else {
            req.setAttribute("id",req.getPathInfo().replace("/", ""));
            answer=()->new Core<AccRec>(config).getEntity(config.getDaoFactory().createAccRecDao());
        }
        new Responser(answer, config).doResponse();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Config config = new Config(req, resp);
        Answer answer=()-> new Core<AccRec>(config).postEntity(config.getDaoFactory().createAccRecDao(),AccRec.class);
        Integer id=0;
        try {
            JSONObject jsonObject=new JSONObject(answer.run());
            id=jsonObject.getInt("accId");
            req.setAttribute("id",id);
        } catch (Exception personExeption) { System.out.println("EXEPTION"); } //TODO EXEPTION
        Answer answerAcc=()->new Core<Acc>(config).updateEntity(config.getDaoFactory().createAccDao(),Acc.class);
        new Responser(answerAcc, config).doResponse();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        Config config = new Config(req, resp);
        req.setAttribute("id",req.getPathInfo().replace("/", ""));
        Answer answer=()-> new Core<AccRec>(config).updateEntity(config.getDaoFactory().createAccRecDao(), AccRec.class);
        new Responser(answer, config).doResponse();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Config config = new Config(req, resp);
        req.setAttribute("id",req.getPathInfo().replace("/", ""));
        Answer answer=()->new Core<AccRec>(config).deleteEntity(config.getDaoFactory().createAccRecDao());
        new Responser(answer, config).doResponse();
    }
}
