package ru.vapima.butjet.answers.acc;

import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.dao.AccInfoDAO;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.AccInfoEntity;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.service.json.Deserial;

import java.io.IOException;
import java.sql.SQLException;

public class PutAccountByName implements Answer {
    Config config;

    public PutAccountByName(Config config) {
        this.config = config;
    }

    // CREATE NEW ACCOUNT, NEW NAME, NEW HISTORY
    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption, IOException {
        Deserial deserial=config.getSerialFactory().createJsonDeser();
        AccInfoDAO accInfoDao=  config.getDaoFactory().createAccInfoDao();
        Person person = config.getDaoFactory().createPersonDao().takeBy(config.getAuthPerson().getPersonID());
        AccInfoEntity inputAccountInfo = deserial.getAccInfoEntity(config.getReq());
        String nameOfAccount = config.getReq().getAttribute("name").toString();
        AccInfoEntity targetAccountInfo=accInfoDao.takeBy(nameOfAccount);
        if (!targetAccountInfo.getPersonId().equals(person.getId())) {
            throw new AccExeption("This is not your account.");
        }
        if(inputAccountInfo.getActive()!=null){targetAccountInfo.setActive(inputAccountInfo.getActive());}
        if(inputAccountInfo.getNameInfo()!=null){targetAccountInfo.setNameInfo(inputAccountInfo.getNameInfo());}
        if(inputAccountInfo.getDescription()!=null){targetAccountInfo.setDescription(inputAccountInfo.getDescription());}
        Integer countUpdate=accInfoDao.update(targetAccountInfo);
        if (countUpdate != 1) {
            throw new PlanExeption("So many fields you are updated. Danger.");
        }
        return config.getSerialFactory().createJsonSer().go(accInfoDao.takeBy(targetAccountInfo.getId()));
    }
}
