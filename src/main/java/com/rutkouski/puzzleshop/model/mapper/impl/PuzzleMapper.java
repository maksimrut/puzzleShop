package com.rutkouski.puzzleshop.model.mapper.impl;

import com.rutkouski.puzzleshop.model.entity.Puzzle;
import com.rutkouski.puzzleshop.model.mapper.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.rutkouski.puzzleshop.model.mapper.ColumnName.*;

public class PuzzleMapper implements RowMapper<Puzzle> {

    @Override
    public Optional<Puzzle> mapRow(ResultSet resultSet) {

        Puzzle puzzle = new Puzzle();
        try {
            puzzle.setId(resultSet.getInt(PUZZLE_ID));
            puzzle.setName(resultSet.getString(PUZZLE_NAME));
            puzzle.setDifficultyLevel(resultSet.getInt(DIFFICULTY_LEVEL));
            puzzle.setDescription(resultSet.getString(DESCRIPTION));
            puzzle.setPicturePath(resultSet.getString(IMAGE));
            double price = Double.parseDouble(resultSet.getString(PRICE));
            puzzle.setPrice(BigDecimal.valueOf(price));
            return Optional.of(puzzle);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
