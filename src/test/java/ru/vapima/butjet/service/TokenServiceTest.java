package ru.vapima.butjet.service;

import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.model.Person;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TokenServiceTest {

    @Test
    public void create() {
        TokenService tokenService = new TokenService();
        assertNotNull(tokenService.create(new Person(0, "test", "test", "test")));
    }

    @Test
    public void decode() throws PersonExeption {
        TokenService tokenService = new TokenService();
        Integer i = 0;
        assertEquals(i, tokenService.decode(tokenService.create(new Person(i, "test", "test", "test"))));
    }
}