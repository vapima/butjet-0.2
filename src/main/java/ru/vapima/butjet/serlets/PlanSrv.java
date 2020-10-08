package ru.vapima.butjet.serlets;


import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.answers.plan.*;
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
        if (config.getReq().getPathInfo().startsWith("/all/")) { //contains("/all/")
            new Responser(new GetPlans(config), config).doResponse();
        } else {
            req.setAttribute("id",req.getPathInfo().replace("/", ""));
            new Responser(new GetPlanByID(config), config).doResponse();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Config config = new Config(req, resp);
        Answer answer = new PostPlan(config);
        new Responser(answer, config).doResponse();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        Config config = new Config(req, resp);
        req.setAttribute("id",req.getPathInfo().replace("/", ""));
        Answer answer = new PutPlanByID(config);
        new Responser(answer, config).doResponse();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Config config = new Config(req, resp);
        req.setAttribute("id",req.getPathInfo().replace("/", ""));
        Answer answer = new DeletePlanByID(config);
        new Responser(answer, config).doResponse();
    }
}
