package ru.vapima.butjet.answers.acc;

import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.dao.AccDAO;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.Person;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PostPointAccount implements Answer {
    Config config;

    public PostPointAccount(Config config) {
        this.config = config;
    }

    //CREATE NEW POINT WITH NEW BALANCE, OLD NAME, OLD HISTORY
    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption, IOException {
        Person person = config.getDaoFactory().createPersonDao().takeBy(config.getAuthPerson().getPersonID());
        Acc acc = config.getSerialFactory().createJsonDeser().getAccaunt(config.getReq());
        acc.setChangTime(LocalDateTime.now());
        acc.setPersonId(person.getId());
        AccDAO accDAO = config.getDaoFactory().createAccDao();
        Integer i = accDAO.insert(acc);
        return config.getSerialFactory().createJsonSer().go(accDAO.takeBy(i));
    }
}

