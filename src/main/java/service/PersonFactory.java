package service;

import dao.AccDAO;
import dao.DaoFactory;
import dao.PersonDAO;
import dao.PlanDAO;
import exeptions.AccExeption;
import exeptions.PersonExeption;
import exeptions.PlanExeption;
import model.Person;

import java.sql.SQLException;

public class PersonFactory {
    private PersonDAO personDAO = DaoFactory.createPersonDao();
    private AccDAO accDAO = DaoFactory.createAccDao();
    private PlanDAO planDAO = DaoFactory.createPlanDao();
    private Person person;

    public Person getPerson(String name) throws SQLException, PersonExeption, PlanExeption, AccExeption {
        Validation.isNameOK(name);
        this.person = personDAO.takeByName(name);
        if (person == null) {
            throw new PersonExeption("Person not found.");
        }
        this.person.setAccs(accDAO.takeLastByForeignKey(person.getId()));
        this.person.setPlans(planDAO.takeAllByForeignKey(person.getId()));
        return person;
    }

    public Person getPerson(Integer id) throws SQLException, PersonExeption, AccExeption, PlanExeption {
        this.person = personDAO.takeById(id);
        if (person == null) {
            throw new PersonExeption("Person not found.");
        }
        this.person.setAccs(accDAO.takeLastByForeignKey(person.getId()));
        this.person.setPlans(planDAO.takeAllByForeignKey(person.getId()));
        return person;
    }

    public Person getEmptyPerson() {
        return new Person(0, "", "", "");
    }

    public Person getNewPerson(String name, String password) throws SQLException, PersonExeption, PlanExeption, AccExeption {
        if (DaoFactory.createPersonDao().takeByName(name) != null) {
            throw new PersonExeption("Name is already registered.");
        }
        Validation.isNameOK(name);
        Validation.isPasswordOK(password);
        this.person = new Person(0, name, "", password);
        this.person.setPassword(PasswordHash.getHash(password));
        return person;
    }

}
