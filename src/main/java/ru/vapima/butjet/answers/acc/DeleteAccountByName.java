package ru.vapima.butjet.answers.acc;

import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.AccInfoEntity;
import ru.vapima.butjet.model.Person;

import java.io.IOException;
import java.sql.SQLException;

public class DeleteAccountByName implements Answer {
    Config config;

    public DeleteAccountByName(Config config) {
        this.config = config;
    }

    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption, IOException {
        Person person = config.getDaoFactory().createPersonDao().takeBy(config.getAuthPerson().getPersonID());
        String inputName = config.getReq().getAttribute("name").toString();
        Integer countDel = config.getDaoFactory().createAccDao().deleteAllByNameAndIdPerson(inputName, person.getId());
        AccInfoEntity accInfoEntity=config.getDaoFactory().createAccInfoDao().takeBy(inputName);
        countDel+=config.getDaoFactory().createAccInfoDao().delete(accInfoEntity);
        return "{ \"status\":\"deleted\",\"rows\":\"" + countDel + "\"}";
    }
}
