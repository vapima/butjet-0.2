package service;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PasswordHashTest {


    @Test
    public void verifyHash() {
        assertTrue(PasswordHash.verifyHash("TEST", PasswordHash.getHash("TEST")));
    }
}