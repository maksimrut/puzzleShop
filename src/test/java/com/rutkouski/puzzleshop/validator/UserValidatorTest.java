package com.rutkouski.puzzleshop.validator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserValidatorTest {
    private String login;
    private String loginIncorrect;
    private String password;
    private String passwordIncorrect;
    private String name;
    private String email;
    private String emailIncorrect;
    private String phoneNumber;
    private String phoneNumberIncorrect;

    @BeforeMethod
    public void setUp() {
        login = "hello";
        loginIncorrect = "hello <>";
        password = "Password1";
        passwordIncorrect = "incorrect";
        name = "Boris";
        email = "boris@mail.ru";
        emailIncorrect = "w@test";
        phoneNumber = "251111111";
        phoneNumberIncorrect = "27111111100";
    }

    @Test
    public void testIsLoginValid() {
        boolean actual = UserValidator.isLoginValid(login);
        assertTrue(actual);
    }

    @Test
    public void testIsLoginValidIncorrectValue() {
        boolean actual = !UserValidator.isLoginValid(loginIncorrect);
        assertTrue(actual);
    }

    @Test
    public void testIsPasswordValid() {
        boolean actual = UserValidator.isPasswordValid(password);
        assertTrue(actual);
    }

    @Test
    public void testIsPasswordValidIncorrectValue() {
        boolean actual = !UserValidator.isPasswordValid(passwordIncorrect);
        assertTrue(actual);
    }

    @Test
    public void testIsNameValid() {
        boolean actual = UserValidator.isNameValid(name);
        assertTrue(actual);
    }

    @Test
    public void testIsEmailValid() {
        boolean actual = UserValidator.isEmailValid(email);
        assertTrue(actual);
    }

    @Test
    public void testIsEmailValidIncorrectValue() {
        boolean actual = !UserValidator.isEmailValid(emailIncorrect);
        assertTrue(actual);
    }

    @Test
    public void testIsPhoneNumberValid() {
        boolean actual = UserValidator.isPhoneNumberValid(phoneNumber);
        assertTrue(actual);
    }

    @Test
    public void testIsPhoneNumberValidIncorrectValue() {
        boolean actual = !UserValidator.isPhoneNumberValid(phoneNumberIncorrect);
        assertTrue(actual);
    }
}