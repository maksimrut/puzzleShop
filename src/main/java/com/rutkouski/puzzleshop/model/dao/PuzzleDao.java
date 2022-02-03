package com.rutkouski.puzzleshop.model.dao;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.entity.Puzzle;

import java.util.List;

/**
 * @author Maksim Rutkouski
 * <p>
 * The interface Puzzle dao. Extends {@link BaseDao}
 */
public interface PuzzleDao extends BaseDao<Integer, Puzzle> {

    /**
     * Finds all puzzles by difficulty level
     *
     * @param difficultyLevel the puzzle's difficulty level
     * @return the list of all puzzle with chosen difficulty level
     * @throws DaoException happens if the request to database could not be handled
     */
    List<Puzzle> findAllByDifficultyLevel(int difficultyLevel) throws DaoException;

    /**
     * Updates puzzle
     *
     * @param puzzle the puzzle to update
     * @return the boolean result
     * @throws DaoException happens if the request to database could not be handled
     */
    boolean updatePuzzle(Puzzle puzzle) throws DaoException;

}
