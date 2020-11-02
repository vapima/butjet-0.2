package ru.vapima.butjet.answers.person;

import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.dao.Dao;
import ru.vapima.butjet.dao.DaoFactory;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;
import ru.vapima.butjet.service.json.Serial;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeletePerson implements Answer {
    Config config;
    private Dao<Person> personDAO;
    private HttpServletRequest req;
    private Serial serial;
    private DaoFactory daoFactory;

    public DeletePerson(Config config) {
        this.config = config;
        this.personDAO = config.getDaoFactory().createPersonDao();
        this.req = config.getReq();
        this.serial = config.getSerialFactory().createJsonSer();
        this.daoFactory = config.getDaoFactory();
    }

    @Override //NOT GOOD, but there is not awesome function =)
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption {
        Person person = personDAO.takeBy(config.getAuthPerson().getPersonID());
        for (Acc a : daoFactory.createAccDao().takeAllByForeignKey(person.getId())) {
            daoFactory.createAccDao().delete(a);
        }
        for (Plan p : daoFactory.createPlanDao().takeAllByForeignKey(person.getId())) {
            daoFactory.createPlanDao().delete(p);
        }
        Integer countDel = personDAO.delete(person);
        if (countDel != 1) {
            throw new AccExeption("So many fields you are deleted. Danger.");
        }
        return "{ \"status\":\"deleted\",\"rows\":\"" + countDel + "\"}";
    }
}
