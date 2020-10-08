package ru.vapima.butjet.service;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PasswordHashTest {


    @Test
    public void verifyHash() {
        assertTrue(new PasswordHash().verifyHash("TEST", new PasswordHash().getHash("TEST")));
    }
}