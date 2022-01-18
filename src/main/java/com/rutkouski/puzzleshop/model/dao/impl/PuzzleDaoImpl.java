package com.rutkouski.puzzleshop.model.dao.impl;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.dao.PuzzleDao;
import com.rutkouski.puzzleshop.model.entity.Puzzle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class PuzzleDaoImpl implements PuzzleDao {
    static Logger logger = LogManager.getLogger();


    @Override
    public List<Puzzle> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Puzzle> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Puzzle entity) throws DaoException {
        return false;
    }

    @Override
    public int create(Puzzle entity) throws DaoException {
        return 0;
    }

    @Override
    public List<Puzzle> findAllByDifficultyLevel(int difficultyLevel) {
        return null;
    }

    @Override
    public List<Puzzle> findAllByRatingNotLessThan(int rating) {
        return null;
    }
}
