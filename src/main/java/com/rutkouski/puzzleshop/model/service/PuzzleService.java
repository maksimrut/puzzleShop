package com.rutkouski.puzzleshop.model.service;

import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.Puzzle;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PuzzleService {

    List<Puzzle> findAllPuzzles() throws ServiceException;
    Optional<Puzzle> findPuzzleById(Integer id) throws ServiceException;
    boolean deletePuzzleById(Integer id) throws ServiceException;
    boolean deletePuzzle(Puzzle puzzle) throws ServiceException;
    BigDecimal calculatePuzzleSet(Map<Integer, Integer> items, int customerDiscount) throws ServiceException;


}
