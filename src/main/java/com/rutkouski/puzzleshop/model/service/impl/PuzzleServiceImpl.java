package com.rutkouski.puzzleshop.model.service.impl;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.dao.impl.PuzzleDaoImpl;
import com.rutkouski.puzzleshop.model.entity.Puzzle;
import com.rutkouski.puzzleshop.model.service.PuzzleService;
import com.rutkouski.puzzleshop.validator.PuzzleValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

import static com.rutkouski.puzzleshop.controller.command.ParameterName.*;

public class PuzzleServiceImpl implements PuzzleService {
    static Logger logger = LogManager.getLogger();
    private static PuzzleServiceImpl instance;
    private final PuzzleDaoImpl puzzleDao = PuzzleDaoImpl.getInstance();

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
            Optional<Puzzle> puzzle;
            try {
                puzzle = puzzleDao.findById(id);
            } catch (DaoException e) {
                logger.error("Impossible to delete puzzle: ", e);
                throw new ServiceException("Impossible to delete puzzle: ", e);
            }
            puzzle.ifPresent(puzzles::add);
        }
        BigDecimal totalCost = new BigDecimal(0);
        for (Puzzle puzzle : puzzles) {
            totalCost = totalCost.add(puzzle.getPrice()
                            .multiply(BigDecimal.valueOf(items.get(puzzle.getId())))
                            .multiply(BigDecimal.valueOf(1 - customerDiscount / 100d))
                    , MathContext.DECIMAL32);
        }
        return totalCost;
    }

    @Override
    public List<Puzzle> findPuzzlesByDifficultyLevel(int difficultyLevel) throws ServiceException {
        try {
            return puzzleDao.findAllByDifficultyLevel(difficultyLevel);
        } catch (DaoException e) {
            logger.error("Impossible to find puzzles by difficulty level: ", e);
            throw new ServiceException("Impossible to find puzzles by difficulty level: ", e);
        }
    }

    @Override
    public boolean updatePuzzle(Map<String, String> formValues) throws ServiceException {
        boolean result = false;
        Optional<Puzzle> optionalPuzzle = formPuzzleValuesHandling(formValues);
        String puzzleId = formValues.get(PUZZLE_ID);

        try {
            if (optionalPuzzle.isPresent()) {
                Puzzle puzzleForUpdating = optionalPuzzle.get();
                puzzleForUpdating.setId(Integer.parseInt(puzzleId));
                puzzleDao.updatePuzzle(puzzleForUpdating);
                result = true;
            }
        } catch (DaoException e) {
            logger.error("Impossible to update puzzle: ", e);
            throw new ServiceException("Impossible to update puzzle: ", e);
        }
        return result;
    }

    @Override
    public boolean createPuzzle(Map<String, String> formValues) throws ServiceException {
        Optional<Puzzle> optionalPuzzle = formPuzzleValuesHandling(formValues);
        boolean result = false;
        try {
            if (optionalPuzzle.isPresent()) {
                puzzleDao.create(optionalPuzzle.get());
                result = true;
            }
        } catch (DaoException e) {
            logger.error("Impossible to create puzzle: ", e);
            throw new ServiceException("Impossible to create puzzle: ", e);
        }
        return result;
    }

    private Optional<Puzzle> formPuzzleValuesHandling(Map<String, String> formValues) {
        String name = formValues.get(NAME);
        String price = formValues.get(PRICE);
        String difficultyLevel = formValues.get(DIFFICULTY_LEVEL);
        String description = formValues.get(DESCRIPTION);
        String picturePath = formValues.get(PICTURE_PATH);

        boolean result = PuzzleValidator.isNameValid(name)
                && PuzzleValidator.isPriceValid(price)
                && PuzzleValidator.isDifficultyLevelValid(difficultyLevel)
                && PuzzleValidator.isDescriptionValid(description)
                && PuzzleValidator.isPicturePathValid(picturePath);
        System.out.println(result);
        Optional<Puzzle> optionalPuzzle = Optional.empty();
        if (result) {
            BigDecimal bigDecimalPrice = BigDecimal.valueOf(Double.parseDouble(price));
            int intDifficultLevel = Integer.parseInt(difficultyLevel);
            optionalPuzzle = Optional.of(new Puzzle(name, bigDecimalPrice, intDifficultLevel, description, picturePath));
        }
        return optionalPuzzle;
    }
}
