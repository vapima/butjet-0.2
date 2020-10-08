package ru.vapima.butjet.dao.impl;

import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import ru.vapima.butjet.model.*;
import ru.vapima.butjet.dao.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class AccInfoDaoJDBCIT {
    public static Integer i;
    public static AccInfoEntity accInfoEntity;
    public static AccInfoDaoJDBC aid;
    public static PersonDAO perd;

    static {
        aid = new AccInfoDaoJDBC(JDBCpoolStub.getDataSource());
        perd = new PersonDaoJDBC(JDBCpoolStub.getDataSource());
    }

    @BeforeClass
    public static void beforeClass() throws SQLException, PersonExeption {
        Integer personId=perd.insert(new Person(0, "testForAccInfoTest", "testToken", "testPassword"));
        i = aid.insert(new AccInfoEntity(1,personId,"testIT","testIT","testIT",true));
    }

    @AfterClass
    public static void afterClass() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        AccInfoEntity accInfoEntity2=aid.takeBy(i);
        aid.delete(accInfoEntity2);
        perd.delete(perd.takeBy(accInfoEntity2.getPersonId()));
    }
    @Test
    public void takeAllByForeignKey() throws PlanExeption, SQLException, PersonExeption, AccExeption {
        ArrayList<AccInfoEntity> accs = aid.takeAllByForeignKey(aid.takeBy(i).getPersonId());
        assertNotNull(accs);
        assertFalse(accs.isEmpty());
        assertEquals(accs.get(0).getPersonId(), aid.takeBy(i).getPersonId());
    }

    @Test
    public void takeBy() throws PlanExeption, SQLException, PersonExeption, AccExeption {
        AccInfoEntity a = aid.takeBy(i);
        assertEquals("testIT", a.getNameInfo());
    }

    @Test
    public void testTakeBy() throws PlanExeption, SQLException, PersonExeption, AccExeption {
        AccInfoEntity a = aid.takeBy(aid.takeBy(i).getName());
        assertEquals("testIT", a.getNameInfo());
    }

    @Test
    public void delete() throws PlanExeption, SQLException, PersonExeption, AccExeption {
        AccInfoEntity a = aid.takeBy(i);
        int i2 = aid.delete(a);
        assertEquals(1, i2);
        try{assertNull(aid.takeBy(i));}catch (AccExeption ae){assertEquals(ae.getMessage(),"Account not found.");}
        i = aid.insert(new AccInfoEntity(1,a.getPersonId(),"testIT","testIT","testIT",true));
    }

    @Test
    public void update() throws PlanExeption, SQLException, PersonExeption, AccExeption {
        AccInfoEntity a = aid.takeBy(i);
        a.setNameInfo("testupdate");
        int i2 = aid.update(a);
        assertEquals(1, i2);
        assertEquals("testupdate", aid.takeBy(i).getNameInfo());
        a = aid.takeBy(i);
        a.setNameInfo("testIT");
        i2 = aid.update(a);
        assertEquals(1, i2);
        assertEquals("testIT", aid.takeBy(i).getNameInfo());
    }

    @Test
    public void insert() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        Integer personId=perd.insert(new Person(0, "testForAccInfoTest", "testToken", "testPassword"));
        int iInsert = aid.insert(new AccInfoEntity(1,personId,"testIT","testInsert","testIT",true));
        assertEquals("testInsert", aid.takeBy(iInsert).getNameInfo());
        AccInfoEntity a = aid.takeBy(iInsert);
        aid.delete(a);
        perd.delete(perd.takeBy(a.getPersonId()));
    }

    @Test
    public void takeAll() throws PlanExeption, SQLException, PersonExeption, AccExeption {
        ArrayList<AccInfoEntity> accs = aid.takeAll();
        assertNotNull(accs);
        assertFalse(accs.isEmpty());
    }

    @Test
    public void testTakeAll() throws PlanExeption, SQLException, PersonExeption, AccExeption {
        ArrayList<AccInfoEntity> accs = aid.takeAll("TestIT");
        assertNotNull(accs);
        assertFalse(accs.isEmpty());
    }
}