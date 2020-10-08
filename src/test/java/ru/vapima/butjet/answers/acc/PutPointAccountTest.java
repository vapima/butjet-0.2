package ru.vapima.butjet.answers.acc;

import ru.vapima.butjet.answers.ConfigStub;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class PutPointAccountTest {

    @Test
    public void run() throws PlanExeption, SQLException, PersonExeption, AccExeption, IOException {
        String str=new PutPointAccount(new ConfigStub()).run();
        assertNotNull(str);
        assertEquals(str,"Acc{id=1, name='test', balance=0, changTime=2029-10-01T16:11:34.200842, isActive=true, personId=1}");
    }
}