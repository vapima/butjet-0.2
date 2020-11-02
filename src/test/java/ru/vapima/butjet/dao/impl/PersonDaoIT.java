package ru.vapima.butjet.dao.impl;

import ru.vapima.butjet.dao.Dao;
import ru.vapima.butjet.dao.JDBCpoolMock;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Person;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PersonDaoIT {
    public static Integer i;

    public static Dao<Person> pd;

    static {
        pd = new PersonDaoJDBC(JDBCpoolMock.getDataSource());
    }

    public PersonDaoIT() {
    }

    @BeforeClass
    public static void beforeClass() throws SQLException, PersonExeption {
        i = pd.insert(new Person(0, "test", "testToken", "testPassword"));
    }

    @AfterClass
    public static void afterClass() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        pd.delete(pd.takeBy(i));
    }

    @Test
    public void takeById() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        Person p = pd.takeBy(i);
        assertEquals("test", p.getName());
        assertEquals("testToken", p.getToken());
        assertEquals("testPassword", p.getPassword());
    }

    @Test
    public void takeByName() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        Person p = pd.takeBy("test");
        assertEquals(i, p.getId());
        assertEquals("testToken", p.getToken());
        assertEquals("testPassword", p.getPassword());
    }

    @Test
    public void delete() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        Person p = pd.takeBy(i);
        int i2 = pd.delete(p);
        assertEquals(1, i2);
        try{assertNull(pd.takeBy(i));}
        catch (PersonExeption pe){assertEquals(pe.getMessage(),"Person not found.");}
        i = pd.insert(new Person(0, "test", "testToken", "testPassword"));
    }

    @Test
    public void update() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        Person p = pd.takeBy(i);
        p.setName("testupdate");
        int i2 = pd.update(p);
        assertEquals(1, i2);
        assertEquals("testupdate", pd.takeBy(i).getName());
        p = pd.takeBy(i);
        p.setName("test");
        i2 = pd.update(p);
        assertEquals(1, i2);
        assertEquals("test", pd.takeBy(i).getName());
    }

    @Test
    public void insert() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        int iInsert = pd.insert(new Person(0, "testInsert", "testToken", "testPassword"));
        assertEquals("testInsert", pd.takeBy(iInsert).getName());
        pd.delete(pd.takeBy(iInsert));
    }

    @Test
    public void takeAll() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<Person> persons = pd.takeAll();
        assertNotNull(persons);
        assertFalse(persons.isEmpty());
    }
}