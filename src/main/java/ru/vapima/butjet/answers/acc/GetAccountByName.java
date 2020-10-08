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

public class GetAccountByName implements Answer {
    Config config;

    public GetAccountByName(Config config) {
        this.config = config;
    }

    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption, IOException {
        Person person = config.getDaoFactory().createPersonDao().takeBy(config.getAuthPerson().getPersonID());
        String nameOfAccount = config.getReq().getAttribute("name").toString();
        Acc acc = config.getDaoFactory().createAccDao().takeBy(nameOfAccount);
        if (!acc.getPersonId().equals(person.getId())) {
            throw new AccExeption("This is not your account.");
        }
        return config.getSerialFactory().createJsonSer().go(acc);
    }
}
