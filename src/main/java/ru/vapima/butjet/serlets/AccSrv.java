package ru.vapima.butjet.serlets;


import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.answers.acc.*;
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
        if (req.getPathInfo().startsWith("/all/")) {
            new Responser(new GetAccounts(config), config).doResponse();
        } else if (req.getPathInfo().startsWith("/point/all/")) {
            req.setAttribute("name",req.getPathInfo().replace("/point/all/", ""));
            new Responser(new GetAllPointsAccountByName(config),config).doResponse();
        }else if (req.getPathInfo().startsWith("/point/")) {
            req.setAttribute("id",req.getPathInfo().replace("/point/", ""));
            new Responser(new GetPointAccountByID(config),config).doResponse();
        }else if (req.getPathInfo().startsWith("/info/")) {
            new Responser(new GetAccountsInfo(config),config).doResponse();
        }
        else{
            req.setAttribute("name",req.getPathInfo().replace("/", ""));
            new Responser(new GetAccountByName(config), config).doResponse();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Config config = new Config(req, resp);
        if (req.getPathInfo().startsWith("/point/")) {
            new Responser(new PostPointAccount(config), config).doResponse();
        } else{
            new Responser(new PostAccount(config), config).doResponse();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)  {
        Config config = new Config(req, resp);
        if (req.getPathInfo().startsWith("/point/")) {
            req.setAttribute("id",req.getPathInfo().replace("/point/", ""));
            new Responser(new PutPointAccount(config),config).doResponse();
        } else{
            req.setAttribute("name",req.getPathInfo().replace("/", ""));
            new Responser(new PutAccountByName(config),config).doResponse();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)  {
        Config config = new Config(req, resp);
        if (req.getPathInfo().startsWith("/point/")) {
            req.setAttribute("id",req.getPathInfo().replace("/point/", ""));
            new Responser(new DeletePointAccountByID(config), config).doResponse();
        } else{
            req.setAttribute("name",req.getPathInfo().replace("/", ""));
            new Responser(new DeleteAccountByName(config), config).doResponse();
        }
    }
}
