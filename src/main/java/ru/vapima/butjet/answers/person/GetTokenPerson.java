package ru.vapima.butjet.answers.person;

import ru.vapima.butjet.answers.Answer;
import ru.vapima.butjet.answers.AuthPerson;
import ru.vapima.butjet.answers.Config;
import ru.vapima.butjet.dao.Dao;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.service.json.Serial;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class GetTokenPerson implements Answer {
    private Config config;
    private Dao<Person> personDAO;
    private HttpServletRequest req;
    private Serial serial;
    private AuthPerson authPerson;
    private static final String SPLITER="ID";

    public GetTokenPerson(Config config) {
        this.config = config;
        this.personDAO = config.getDaoFactory().createPersonDao();
        this.req = config.getReq();
        this.serial = config.getSerialFactory().createJsonSer();
        this.authPerson=config.getAuthPerson();
    }

    @Override
    public String run() throws PersonExeption, AccExeption, SQLException, PlanExeption {
        Person person=null;
        String inputToken=authPerson.getTokenAuthFromReq();
        String name = authPerson.getNameAuthFromReq();
        String password = authPerson.getPasswordAuthFromReq();
        if (inputToken==null){
            person = personDAO.takeBy(name);
            if(!authPerson.getPasswordHash().verifyHash(password, person.getPassword())) {throw new PersonExeption("Invalid password.");}
        } else {
            Integer id=Integer.parseInt(inputToken.split(SPLITER)[0]);
            person = personDAO.takeBy(id);
            if(!person.getToken().equals(inputToken.split(SPLITER)[1])){throw new PersonExeption("Invalid token.");}
            person.setToken(null);
            personDAO.update(person);
        }
        if (req.getParameter("type")!=null && req.getParameter("type").equalsIgnoreCase("telegram")) {
            if (authPerson.getPasswordHash().verifyHash(password, person.getPassword())) {
                String token=authPerson.getTokenService().getSimpleToken();
                person.setToken(token);
                personDAO.update(person);
                return person.getId()+SPLITER+token;
            } else throw new PersonExeption("Login or password invalid.");
        } else{
            return authPerson.getTokenService().create(person);
        }
    }
}
