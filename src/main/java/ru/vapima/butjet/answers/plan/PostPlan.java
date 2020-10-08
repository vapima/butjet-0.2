package ru.vapima.butjet.answers.plan;

import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.dao.PlanDAO;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;

import java.io.IOException;
import java.sql.SQLException;

public class PostPlan implements Answer {
    Config config;

    public PostPlan(Config config) {
        this.config = config;
    }

    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption, IOException {
        Person person = config.getDaoFactory().createPersonDao().takeBy(config.getAuthPerson().getPersonID());
        Plan plan = config.getSerialFactory().createJsonDeser().getPlan(config.getReq());
        plan.setIdPerson(person.getId());
        PlanDAO planDAO = config.getDaoFactory().createPlanDao();
        Integer i = planDAO.insert(plan);
        return config.getSerialFactory().createJsonSer().go(planDAO.takeBy(i));
    }
}
