package ru.vapima.butjet.answers.person;

import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.dao.Dao;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;
import ru.vapima.butjet.service.MyRdnMonth;
import ru.vapima.butjet.service.json.Serial;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetRdn implements Answer {
    private Config config;
    private Dao<Person> personDAO;
    private HttpServletRequest req;
    private Serial serial;

    public GetRdn(Config config) {
        this.config = config;
        this.personDAO = config.getDaoFactory().createPersonDao();
        this.req = config.getReq();
        this.serial = config.getSerialFactory().createJsonSer();
    }


    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption {
        Person person = personDAO.takeBy(config.getAuthPerson().getPersonID());
        ArrayList<Acc> accs = config.getDaoFactory().createAccDao().takeAllByForeignKey(person.getId());
        ArrayList<Plan> plans=config.getDaoFactory().createPlanDao().takeAllByForeignKey(person.getId());
        MyRdnMonth myRdnMonth=new MyRdnMonth(accs,plans);
        return myRdnMonth.getRdn().toString();
    }
}
