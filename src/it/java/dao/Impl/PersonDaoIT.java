package dao.Impl;

import dao.DaoFactory;
import dao.PersonDAO;
import model.Person;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PersonDaoIT {
    public static Integer i;

    public static PersonDAO pd;

    static {
        pd = DaoFactory.createPersonDao();
    }

    public PersonDaoIT() {
    }

    @BeforeClass
    public static void beforeClass() throws SQLException {
        i = pd.insert(new Person(0, "test", "testToken", "testPassword"));

    }

    @AfterClass
    public static void afterClass() throws SQLException {
        pd.delete(pd.takeById(i));
    }

    @Test
    public void takeById() throws SQLException {
        Person p = pd.takeById(i);
        assertEquals("test", p.getName());
        assertEquals("testToken", p.getToken());
        assertEquals("testPassword", p.getPassword());
    }

    @Test
    public void takeByName() throws SQLException {
        Person p = pd.takeByName("test");
        assertEquals(i, p.getId());
        assertEquals("testToken", p.getToken());
        assertEquals("testPassword", p.getPassword());
    }

    @Test
    public void delete() throws SQLException {
        Person p = pd.takeById(i);
        int i2 = pd.delete(p);
        assertEquals(1, i2);
        assertNull(pd.takeById(i));
        i = pd.insert(new Person(0, "test", "testToken", "testPassword"));
    }

    @Test
    public void update() throws SQLException {
        Person p = pd.takeById(i);
        p.setName("testupdate");
        int i2 = pd.update(p);
        assertEquals(1, i2);
        assertEquals("testupdate", pd.takeById(i).getName());
        p = pd.takeById(i);
        p.setName("test");
        i2 = pd.update(p);
        assertEquals(1, i2);
        assertEquals("test", pd.takeById(i).getName());
    }

    @Test
    public void insert() throws SQLException {
        int iInsert = pd.insert(new Person(0, "testInsert", "testToken", "testPassword"));
        assertEquals("testInsert", pd.takeById(iInsert).getName());
        pd.delete(pd.takeById(iInsert));
    }

    @Test
    public void takeAll() throws SQLException {
        ArrayList<Person> persons = pd.takeAll();
        assertNotNull(persons);
        assertFalse(persons.isEmpty());
    }
}