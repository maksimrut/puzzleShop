package com.rutkouski.puzzleshop.validator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PuzzleValidatorTest {
    private String diffLevel;
    private String diffLevelIncorrect;
    private String picturePath;
    private String picturePathIncorrect;
    private String description;
    private String name;
    private String price;
    private String priceIncorrect;

    @BeforeMethod
    public void setUp() {
        diffLevel = "2";
        diffLevelIncorrect = "14";
        picturePath = "dfr/pic.jpg";
        picturePathIncorrect = "d<>wer.jpg";
        description = "Описание головоломки!";
        name = "Interesting puzzle";
        price = "26.34";
        priceIncorrect = "0,36";
    }

    @Test
    public void testIsDifficultyLevelValid() {
        boolean actual = PuzzleValidator.isDifficultyLevelValid(diffLevel);
        assertTrue(actual);
    }

    @Test
    public void testIsDifficultyLevelValidIncorrectValue() {
        boolean actual = !PuzzleValidator.isDifficultyLevelValid(diffLevelIncorrect);
        assertTrue(actual);
    }

    @Test
    public void testIsNameValid() {
        boolean actual = PuzzleValidator.isNameValid(name);
        assertTrue(actual);
    }

    @Test
    public void testIsPriceValid() {
        boolean actual = PuzzleValidator.isPriceValid(price);
        assertTrue(actual);
    }

    @Test
    public void testIsPriceValidIncorrectValue() {
        boolean actual = !PuzzleValidator.isPriceValid(priceIncorrect);
        assertTrue(actual);
    }

    @Test
    public void testIsDescriptionValid() {
        boolean actual = PuzzleValidator.isDescriptionValid(description);
        assertTrue(actual);
    }

    @Test
    public void testIsPicturePathValid() {
        boolean actual = PuzzleValidator.isPicturePathValid(picturePath);
        assertTrue(actual);
    }

    @Test
    public void testIsPicturePathValidIncorrectValue() {
        boolean actual = !PuzzleValidator.isPicturePathValid(picturePathIncorrect);
        assertTrue(actual);
    }
}