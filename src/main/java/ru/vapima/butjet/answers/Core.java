package ru.vapima.butjet.answers;

import ru.vapima.butjet.dao.Dao;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.PersonID;
import ru.vapima.butjet.service.ObjectSetter;
import ru.vapima.butjet.service.ObjectUpdater;
import ru.vapima.butjet.service.json.Parser;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Core<T> {
    HttpServletRequest req;
    StringBuilder bodyData;
    Person person;
    Parser<T> parser;
    Config config;

    public Core(Config config) {
        this.config = config;
        this.req=config.getReq();
        this.bodyData=config.getBodyData();
        this.parser=new Parser<>();
    }

    public void initPerson() throws PersonExeption, AccExeption, SQLException, PlanExeption {
        person = config.getDaoFactory().createPersonDao().takeBy(config.getAuthPerson().getPersonID());
    }

    public String getList(Dao<T> dao) throws PersonExeption, AccExeption, SQLException, PlanExeption {
        initPerson();
        ArrayList<T> list=dao.takeAllByForeignKey(person.getId());
        return parser.parceArray(list);
    }
    public String getEntity(Dao<T> dao) throws PersonExeption, AccExeption, SQLException, PlanExeption {
        initPerson();
        Integer id = Integer.parseInt(req.getAttribute("id").toString());
        T t=dao.takeBy(id);
        checkPersonAndEntitysID(t,person);
        return parser.parse(t);
    }
    public String postEntity(Dao<T> dao, Class cls) throws PersonExeption, AccExeption, SQLException, PlanExeption, IOException {
        initPerson();
        T entity= parser.deparse(bodyData.toString(),cls);
        ObjectSetter.setPersonId(entity,person);
        Integer i = dao.insert(entity);
        return parser.parse(dao.takeBy(i));
    }
    public String deleteEntity(Dao<T> dao) throws PersonExeption, AccExeption, SQLException, PlanExeption {
        initPerson();
        Integer id = Integer.parseInt(req.getAttribute("id").toString());
        T t = dao.takeBy(id);
        checkPersonAndEntitysID(t,person);
        Integer countDel = dao.delete(t);
        if (countDel != 1) {
            throw new PlanExeption("So many fields you are deleted. Danger.");
        }
        return "{ \"status\":\"deleted\",\"rows\":\"" + countDel + "\"}";
    }
    public String updateEntity(Dao<T> dao, Class cls) throws PersonExeption, AccExeption, SQLException, PlanExeption, IOException {
        initPerson();
        T tInput = parser.deparse(bodyData.toString(),cls);
        Integer id = Integer.parseInt(req.getAttribute("id").toString());
        T t = dao.takeBy(id);
        checkPersonAndEntitysID(t,person);
        ObjectUpdater.update(tInput,t);
        Integer i = dao.update(t);
        if (i != 1) {
            throw new PlanExeption("So many fields you are updated. Danger.");
        }
        return parser.parse(dao.takeBy(id));
    }
   private void checkPersonAndEntitysID(Object o,Person person) throws PlanExeption {
       PersonID personID=(PersonID) o;
       if (!personID.takePersonId().equals(person.getId())) {
           throw new PlanExeption("It's not yours. Bad ass.");
       }
   }

}
