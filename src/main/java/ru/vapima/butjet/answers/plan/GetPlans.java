package ru.vapima.butjet.answers.plan;

import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetPlans implements Answer {
    Config config;

    public GetPlans(Config config) {
        this.config = config;
    }

    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption, IOException {
        Person person = config.getDaoFactory().createPersonDao().takeBy(config.getAuthPerson().getPersonID());
        ArrayList<Plan> plans = config.getDaoFactory().createPlanDao().takeAllByForeignKey(person.getId());
        return config.getSerialFactory().createJsonSer().goPlans(plans);
    }
}
