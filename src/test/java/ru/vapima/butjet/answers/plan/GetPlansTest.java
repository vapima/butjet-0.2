package ru.vapima.butjet.answers.plan;

import ru.vapima.butjet.answers.ConfigStub;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class GetPlansTest {

    @Test
    public void run() throws PlanExeption, SQLException, PersonExeption, AccExeption, IOException {
        String str= new GetPlans(new ConfigStub()).run();
        assertNotNull(str);
        assertEquals(str,"[Plan{id=1, name='test', balance=0, dateExpiration=2029-01-01, personId=1}]");
    }
}