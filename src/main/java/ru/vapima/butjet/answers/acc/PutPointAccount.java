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

public class PutPointAccount implements Answer {
    Config config;

    public PutPointAccount(Config config) {
        this.config = config;
    }

    //CREATE NEW POINT WITH NEW BALANCE, OLD NAME, OLD HISTORY
    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption, IOException {
        Person person = config.getDaoFactory().createPersonDao().takeBy(config.getAuthPerson().getPersonID());
        Acc accInput = config.getSerialFactory().createJsonDeser().getAccaunt(config.getReq());
        AccDAO accDao=config.getDaoFactory().createAccDao();
        Integer inputId=Integer.parseInt(config.getReq().getAttribute("id").toString());
        Acc targetAcc=accDao.takeBy(inputId);
        if (!targetAcc.getPersonId().equals(person.getId())) {
            throw new AccExeption("This is not your account.");
        }
        if(accInput.getBalance()!=null){targetAcc.setBalance(accInput.getBalance());}
        accDao.update(targetAcc);
        return config.getSerialFactory().createJsonSer().go(accDao.takeBy(targetAcc.getId()));
    }
}

