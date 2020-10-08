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

public class DeletePlanByID implements Answer {
    Config config;

    public DeletePlanByID(Config config) {
        this.config = config;
    }

    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption, IOException {
        Person person = config.getDaoFactory().createPersonDao().takeBy(config.getAuthPerson().getPersonID());
        Integer idPlan = Integer.parseInt(config.getReq().getAttribute("id").toString());
        PlanDAO planDAO = config.getDaoFactory().createPlanDao();
        Plan plan = planDAO.takeBy(idPlan);
        if (!plan.getIdPerson().equals(person.getId())) {
            throw new PersonExeption("This is not your account. Bad ass.");
        }
        Integer countDel = planDAO.delete(plan);
        if (countDel != 1) {
            throw new PlanExeption("So many fields you are deleted. Danger.");
        }
        return "{ \"status\":\"deleted\",\"rows\":\"" + countDel + "\"}";
    }

}