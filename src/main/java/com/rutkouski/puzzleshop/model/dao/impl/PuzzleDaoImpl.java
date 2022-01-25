package com.rutkouski.puzzleshop.model.dao.impl;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.dao.PuzzleDao;
import com.rutkouski.puzzleshop.model.entity.Puzzle;
import com.rutkouski.puzzleshop.model.mapper.RowMapper;
import com.rutkouski.puzzleshop.model.mapper.impl.PuzzleMapper;
import com.rutkouski.puzzleshop.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PuzzleDaoImpl implements PuzzleDao {
    static Logger logger = LogManager.getLogger();

    private static final String SQL_FIND_ALL =
            "SELECT id, name, price, difficulty_level, description, image FROM puzzles";
    private static final String SQL_FIND_BY_ID =
            "SELECT id, name, price, difficulty_level, description, image FROM puzzles WHERE puzzles.id=?";
    private static final String SQL_DELETE_BY_ID =
            "DELETE FROM puzzles WHERE id=?";
    private static final String SQL_INSERT_NEW_PUZZLE = """
            INSERT INTO puzzles (name, price, difficulty_level, description, image)
            VALUES(?, ?, ?, ?, ?)""";



    private RowMapper<Puzzle> mapper = new PuzzleMapper();

    @Override
    public List<Puzzle> findAll() throws DaoException {
        List<Puzzle> puzzles;
        try(Connection connection = CustomConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
        ResultSet resultSet = statement.executeQuery()) {
            puzzles = mapper.mapRows(resultSet);
            logger.debug("findAll method was completed successfully. {} puzzles were found", puzzles.size());
        } catch (SQLException e) {
            logger.error("SQL exception happened in findAll method: ", e);
            throw new DaoException("SQL exception happened in findAll method", e);
        }
        return puzzles;
    }

    @Override
    public Optional<Puzzle> findById(Integer id) throws DaoException {
        Optional<Puzzle> optionalPuzzle = Optional.empty();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setInt(FIRST_PARAM_INDEX, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalPuzzle = mapper.mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("SQL exception happened in findById method: ", e);
            throw new DaoException("SQL exception happened in findById method", e);
        }
        logger.debug("findById method was completed successfully. Puzzle with id={} {}", id,
                ((optionalPuzzle.isPresent()) ? " is found" : " doesn't exist"));
        return optionalPuzzle;
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setInt(FIRST_PARAM_INDEX, id);
            boolean result = statement.executeUpdate() == 1;
            logger.debug("deleteById method was completed successfully. Result: {}", result);
            return result;
        } catch (SQLException e) {
            logger.error("SQL exception happened in deleteById method: ", e);
            throw new DaoException("SQL exception happened in deleteById method", e);
        }
    }

    @Override
    public boolean delete(Puzzle puzzle) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setInt(FIRST_PARAM_INDEX, puzzle.getId());
            boolean result = statement.executeUpdate() == 1;
            logger.debug("delete(Puzzle puzzle) method was completed successfully. Result: {}", result);
            return result;
        } catch (SQLException e) {
            logger.error("SQL exception happened in delete(Puzzle puzzle) method: ", e);
            throw new DaoException("SQL exception happened in delete(Puzzle puzzle) method", e);
        }
    }

    @Override
    public int create(Puzzle puzzle) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_PUZZLE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(FIRST_PARAM_INDEX, puzzle.getName());
            statement.setBigDecimal(SECOND_PARAM_INDEX, puzzle.getPrice());
            statement.setInt(THIRD_PARAM_INDEX, puzzle.getDifficultyLevel());
            statement.setString(FOURTH_PARAM_INDEX, puzzle.getDescription());
            statement.setString(FIFTH_PARAM_INDEX, puzzle.getPicturePath());
            statement.executeUpdate();
            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                int puzzleId = 0;
                if (resultSet.next()) {
                    puzzleId = resultSet.getInt(FIRST_PARAM_INDEX);
                    logger.info("create(Puzzle puzzle) method was completed successfully. Puzzle with the id={} was created", puzzleId);
                }
                return puzzleId;
            }
        } catch (SQLException e) {
            logger.error("SQL exception happened in create(Puzzle puzzle) method: ", e);
            throw new DaoException("SQL exception happened in create(Puzzle puzzle) method", e);
        }
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
