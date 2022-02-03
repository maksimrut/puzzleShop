package com.rutkouski.puzzleshop.validator;

/**
 * @author Maksim Rutkouski
 *
 * The type Locale validator.
 */
public class LocaleValidator {
    private static final String ENGLISH_LOCALE = "en";
    private static final String RUSSIAN_LOCALE = "ru";

    private LocaleValidator() {
    }

    /**
     * Validate boolean.
     *
     * @param locale compared to the existing ones
     * @return the boolean result
     */
    public static boolean validate(String locale) {
        return locale.equals(ENGLISH_LOCALE) || locale.equals(RUSSIAN_LOCALE);
    }
}
