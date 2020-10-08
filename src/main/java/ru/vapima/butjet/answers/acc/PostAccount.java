package ru.vapima.butjet.answers.acc;

import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.dao.AccDAO;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.AccInfoEntity;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.service.json.Deserial;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PostAccount implements Answer {
    Config config;

    public PostAccount(Config config) {
        this.config = config;
    }

    // CREATE NEW ACCOUNT, NEW NAME, NEW HISTORY
    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption, IOException {
        Deserial deserial=config.getSerialFactory().createJsonDeser();
        Person person = config.getDaoFactory().createPersonDao().takeBy(config.getAuthPerson().getPersonID());
        Acc acc = deserial.getAccaunt(config.getReq());
        acc.setChangTime(LocalDateTime.now());
        acc.setPersonId(person.getId());
        AccInfoEntity accInfoEntity=deserial.getAccInfoEntity(config.getReq());
        accInfoEntity.setPersonId(person.getId());
        AccDAO accDAO = config.getDaoFactory().createAccDao();
        Integer countAccInsert = accDAO.insert(acc);
        config.getDaoFactory().createAccInfoDao().insert(accInfoEntity);
        return config.getSerialFactory().createJsonSer().go(accDAO.takeBy(countAccInsert));
    }
}
