package ru.vapima.butjet.answers;

import org.junit.Test;
import ru.vapima.butjet.dao.impl.AccDaoJDBCStub;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Acc;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class CoreTest {

    @Test
    public void getList() throws PlanExeption, SQLException, PersonExeption, AccExeption, IOException {
        Config config=new ConfigStub();
        Answer answer=()-> new Core<Acc>(config).getList(config.getDaoFactory().createAccDao());
        assertEquals("[{\"id\":1,\"name\":\"test\",\"balance\":0,\"changeTime\":\"2029-10-01T16:11:34\",\"active\":true,\"personId\":1}]",answer.run());
    }

    @Test
    public void getEntity() throws PlanExeption, SQLException, PersonExeption, AccExeption, IOException {
        Config config=new ConfigStub();
        Answer answer=()-> new Core<Acc>(config).getEntity(config.getDaoFactory().createAccDao());
        assertEquals("{\"id\":1,\"name\":\"test\",\"balance\":0,\"changeTime\":\"2029-10-01T16:11:34\",\"active\":true,\"personId\":1}",answer.run());
    }

    @Test
    public void postEntity() throws PlanExeption, SQLException, PersonExeption, AccExeption, IOException {
        Config config=new ConfigStub();
        Answer answer=()-> new Core<Acc>(config).postEntity(config.getDaoFactory().createAccDao(),Acc.class);
        assertEquals("{\"id\":1,\"name\":\"test\",\"balance\":0,\"changeTime\":\"2029-10-01T16:11:34\",\"active\":true,\"personId\":1}",answer.run());
    }

    @Test
    public void deleteEntity() throws PlanExeption, SQLException, PersonExeption, AccExeption, IOException {
        Config config=new ConfigStub();
        Answer answer=()-> new Core<Acc>(config).deleteEntity(config.getDaoFactory().createAccDao());
        assertEquals("{ \"status\":\"deleted\",\"rows\":\"1\"}",answer.run());
    }

    @Test
    public void updateEntity() throws PlanExeption, SQLException, PersonExeption, AccExeption, IOException {
        Config config=new ConfigStub();
        Answer answer=()-> new Core<Acc>(config).updateEntity(config.getDaoFactory().createAccDao(),Acc.class);
        assertEquals("{\"id\":1,\"name\":\"test\",\"balance\":0,\"changeTime\":\"2029-10-01T16:11:34\",\"active\":true,\"personId\":1}",answer.run());
    }
}