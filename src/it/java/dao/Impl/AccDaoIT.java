package dao.Impl;

import dao.AccDAO;
import dao.DaoFactory;
import model.Acc;
import model.Person;
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

    static {
        ad = DaoFactory.createAccDao();
    }

    @BeforeClass
    public static void beforeClass() throws SQLException {
        i = ad.insert(new Acc(0, "accTest", 100, LocalDateTime.now(), true, DaoFactory.createPersonDao().insert(new Person(0, "testForAccTest", "testToken", "testPassword"))));
    }

    @AfterClass
    public static void afterClass() throws SQLException {
        Acc a = ad.takeById(i);
        ad.delete(a);
        DaoFactory.createPersonDao().delete(DaoFactory.createPersonDao().takeById(a.getPersonId()));
    }

    @Test
    public void takeById() throws SQLException {
        Acc a = ad.takeById(i);
        assertEquals("accTest", a.getName());
    }

    @Test
    public void takeByName() throws SQLException {
        Acc a = ad.takeByName("accTest");
        assertEquals("accTest", a.getName());
    }

    @Test
    public void delete() throws SQLException {
        Acc a = ad.takeById(i);
        int i2 = ad.delete(a);
        assertEquals(1, i2);
        assertNull(ad.takeById(i));
        i = ad.insert(new Acc(0, "accTest", 100, LocalDateTime.now(), true, a.getPersonId()));
    }

    @Test
    public void update() throws SQLException {
        Acc a = ad.takeById(i);
        a.setName("testupdate");
        int i2 = ad.update(a);
        assertEquals(1, i2);
        assertEquals("testupdate", ad.takeById(i).getName());
        a = ad.takeById(i);
        a.setName("accTest");
        i2 = ad.update(a);
        assertEquals(1, i2);
        assertEquals("accTest", ad.takeById(i).getName());
    }

    @Test
    public void insert() throws SQLException {
        int iInsert = ad.insert(new Acc(0, "testInsert", 100, LocalDateTime.now(), true, DaoFactory.createPersonDao().insert(new Person(0, "testForAccTest", "testToken", "testPassword"))));
        assertEquals("testInsert", ad.takeById(iInsert).getName());
        Acc a = ad.takeById(iInsert);
        ad.delete(a);
        DaoFactory.createPersonDao().delete(DaoFactory.createPersonDao().takeById(a.getPersonId()));
    }

    @Test
    public void takeAll() throws SQLException {
        ArrayList<Acc> accs = ad.takeAll();
        assertNotNull(accs);
        assertFalse(accs.isEmpty());
    }

    @Test
    public void takeAllByForeignKey() throws SQLException {
        ArrayList<Acc> accs = ad.takeAllByForeignKey(ad.takeById(i).getPersonId());
        assertNotNull(accs);
        assertFalse(accs.isEmpty());
        assertEquals(accs.get(0).getPersonId(), ad.takeById(i).getPersonId());
    }
}