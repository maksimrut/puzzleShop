package com.rutkouski.puzzleshop.model.service;

import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.Puzzle;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Maksim Rutkouski
 * <p>
 * The interface Puzzle service.
 */
public interface PuzzleService {

    /**
     * @return the all puzzles list
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    List<Puzzle> findAllPuzzles() throws ServiceException;

    /**
     * Finds puzzle by id
     *
     * @param id the puzzle id
     * @return the optional puzzle
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    Optional<Puzzle> findPuzzleById(Integer id) throws ServiceException;

    /**
     * Deletes puzzle by id
     *
     * @param id the puzzle id
     * @return the boolean
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    boolean deletePuzzleById(Integer id) throws ServiceException;

    /**
     * Deletes puzzle
     *
     * @param puzzle the puzzle for deleting
     * @return the boolean
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    boolean deletePuzzle(Puzzle puzzle) throws ServiceException;

    /**
     * Calculates total price of puzzle set according to the customer current discount
     *
     * @param items            the items
     * @param customerDiscount the customer discount
     * @return the big decimal total price of puzzle set
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    BigDecimal calculatePuzzleSet(Map<Integer, Integer> items, int customerDiscount) throws ServiceException;

    /**
     * Finds all puzzles by difficulty level
     *
     * @param difficultyLevel the puzzle difficulty level
     * @return the list of puzzles
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    List<Puzzle> findPuzzlesByDifficultyLevel(int difficultyLevel) throws ServiceException;

    /**
     * Validates and updates puzzle data
     *
     * @param formValues the form values
     * @return the boolean
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    boolean updatePuzzle(Map<String, String> formValues) throws ServiceException;

    /**
     * Creates new puzzle
     *
     * @param formValues the form values
     * @return the boolean
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    boolean createPuzzle(Map<String, String> formValues) throws ServiceException;
}
