package com.rutkouski.puzzleshop.validator;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.dao.impl.UserDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.PASSWORD_MISMATCH;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.*;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.EMAIL;

public class UserValidator {
    static Logger logger = LogManager.getLogger();
    private static final String LOGIN_REGEX = "[a-zA-Z]\\w{3,15}";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,50}$";
    private static final String NAME_REGEX = "[A-Za-zА-Яа-я-]{2,40}";
    private static final String EMAIL_REGEX = "^([A-Za-z0-9_-]+\\.)*[A-Za-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String EMAIL_SYMBOL_NUMBER_REGEX = ".{8,80}";
    private static final String PHONE_NUMBER_REGEX = "(25|29|33|44)\\d{7}";

    private UserValidator() {
    }

    public static boolean isLoginValid(String login) {
        return login.matches(LOGIN_REGEX);
    }

    public static boolean isPasswordValid(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    public static boolean isNameValid(String name) {
        return name.matches(NAME_REGEX);
    }

    public static boolean isEmailValid(String email) {
        return email.matches(EMAIL_SYMBOL_NUMBER_REGEX) && email.matches(EMAIL_REGEX);
    }

    public static boolean isPhoneNumberValid(String name) {
        return name.matches(PHONE_NUMBER_REGEX);
    }
}
