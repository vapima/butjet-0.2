package ru.vapima.butjet.answers.person;

import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.dao.PersonDAO;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.service.json.Serial;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class GetTokenPerson implements Answer {
    private Config config;
    private PersonDAO personDAO;
    private HttpServletRequest req;
    private Serial serial;

    public GetTokenPerson(Config config) {
        this.config = config;
        this.personDAO = config.getDaoFactory().createPersonDao();
        this.req = config.getReq();
        this.serial = config.getSerialFactory().createJsonSer();
    }

    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption {
        String name = config.getAuthPerson().getNameAuthFromReq();
        String password = config.getAuthPerson().getPasswordAuthFromReq();
        Person person = personDAO.takeBy(name);
        if (config.getAuthPerson().getPasswordHash().verifyHash(password, person.getPassword())) {
            return config.getAuthPerson().getTokenService().create(person);
        } else throw new PersonExeption("Login or password invalid.");
    }
}
