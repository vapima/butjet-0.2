package ru.vapima.butjet.answers.person;

import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.dao.Dao;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.service.json.Serial;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class PutPerson implements Answer {
    private Config config;
    private Dao<Person> personDAO;
    private HttpServletRequest req;
    private Serial serial;

    public PutPerson(Config config) {
        this.config = config;
        this.personDAO = config.getDaoFactory().createPersonDao();
        this.req = config.getReq();
        this.serial = config.getSerialFactory().createJsonSer();
    }

    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption {
        Person person = personDAO.takeBy(config.getAuthPerson().getPersonID());
        String password = req.getHeader("password");
        person.setPassword(config.getAuthPerson().getPasswordHash().getHash(password));
        Integer i = personDAO.update(person);
        if (i != 1) {
            throw new PersonExeption("So many fields you are updated. Danger.");
        }
        return serial.parse(personDAO.takeBy(person.getId()));
    }

}
