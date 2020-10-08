package ru.vapima.butjet.dao.impl;

import ru.vapima.butjet.dao.AccDAO;
import ru.vapima.butjet.dao.JDBCpoolStub;
import ru.vapima.butjet.dao.PersonDAO;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.Person;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AccDaoIT {
    public static Integer i;
    public static AccDAO ad;
    public static PersonDAO pd;

    static {
        pd = new PersonDaoJDBC(JDBCpoolStub.getDataSource());
    }
    static {
        ad = new AccDaoJDBC(JDBCpoolStub.getDataSource());
    }

    @BeforeClass
    public static void beforeClass() throws SQLException, PersonExeption {
        i = ad.insert(new Acc(0, "accTest", 100, LocalDateTime.now(), true, pd.insert(new Person(0, "testForAccTest", "testToken", "testPassword"))));
    }

    @AfterClass
    public static void afterClass() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        Acc a = ad.takeBy(i);
        ad.delete(a);
        pd.delete(pd.takeBy(a.getPersonId()));
    }

    @Test
    public void takeById() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        Acc a = ad.takeBy(i);
        assertEquals("accTest", a.getName());
    }

    @Test
    public void takeByName() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        Acc a = ad.takeBy("accTest");
        assertEquals("accTest", a.getName());
    }

    @Test
    public void delete() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        Acc a = ad.takeBy(i);
        int i2 = ad.delete(a);
        assertEquals(1, i2);
        try{assertNull(ad.takeBy(i));}catch (AccExeption ae){assertEquals(ae.getMessage(),"Account not found.");}
        i = ad.insert(new Acc(0, "accTest", 100, LocalDateTime.now(), true, a.getPersonId()));
    }

    @Test
    public void update() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        Acc a = ad.takeBy(i);
        a.setName("testupdate");
        int i2 = ad.update(a);
        assertEquals(1, i2);
        assertEquals("testupdate", ad.takeBy(i).getName());
        a = ad.takeBy(i);
        a.setName("accTest");
        i2 = ad.update(a);
        assertEquals(1, i2);
        assertEquals("accTest", ad.takeBy(i).getName());
    }

    @Test
    public void insert() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        int iInsert = ad.insert(new Acc(0, "testInsert", 100, LocalDateTime.now(), true, pd.insert(new Person(0, "testForAccTest", "testToken", "testPassword"))));
        assertEquals("testInsert", ad.takeBy(iInsert).getName());
        Acc a = ad.takeBy(iInsert);
        ad.delete(a);
        pd.delete(pd.takeBy(a.getPersonId()));
    }

    @Test
    public void takeAll() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<Acc> accs = ad.takeAll();
        assertNotNull(accs);
        assertFalse(accs.isEmpty());
    }

    @Test
    public void takeAllByForeignKey() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<Acc> accs = ad.takeAllByForeignKey(ad.takeBy(i).getPersonId());
        assertNotNull(accs);
        assertFalse(accs.isEmpty());
        assertEquals(accs.get(0).getPersonId(), ad.takeBy(i).getPersonId());
    }
}