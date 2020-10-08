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

public class PutPlanByID implements Answer {
    Config config;

    public PutPlanByID(Config config) {
        this.config = config;
    }

    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption, IOException {
        Person person = config.getDaoFactory().createPersonDao().takeBy(config.getAuthPerson().getPersonID());
        Plan planInpt = config.getSerialFactory().createJsonDeser().getPlan(config.getReq());
        PlanDAO planDAO = config.getDaoFactory().createPlanDao();
        Integer idPlan = Integer.parseInt(config.getReq().getAttribute("id").toString());
        Plan plan = planDAO.takeBy(idPlan);
        if (!plan.getIdPerson().equals(person.getId())) {
            throw new PlanExeption("This is not your account. Bad ass.");
        }
        plan.setName(planInpt.getName());
        plan.setBalance(planInpt.getBalance());
        plan.setDateExpiration(planInpt.getDateExpiration());
        Integer i = planDAO.update(plan);
        if (i != 1) {
            throw new PlanExeption("So many fields you are updated. Danger.");
        }
        return config.getSerialFactory().createJsonSer().go(planDAO.takeBy(plan.getId()));
    }
}
