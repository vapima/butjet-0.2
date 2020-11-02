package ru.vapima.butjet.answers.person;

import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.dao.Dao;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.service.json.Deserial;
import ru.vapima.butjet.service.json.Serial;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class PostPerson implements Answer {
    private Config config;
    private Dao<Person> personDAO;
    private HttpServletRequest req;
    private Serial serial;
    private Deserial deserial;

    public PostPerson(Config config) {
        this.config = config;
        this.personDAO = config.getDaoFactory().createPersonDao();
        this.req = config.getReq();
        this.serial = config.getSerialFactory().createJsonSer();
        this.deserial = config.getSerialFactory().createGsonDeser();
    }

    @Override
    public String run() throws PersonExeption, IOException, SQLException, PlanExeption, AccExeption {
        String name = config.getAuthPerson().getNameAuthFromReq();
        String password = config.getAuthPerson().getPasswordAuthFromReq();
        Person person = deserial.getPerson(req);
        person.setName(name);
        person.setPassword(config.getAuthPerson().getPasswordHash().getHash(password));
        config.getValidation().check(person);
        Integer i = personDAO.insert(person);
        return serial.parse(personDAO.takeBy(i));
    }

}
