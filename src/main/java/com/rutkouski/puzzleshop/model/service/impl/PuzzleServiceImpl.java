package com.rutkouski.puzzleshop.model.service.impl;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.dao.impl.PuzzleDaoImpl;
import com.rutkouski.puzzleshop.model.entity.Puzzle;
import com.rutkouski.puzzleshop.model.service.PuzzleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class PuzzleServiceImpl implements PuzzleService {
    static Logger logger = LogManager.getLogger();
    private static PuzzleServiceImpl instance;
    private final PuzzleDaoImpl puzzleDao = new PuzzleDaoImpl();

    private PuzzleServiceImpl() {
    }

    public static PuzzleServiceImpl getInstance() {
        if (instance == null) {
            instance = new PuzzleServiceImpl();
        }
        return instance;
    }


    @Override
    public List<Puzzle> findAllPuzzles() throws ServiceException {
        try {
            return puzzleDao.findAll();
        } catch (DaoException e) {
            logger.error("Impossible to find all puzzles: ", e);
            throw new ServiceException("Impossible to find all puzzles: ", e);
        }
    }

    @Override
    public Optional<Puzzle> findPuzzleById(Integer id) throws ServiceException {
        try {
            return puzzleDao.findById(id);
        } catch (DaoException e) {
            logger.error("Impossible to find user by id: ", e);
            throw new ServiceException("Impossible to find user by id: ", e);
        }
    }

    @Override
    public boolean deletePuzzleById(Integer id) throws ServiceException {
        try {
            return puzzleDao.deleteById(id);
        } catch (DaoException e) {
            logger.error("Impossible to delete puzzle by id: ", e);
            throw new ServiceException("Impossible to delete puzzle by id: ", e);
        }
    }

    @Override
    public boolean deletePuzzle(Puzzle puzzle) throws ServiceException {
        try {
            return puzzleDao.delete(puzzle);
        } catch (DaoException e) {
            logger.error("Impossible to delete puzzle: ", e);
            throw new ServiceException("Impossible to delete puzzle: ", e);
        }
    }

    @Override
    public BigDecimal calculatePuzzleSet(Map<Integer, Integer> items, int customerDiscount) throws ServiceException {
        Set<Integer> idSet = items.keySet();
        List<Puzzle> puzzles = new ArrayList<>();

        for (Integer id : idSet) {
            Optional<Puzzle> puzzle = null;
            try {
                puzzle = puzzleDao.findById(id);
            } catch (DaoException e) {
                logger.error("Impossible to delete puzzle: ", e);
                throw new ServiceException("Impossible to delete puzzle: ", e);
            }
            if (puzzle.isPresent()) {
                puzzles.add(puzzle.get());
            }
        }
        BigDecimal totalCost = new BigDecimal(0);
        for (Puzzle puzzle : puzzles) {
            totalCost = totalCost.add(puzzle.getPrice()
                            .multiply(BigDecimal.valueOf(items.get(puzzle.getId())))
                            .multiply(BigDecimal.valueOf(1d - customerDiscount / 100))
                    , MathContext.DECIMAL32);
        }
        return totalCost;
    }
}
