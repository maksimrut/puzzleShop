package com.rutkouski.puzzleshop.model.dao.impl;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.entity.Puzzle;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class PuzzleDaoImplTest {
    PuzzleDaoImpl puzzleDao;

    @BeforeClass
    public void init() {
        puzzleDao = PuzzleDaoImpl.getInstance();
    }

    @Test
    public void testFindAll() throws DaoException {
        List<Puzzle> actual = puzzleDao.findAll();
        assertFalse(actual.isEmpty());
    }

    @Test
    public void testFindById() throws DaoException {
        int puzzleId = 5;
        Optional<Puzzle> actual = puzzleDao.findById(puzzleId);
        assertTrue(actual.isPresent());
    }

    @Test
    public void testFindAllByDifficultyLevel() throws DaoException {
        int diffucultyLevel = 2;
        List<Puzzle> actual = puzzleDao.findAllByDifficultyLevel(diffucultyLevel);
        assertFalse(actual.isEmpty());
    }
}
