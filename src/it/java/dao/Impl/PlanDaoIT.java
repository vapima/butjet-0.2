package dao.Impl;

import dao.DaoFactory;
import dao.PlanDAO;
import model.Person;
import model.Plan;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlanDaoIT {
    public static Integer i;
    public static PlanDAO pd;

    static {
        pd = DaoFactory.createPlanDao();
    }

    @BeforeClass
    public static void beforeClass() throws SQLException {
        i = pd.insert(new Plan(0, "planTest", 100, LocalDate.now(), DaoFactory.createPersonDao().insert(new Person(0, "testForPlanTest", "testToken", "testPassword"))));
    }

    @AfterClass
    public static void afterClass() throws SQLException {
        Plan p = pd.takeById(i);
        pd.delete(p);
        DaoFactory.createPersonDao().delete(DaoFactory.createPersonDao().takeById(p.getIdPerson()));
    }

    @Test
    public void takeById() throws SQLException {
        Plan p = pd.takeById(i);
        assertEquals("planTest", p.getName());
    }

    @Test
    public void takeByName() throws SQLException {
        Plan p = pd.takeByName("planTest");
        assertEquals("planTest", p.getName());
    }

    @Test
    public void delete() throws SQLException {
        Plan p = pd.takeById(i);
        int i2 = pd.delete(p);
        assertEquals(1, i2);
        assertNull(pd.takeById(i));
        i = pd.insert(new Plan(0, "planTest", 100, LocalDate.now(), p.getIdPerson()));
    }

    @Test
    public void update() throws SQLException {
        Plan p = pd.takeById(i);
        p.setName("testupdate");
        int i2 = pd.update(p);
        assertEquals(1, i2);
        assertEquals("testupdate", pd.takeById(i).getName());
        p = pd.takeById(i);
        p.setName("planTest");
        i2 = pd.update(p);
        assertEquals(1, i2);
        assertEquals("planTest", pd.takeById(i).getName());
    }

    @Test
    public void insert() throws SQLException {
        int iInsert = pd.insert(new Plan(0, "testInsert", 100, LocalDate.now(), DaoFactory.createPersonDao().insert(new Person(0, "testForPlanTest", "testToken", "testPassword"))));
        assertEquals("testInsert", pd.takeById(iInsert).getName());
        Plan p = pd.takeById(iInsert);
        pd.delete(p);
        DaoFactory.createPersonDao().delete(DaoFactory.createPersonDao().takeById(p.getIdPerson()));
    }

    @Test
    public void takeAll() throws SQLException {
        ArrayList<Plan> plans = pd.takeAll();
        assertNotNull(plans);
        assertFalse(plans.isEmpty());
    }

    @Test
    public void takeAllByForeignKey() throws SQLException {
        ArrayList<Plan> plans = pd.takeAllByForeignKey(pd.takeById(i).getIdPerson());
        assertNotNull(plans);
        assertFalse(plans.isEmpty());
        assertEquals(plans.get(0).getIdPerson(), pd.takeById(i).getIdPerson());
    }
}