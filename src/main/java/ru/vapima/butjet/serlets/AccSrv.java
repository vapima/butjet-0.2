package ru.vapima.butjet.serlets;


import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.answers.Core;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.service.Responser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/acc/*")
public class AccSrv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        Config config = new Config(req, resp);
        Answer answer=()->"Input error. possible /all/ or /id";
        if (config.getReq().getPathInfo().startsWith("/all/")) { //contains("/all/")
            answer=()->new Core<Acc>(config).getList(config.getDaoFactory().createAccDao());
        } else {
            req.setAttribute("id",req.getPathInfo().replace("/", ""));
            answer=()->new Core<Acc>(config).getEntity(config.getDaoFactory().createAccDao());
        }
        new Responser(answer, config).doResponse();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Config config = new Config(req, resp);
        Answer answer=()-> new Core<Acc>(config).postEntity(config.getDaoFactory().createAccDao(),Acc.class);
        new Responser(answer, config).doResponse();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        Config config = new Config(req, resp);
        req.setAttribute("id",req.getPathInfo().replace("/", ""));
        Answer answer=()-> new Core<Acc>(config).updateEntity(config.getDaoFactory().createAccDao(),Acc.class);
        new Responser(answer, config).doResponse();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Config config = new Config(req, resp);
        req.setAttribute("id",req.getPathInfo().replace("/", ""));
        Answer answer=()->new Core<Acc>(config).deleteEntity(config.getDaoFactory().createAccDao());
        new Responser(answer, config).doResponse();
    }
}
