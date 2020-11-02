package ru.vapima.butjet.dao.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.vapima.butjet.dao.Dao;
import ru.vapima.butjet.dao.JDBCpoolMock;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.AccRec;
import ru.vapima.butjet.model.Person;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AccRecDaoIT {

    public static Integer i;
    public static Dao<AccRec> daoAccRec;
    public static Dao<Person> daoPerson;
    public static Dao<Acc> daoAcc;

    static {
        daoPerson = new PersonDaoJDBC(JDBCpoolMock.getDataSource());
    }
    static {
        daoAccRec = new AccRecDaoJDBC(JDBCpoolMock.getDataSource());
    }
    static {
        daoAcc = new AccDaoJDBC(JDBCpoolMock.getDataSource());
    }

    @BeforeClass
    public static void beforeClass() throws SQLException, PersonExeption {
        Integer personId =  daoPerson.insert(new Person(0, "testForAccRecTest", "testToken", "testPassword"));
        Integer accId = daoAcc.insert(new Acc(0, "accTest", 100, LocalDateTime.now(), true, personId));
        i = daoAccRec.insert(new AccRec(0, 100,personId,accId , LocalDateTime.now()));
    }

    @AfterClass
    public static void afterClass() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        AccRec a = daoAccRec.takeBy(i);
        daoAccRec.delete(a);
        daoAcc.delete(daoAcc.takeBy(a.getAccId()));
        daoPerson.delete(daoPerson.takeBy(a.getPersonId()));

    }

    @Test
    public void takeById() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        AccRec a = daoAccRec.takeBy(i);
        assertEquals(Integer.valueOf(100), a.getBalance());
    }


    @Test
    public void delete() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        AccRec a = daoAccRec.takeBy(i);
        int i2 = daoAccRec.delete(a);
        assertEquals(1, i2);
        try{assertNull(daoAccRec.takeBy(i));}catch (AccExeption ae){assertEquals(ae.getMessage(),"Account Record not found.");}
        i = daoAccRec.insert(new AccRec(0, 100, a.getPersonId(), a.getAccId(), LocalDateTime.now()));
    }

    @Test
    public void update() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        AccRec a = daoAccRec.takeBy(i);
        a.setBalance(200);
        int i2 = daoAccRec.update(a);
        assertEquals(1, i2);
        assertEquals(Integer.valueOf(200),  daoAccRec.takeBy(i).getBalance());
        a = daoAccRec.takeBy(i);
        a.setBalance(100);
        i2 = daoAccRec.update(a);
        assertEquals(1, i2);
        assertEquals(Integer.valueOf(100),  daoAccRec.takeBy(i).getBalance());
    }

    @Test
    public void insert() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        Integer personId =  daoPerson.insert(new Person(0, "testForAccRecTest", "testToken", "testPassword"));
        Integer accId = daoAcc.insert(new Acc(0, "accTest", 100, LocalDateTime.now(), true, personId));
        int iInsert = daoAccRec.insert(new AccRec(0, 100,personId,accId , LocalDateTime.now()));
        assertEquals(Integer.valueOf(100),  daoAccRec.takeBy(iInsert).getBalance());
        AccRec a = daoAccRec.takeBy(iInsert);
        daoAccRec.delete(a);
        daoAcc.delete(daoAcc.takeBy(a.getAccId()));
        daoPerson.delete(daoPerson.takeBy(a.getPersonId()));
    }

    @Test
    public void takeAll() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<AccRec> accRecs = daoAccRec.takeAll();
        assertNotNull(accRecs);
        assertFalse(accRecs.isEmpty());
    }

    @Test
    public void takeAllByForeignKey() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<AccRec> accRecs = daoAccRec.takeAllByForeignKey(daoAccRec.takeBy(i).getPersonId());
        assertNotNull(accRecs);
        assertFalse(accRecs.isEmpty());
        assertEquals(accRecs.get(0).getPersonId(), daoAccRec.takeBy(i).getPersonId());
    }
}