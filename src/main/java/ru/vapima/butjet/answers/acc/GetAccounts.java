package ru.vapima.butjet.answers.acc;

import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.Person;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetAccounts implements Answer {
    Config config;

    public GetAccounts(Config config) {
        this.config = config;
    }

    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption, IOException {
        Person person = config.getDaoFactory().createPersonDao().takeBy(config.getAuthPerson().getPersonID());
        ArrayList<Acc> accs = config.getDaoFactory().createAccDao().takeLastByForeignKey(person.getId());
        return config.getSerialFactory().createJsonSer().goAccs(accs);
    }
}
