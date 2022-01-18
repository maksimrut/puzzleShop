package com.rutkouski.puzzleshop.validator;

public class LocaleValidator {
    private static final String ENGLISH_LOCALE = "en";
    private static final String RUSSIAN_LOCALE = "ru";

    private LocaleValidator() {
    }

    public static boolean validate(String locale) {
        return locale.equals(ENGLISH_LOCALE) || locale.equals(RUSSIAN_LOCALE);
    }
}
