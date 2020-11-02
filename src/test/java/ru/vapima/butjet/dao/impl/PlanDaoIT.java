package ru.vapima.butjet.dao.impl;

import ru.vapima.butjet.dao.Dao;
import ru.vapima.butjet.dao.JDBCpoolMock;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlanDaoIT {
    public static Integer i;
    public static Dao<Plan> pd;

    static {
        pd = new PlanDaoJDBC(JDBCpoolMock.getDataSource());
    }
    public static Dao<Person> perd;

    static {
        perd = new PersonDaoJDBC(JDBCpoolMock.getDataSource());
    }
    @BeforeClass
    public static void beforeClass() throws SQLException, PersonExeption {
        i = pd.insert(new Plan(0, "planTest", 100, LocalDate.now(), perd.insert(new Person(0, "testForPlanTest", "testToken", "testPassword"))));
    }

    @AfterClass
    public static void afterClass() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        Plan p = pd.takeBy(i);
        pd.delete(p);
        perd.delete(perd.takeBy(p.getIdPerson()));
    }

    @Test
    public void takeById() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        Plan p = pd.takeBy(i);
        assertEquals("planTest", p.getName());
    }

    @Test
    public void takeByName() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        Plan p = pd.takeBy("planTest");
        assertEquals("planTest", p.getName());
    }

    @Test
    public void delete() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        Plan p = pd.takeBy(i);
        int i2 = pd.delete(p);
        assertEquals(1, i2);
        try {assertNull(pd.takeBy(i));} catch (PlanExeption px){assertEquals(px.getMessage(),"Plan not found.");}
        i = pd.insert(new Plan(0, "planTest", 100, LocalDate.now(), p.getIdPerson()));
    }

    @Test
    public void update() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        Plan p = pd.takeBy(i);
        p.setName("testupdate");
        int i2 = pd.update(p);
        assertEquals(1, i2);
        assertEquals("testupdate", pd.takeBy(i).getName());
        p = pd.takeBy(i);
        p.setName("planTest");
        i2 = pd.update(p);
        assertEquals(1, i2);
        assertEquals("planTest", pd.takeBy(i).getName());
    }

    @Test
    public void insert() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        int iInsert = pd.insert(new Plan(0, "testInsert", 100, LocalDate.now(), perd.insert(new Person(0, "testForPlanTest", "testToken", "testPassword"))));
        assertEquals("testInsert", pd.takeBy(iInsert).getName());
        Plan p = pd.takeBy(iInsert);
        pd.delete(p);
        perd.delete(perd.takeBy(p.getIdPerson()));
    }

    @Test
    public void takeAll() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<Plan> plans = pd.takeAll();
        assertNotNull(plans);
        assertFalse(plans.isEmpty());
    }

    @Test
    public void takeAllByForeignKey() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<Plan> plans = pd.takeAllByForeignKey(pd.takeBy(i).getIdPerson());
        assertNotNull(plans);
        assertFalse(plans.isEmpty());
        assertEquals(plans.get(0).getIdPerson(), pd.takeBy(i).getIdPerson());
    }
}