package com.rutkouski.puzzleshop.validator;

/**
 * @author Maksim Rutkouski
 *
 * The type Puzzle validator.
 */
public class PuzzleValidator {
    private static final String DESCRIPTION_REGEX = "[^<>]+";
    private static final String PICTURE_PATH_REGEX = "[^<>]{0,150}";
    private static final String NAME_REGEX = "[^<>]{1,40}";
    private static final String DIFFICULTY_LEVEL = "\\d";
    private static final String PRICE_REGEX = "^\\d{1,3}(\\.\\d{1,2})?$";

    private PuzzleValidator() {
    }

    /**
     * Check if difficulty level valid
     *
     * @param difficultyLevel must be a one digit String
     * @return the boolean
     */
    public static boolean isDifficultyLevelValid(String difficultyLevel) {
        return difficultyLevel.matches(DIFFICULTY_LEVEL);
    }

    /**
     * Check if puzzle name valid
     *
     * @param name compared to the regex
     * @return the boolean
     */
    public static boolean isNameValid(String name) {
        return name.matches(NAME_REGEX);
    }

    /**
     * Check if price valid
     *
     * @param price the price
     * @return the boolean
     */
    public static boolean isPriceValid(String price) {
        return price.matches(PRICE_REGEX);
    }

    /**
     * Check if description valid
     *
     * @param description compared to the regex
     * @return the boolean
     */
    public static boolean isDescriptionValid(String description) {
        return description.matches(DESCRIPTION_REGEX);
    }

    /**
     * Check if picture path valid
     *
     * @param picturePath compared to the regex
     * @return the boolean
     */
    public static boolean isPicturePathValid(String picturePath) {
        return picturePath.matches(PICTURE_PATH_REGEX);
    }

}
