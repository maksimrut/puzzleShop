package com.rutkouski.puzzleshop.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PasswordEncryptorTest {

    @Test
    public void testEncrypt() {
        String password = "test";
        String actual = PasswordEncryptor.encrypt(password);
        String expected = "098f6bcd4621d373cade4e832627b4f6";
        assertEquals(actual, expected);
    }
}