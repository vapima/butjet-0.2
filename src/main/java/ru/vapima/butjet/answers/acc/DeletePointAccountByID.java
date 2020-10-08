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

public class DeletePointAccountByID implements Answer {
    Config config;

    public DeletePointAccountByID(Config config) {
        this.config = config;
    }

    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption, IOException {
        Person person = config.getDaoFactory().createPersonDao().takeBy(config.getAuthPerson().getPersonID());
        String idOfAccount = config.getReq().getAttribute("id").toString();
        Acc acc = config.getDaoFactory().createAccDao().takeBy(Integer.parseInt(idOfAccount));
        if (!acc.getPersonId().equals(person.getId())) {
            throw new AccExeption("This is not your account.");
        }
        Integer countDel = config.getDaoFactory().createAccDao().delete(acc);
        if (countDel != 1) {
            throw new AccExeption("So many fields you are deleted. Danger. Rows -" + countDel);
        }
        return "{ \"status\":\"deleted\",\"rows\":\"" + countDel + "\"}";
    }
}
