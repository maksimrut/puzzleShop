package com.rutkouski.puzzleshop.validator;

/**
 * @author Maksim Rutkouski
 *
 * The type User validator.
 */
public class UserValidator {
    private static final String LOGIN_REGEX = "^[a-zA-Z]\\w{3,15}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,50}$";
    private static final String NAME_REGEX = "[A-Za-zА-Яа-я-]{2,40}$";
    private static final String EMAIL_REGEX = "^([A-Za-z0-9_-]+\\.)*[A-Za-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String EMAIL_SYMBOL_NUMBER_REGEX = ".{8,80}";
    private static final String PHONE_NUMBER_REGEX = "^(25|29|33|44)\\d{7}$";

    private UserValidator() {
    }

    /**
     * Check if login valid
     *
     * @param login compared to the regex
     * @return the boolean
     */
    public static boolean isLoginValid(String login) {
        return login.matches(LOGIN_REGEX);
    }

    /**
     * Check if password valid
     *
     * @param password compared to the regex
     * @return the boolean
     */
    public static boolean isPasswordValid(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    /**
     * Check if user name valid
     *
     * @param name compared to the regex
     * @return the boolean
     */
    public static boolean isNameValid(String name) {
        return name.matches(NAME_REGEX);
    }

    /**
     * Check if email valid
     *
     * @param email compared to the regex
     * @return the boolean
     */
    public static boolean isEmailValid(String email) {
        return email.matches(EMAIL_SYMBOL_NUMBER_REGEX) && email.matches(EMAIL_REGEX);
    }

    /**
     * Check if phone number valid
     *
     * @param name compared to the regex
     * @return the boolean
     */
    public static boolean isPhoneNumberValid(String name) {
        return name.matches(PHONE_NUMBER_REGEX);
    }
}
