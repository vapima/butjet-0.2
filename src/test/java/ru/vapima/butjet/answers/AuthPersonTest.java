package ru.vapima.butjet.answers;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.service.TokenService;
import ru.vapima.butjet.service.ValidationStub;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.*;

public class AuthPersonTest {
    public static AuthPerson authPerson;
    @BeforeClass
    public static void beforeClass(){
        Config config=new ConfigStub();
        authPerson=new AuthPerson(config,config.getReq());
        authPerson.setTokenService(new TokenService(){
            @Override
            public Integer decode(String swt) throws PersonExeption {
                if (swt.equalsIgnoreCase("testToken")){return 1;}
                return null;
            }
        });
        config.setValidation(new ValidationStub());
    }
    @Test
    public void getPersonID() throws PersonExeption {
        assertEquals(Integer.valueOf(1),authPerson.getPersonID());
    }

    @Test
    public void getNameAuthFromReq() throws PersonExeption {
        assertEquals("testName",authPerson.getNameAuthFromReq());
    }


    @Test
    public void getPasswordAuthFromReq() throws PersonExeption {
        assertEquals("testPassword",authPerson.getPasswordAuthFromReq());
    }

    @Test
    public void getTokenAuthFromReq() throws PersonExeption {
        assertEquals("testToken",authPerson.getTokenAuthFromReq());
    }

}