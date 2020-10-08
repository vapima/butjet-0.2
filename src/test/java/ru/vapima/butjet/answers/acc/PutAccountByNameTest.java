package ru.vapima.butjet.answers.acc;

import ru.vapima.butjet.answers.ConfigStub;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class PutAccountByNameTest {

    @Test
    public void run() throws PlanExeption, SQLException, PersonExeption, AccExeption, IOException {
        String str=new PutAccountByName(new ConfigStub()).run();
        assertNotNull(str);
        assertEquals(str,"AccInfoEntity{id=1, personId=1, nameAcc='testIT', name='test', description='test', active=true}");
    }
}