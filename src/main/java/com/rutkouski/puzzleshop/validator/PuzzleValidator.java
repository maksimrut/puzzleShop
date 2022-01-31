package com.rutkouski.puzzleshop.validator;

import java.util.Map;

import static com.rutkouski.puzzleshop.controller.command.ParameterName.*;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.PICTURE_PATH;

public class PuzzleValidator {
    private static final String DESCRIPTION_REGEX = "[^<>]+";
    private static final String PICTURE_PATH_REGEX = "[^<>]{0,150}";
    private static final String NAME_REGEX = "[^<>]{1,40}";
    private static final String DIFFICULTY_LEVEL = "\\d";
    private static final String PRICE_REGEX = "^\\d{1,3}(\\.\\d{1,2})?$";

    private PuzzleValidator() {
    }

    public static boolean isDifficultyLevelValid(String difficultyLevel) {
        return difficultyLevel.matches(DIFFICULTY_LEVEL);
    }

    public static boolean isNameValid(String name) {
        return name.matches(NAME_REGEX);
    }

    public static boolean isPriceValid(String price) {
        return price.matches(PRICE_REGEX);
    }

    public static boolean isDescriptionValid(String description) {
        return description.matches(DESCRIPTION_REGEX);
    }

    public static boolean isPicturePathValid(String picturePath) {
        return picturePath.matches(PICTURE_PATH_REGEX);
    }

}
