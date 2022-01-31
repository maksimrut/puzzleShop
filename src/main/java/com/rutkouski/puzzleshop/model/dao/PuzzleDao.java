package com.rutkouski.puzzleshop.model.dao;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.entity.Puzzle;

import java.util.List;

public interface PuzzleDao extends BaseDao<Integer, Puzzle> {

    List<Puzzle> findAllByDifficultyLevel(int difficultyLevel) throws DaoException;
    boolean updatePuzzle(Puzzle puzzle) throws DaoException;

}
