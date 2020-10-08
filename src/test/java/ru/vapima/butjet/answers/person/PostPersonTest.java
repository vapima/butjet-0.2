package ru.vapima.butjet.answers.person;

import ru.vapima.butjet.answers.ConfigStub;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class PostPersonTest {

    @Test
    public void run() throws SQLException, AccExeption, PersonExeption, PlanExeption, IOException {
        String str=new PostPerson(new ConfigStub()).run();
        assertNotNull(str);
        assertEquals(str,"Person{id=1, name='test', token='test', password='test'}");
    }
}