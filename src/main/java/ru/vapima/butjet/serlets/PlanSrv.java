package ru.vapima.butjet.serlets;


import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.answers.Core;
import ru.vapima.butjet.model.Plan;
import ru.vapima.butjet.service.Responser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/plan/*")
public class PlanSrv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        Config config = new Config(req, resp);
        Answer answer=()->"Input error. possible /all/ or /id";
        if (config.getReq().getPathInfo().startsWith("/all/")) { //contains("/all/")
            answer=()->new Core<Plan>(config).getList(config.getDaoFactory().createPlanDao());
        } else {
            req.setAttribute("id",req.getPathInfo().replace("/", ""));
            answer=()->new Core<Plan>(config).getEntity(config.getDaoFactory().createPlanDao());
        }
        new Responser(answer, config).doResponse();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Config config = new Config(req, resp);
        //Answer answer = new PostPlan(config);
        Answer answer=()-> new Core<Plan>(config).postEntity(config.getDaoFactory().createPlanDao(),Plan.class);
        new Responser(answer, config).doResponse();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        Config config = new Config(req, resp);
        req.setAttribute("id",req.getPathInfo().replace("/", ""));
        Answer answer=()-> new Core<Plan>(config).updateEntity(config.getDaoFactory().createPlanDao(),Plan.class);
        new Responser(answer, config).doResponse();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Config config = new Config(req, resp);
        req.setAttribute("id",req.getPathInfo().replace("/", ""));
        Answer answer=()->new Core<Plan>(config).deleteEntity(config.getDaoFactory().createPlanDao());
        new Responser(answer, config).doResponse();
    }
}
