package ru.vapima.butjet.service;

import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import org.json.JSONException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.logging.Logger;
//TODO NEED TEST
public class Responser {
    Logger log = Logger.getLogger(Responser.class.getName());
    HttpServletResponse resp;
    private Answer answer;
    private Config config;

    public Responser(Answer answer, Config config) {
        this.answer = answer;
        this.config = config;
        this.resp = config.getResp();
    }

    public void doResponse() {
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(200);
            resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            PrintWriter writer = resp.getWriter();
            writer.println(answer.run());
        } catch (JSONException | PersonExeption | AccExeption | PlanExeption e) {
            log.info(e.toString() + Arrays.toString(e.getStackTrace()));
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(400);
            PrintWriter writer = null;
            try {
                writer = resp.getWriter();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            writer.println(e.getMessage());
        } catch (NumberFormatException e){
            log.info(e.toString() + Arrays.toString(e.getStackTrace()));
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(400);
            PrintWriter writer = null;
            try {
                writer = resp.getWriter();
            } catch (IOException  ex) {
                System.out.println(ex.getMessage());
            }
            writer.println("Argument if not correct. " +e.getMessage());
        } catch (Exception e) {
            log.info(e.toString() + Arrays.toString(e.getStackTrace()));
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(500);
            PrintWriter writer = null;
            try {
                writer = resp.getWriter();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            writer.println("Unknown error. " + e.toString());

        }
    }
}
