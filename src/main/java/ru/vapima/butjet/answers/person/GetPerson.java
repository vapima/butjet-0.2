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

public class GetPerson implements Answer {
    private Config config;
    private PersonDAO personDAO;
    private HttpServletRequest req;
    private Serial serial;

    public GetPerson(Config config) {
        this.config = config;
        this.personDAO = config.getDaoFactory().createPersonDao();
        this.req = config.getReq();
        this.serial = config.getSerialFactory().createJsonSer();
    }


    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption {
        Person person = personDAO.takeBy(config.getAuthPerson().getPersonID());
        return serial.go(person);
    }
}
