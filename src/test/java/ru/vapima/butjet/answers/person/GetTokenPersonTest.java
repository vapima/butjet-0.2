package ru.vapima.butjet.answers.person;

import ru.vapima.butjet.answers.ConfigStub;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class GetTokenPersonTest {

    @Test
    public void run() throws PlanExeption, SQLException, PersonExeption, AccExeption {
        String str=new GetTokenPerson(new ConfigStub()).run();
        assertNotNull(str);
        assertEquals(str,"test");
    }
}