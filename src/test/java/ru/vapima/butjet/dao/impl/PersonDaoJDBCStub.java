package ru.vapima.butjet.dao.impl;

import ru.vapima.butjet.dao.Dao;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Person;

import java.sql.SQLException;
import java.util.ArrayList;

public class PersonDaoJDBCStub implements Dao<Person> {
Person person=new Person(1,"test","test","test");

    @Override
    public Person takeBy(Integer id) throws SQLException, PersonExeption, AccExeption, PlanExeption {
        return person;
    }

    @Override
    public Person takeBy(String name) throws SQLException, PersonExeption, AccExeption, PlanExeption {
        return person;
    }

    @Override
    public Integer delete(Person person) throws SQLException {
        return 1;
    }

    @Override
    public Integer update(Person person) throws SQLException {
        return 1;
    }

    @Override
    public Integer insert(Person person) throws SQLException {
        return 1;
    }

    @Override
    public ArrayList<Person> takeAll() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<Person> people=new ArrayList<>();
        people.add(person);
        return people;
    }

    @Override
    public ArrayList<Person> takeAllByForeignKey(Integer foreignKey) throws SQLException, AccExeption, PlanExeption, PersonExeption {
        ArrayList<Person> people=new ArrayList<>();
        people.add(person);
        return people;
    }


}