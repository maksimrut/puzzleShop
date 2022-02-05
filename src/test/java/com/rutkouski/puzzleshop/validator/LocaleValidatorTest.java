package com.rutkouski.puzzleshop.validator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LocaleValidatorTest {
    private String locale;
    private String localeIncorrect;

    @BeforeMethod
    public void setUp() {
        locale = "en";
        localeIncorrect = "hr";
    }

    @Test
    public void testValidate() {
        boolean actual = LocaleValidator.validate(locale);
        assertTrue(actual);
    }

    @Test
    public void testValidateIncorrectValue() {
        boolean actual = !LocaleValidator.validate(localeIncorrect);
        assertTrue(actual);
    }
}
