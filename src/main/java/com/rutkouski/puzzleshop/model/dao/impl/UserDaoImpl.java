package com.rutkouski.puzzleshop.model.dao.impl;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.dao.UserDao;
import com.rutkouski.puzzleshop.model.entity.User;
import com.rutkouski.puzzleshop.model.mapper.RowMapper;
import com.rutkouski.puzzleshop.model.mapper.impl.UserMapper;
import com.rutkouski.puzzleshop.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *  The {@link UserDaoImpl} class provides access to
 *  the 'users', 'roles', 'user_statuses' database tables
 */

public class UserDaoImpl implements UserDao {
    static Logger logger = LogManager.getLogger();

    private static final String SQL_FIND_ALL = """
			SELECT users.id, login, password, email, first_name, phone, role, status
			FROM users
			JOIN roles ON role_id=roles.id
			JOIN user_statuses ON status_id=user_statuses.id""";
    private static final String SQL_FIND_BY_ID = """
			SELECT users.id, login, password, email, first_name, phone, role, status
			FROM users
			JOIN roles ON role_id=roles.id
			JOIN user_statuses ON status_id=user_statuses.id
			WHERE users.id=?""";
    private static final String SQL_FIND_ALL_ACTIVE_USERS = """
			SELECT users.id, login, password, email, first_name, phone, role, status
			FROM users
			JOIN roles ON role_id=roles.id
			JOIN user_statuses ON status_id=user_statuses.id
			WHERE user_statuses.status='active'""";
    private static final String SQL_FIND_USER_BY_EMAIL = """
			SELECT users.id, login, password, email, first_name, phone, role, status
			FROM users
			JOIN roles ON role_id=roles.id
			JOIN user_statuses ON status_id=user_statuses.id
			WHERE users.email=?""";
    private static final String SQL_FIND_USER_ID_BY_LOGIN =
            "SELECT users.id FROM users WHERE login=?";
    private static final String SQL_FIND_USER_ID_BY_EMAIL =
            "SELECT users.id FROM users WHERE email=?";
    private static final String SQL_UPDATE_USER_FIRST_NAME =
            "UPDATE users SET first_name=? WHERE users.id=?";
    private static final String SQL_UPDATE_USER_PHONE_NUMBER =
            "UPDATE users SET phone=? WHERE users.id=?";
    private static final String SQL_UPDATE_USER_PASSWORD =
            "UPDATE users SET password=? WHERE users.id=?";
    private static final String SQL_UPDATE_USER_EMAIL =
            "UPDATE users SET email=? WHERE users.id=?";
    private static final String SQL_DELETE_USER_BY_ID  =
            "DELETE FROM users WHERE users.id=?";
    private static final String SQL_INSERT_NEW_USER = """
			INSERT INTO users (login, password, email, first_name, phone, role_id, status_id)
			VALUES (?, ?, ?, ?, ?, ?, ?)""";
    private static final String SQL_FIND_USER_BY_LOGIN_AND_PASSWORD = """
			SELECT users.id, login, password, email, first_name, phone, role, status
			FROM users
			JOIN roles ON role_id=roles.id
			JOIN user_statuses ON status_id=user_statuses.id
			WHERE users.login=? AND users.password=?""";
    private static final String SQL_UPDATE_USER_STATUS =
            "UPDATE users SET status_id=? WHERE users.id=?";
    private static final String SQL_FIND_USER_BY_ID_AND_PASSWORD =
            "SELECT users.id FROM users WHERE users.id=? AND password=?";
    private static final String SQL_UPDATE_USER_ROLE =
            "UPDATE users SET role_id=? WHERE users.id=?";
    private static UserDaoImpl instance;
    private final RowMapper<User> mapper = new UserMapper();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            users = mapper.mapRows(resultSet);
            logger.debug("findAll method was completed successfully. {} users were found", users.size());
        } catch (SQLException e) {
            logger.error("SQL exception happened in findAll method: ", e);
            throw new DaoException("SQL exception happened in findAll method", e);
        }
        return users;
    }

    @Override
    public Optional<User> findById(Integer id) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setInt(FIRST_PARAM_INDEX, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalUser = mapper.mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("SQL exception happened in findById method: ", e);
            throw new DaoException("SQL exception happened in findById method", e);
        }
        logger.debug("findById method was completed successfully. User with id={} {}", id,
                ((optionalUser.isPresent()) ? " is found" : " doesn't exist"));
        return optionalUser;
    }

    @Override
    public List<User> findAllActiveUsers() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ACTIVE_USERS);
             ResultSet resultSet = statement.executeQuery()) {
            users = mapper.mapRows(resultSet);
        } catch (SQLException e) {
            logger.error("SQL exception happened in findAllActiveUsers method: ", e);
            throw new DaoException("SQL exception happened in findAllActiveUsers method", e);
        }
        logger.debug("findAllActiveUsers method was completed successfully. {} users were found", users.size());
        return users;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL)) {
            statement.setString(FIRST_PARAM_INDEX, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalUser = mapper.mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("SQL exception happened in findUserByEmail method: ", e);
            throw new DaoException("SQL exception happened in findUserByEmail method", e);
        }
        logger.debug("findUserByEmail method was completed successfully. User with email={} {}", email,
                ((optionalUser.isPresent()) ? " is found" : " doesn't exist"));
        return optionalUser;
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
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
    public boolean delete(User user) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            statement.setInt(FIRST_PARAM_INDEX, user.getId());
            boolean result = statement.executeUpdate() == 1;
            logger.debug("delete(User user) method was completed successfully. Result: {}", result);
            return result;
        } catch (SQLException e) {
            logger.error("SQL exception happened in delete(User user) method: ", e);
            throw new DaoException("SQL exception happened in delete(User user) method", e);
        }
    }

    @Override
    public User create(User user) throws DaoException { // TODO: 02.02.2022
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(FIRST_PARAM_INDEX, user.getLogin());
            statement.setString(SECOND_PARAM_INDEX, user.getPassword());
            statement.setString(THIRD_PARAM_INDEX, user.getEmail());
            statement.setString(FOURTH_PARAM_INDEX, user.getFirstName());
            statement.setString(FIFTH_PARAM_INDEX, user.getPhoneNumber());
            statement.setInt(SIXTH_PARAM_INDEX, user.getRole().ordinal() + 1);
            statement.setInt(SEVENTH_PARAM_INDEX, user.getStatus().ordinal() + 1);
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt(FIRST_PARAM_INDEX);
                    user.setId(userId);
                    logger.info("create(User user) method was completed successfully. User {} was created", user);
                }
                return user;
            }
        } catch (SQLException e) {
            logger.error("SQL exception happened in create(User user) method: ", e);
            throw new DaoException("SQL exception happened in create(User user) method", e);
        }
    }

    @Override
    public boolean updateUserFirstName(int userId, String firstName) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_FIRST_NAME)) {
            statement.setString(FIRST_PARAM_INDEX, firstName);
            statement.setInt(SECOND_PARAM_INDEX, userId);
            boolean result = statement.executeUpdate() == 1;
            logger.debug("The result of user id={} first name updating is: {}", userId, result);
            return result;
        } catch (SQLException e) {
            logger.error("SQL exception happened in updateUserFirstName method: ", e);
            throw new DaoException("SQL exception happened in updateUserFirstName method", e);
        }
    }

    @Override
    public boolean updateUserPhoneNumber(int userId, String phoneNumber) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_PHONE_NUMBER)) {
            statement.setString(FIRST_PARAM_INDEX, phoneNumber);
            statement.setInt(SECOND_PARAM_INDEX, userId);
            boolean result = statement.executeUpdate() == 1;
            logger.debug("The result of user id={} phone number updating is: {}", userId, result);
            return result;
        } catch (SQLException e) {
            logger.error("SQL exception happened in updateUserPhoneNumber method: ", e);
            throw new DaoException("SQL exception happened in updateUserPhoneNumber method", e);
        }
    }

    @Override
    public boolean updateUserPassword(int userId, String passwordHash) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_PASSWORD)) {
            statement.setString(FIRST_PARAM_INDEX, passwordHash);
            statement.setInt(SECOND_PARAM_INDEX, userId);
            boolean result = statement.executeUpdate() == 1;
            logger.debug("The result of user id={} password updating is: {}", userId, result);
            return result;
        } catch (SQLException e) {
            logger.error("SQL exception happened in updateUserPassword method: ", e);
            throw new DaoException("SQL exception happened in updateUserPassword method", e);
        }
    }

    @Override
    public boolean updateUserEmail(int userId, String email) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_EMAIL)) {
            statement.setString(FIRST_PARAM_INDEX, email);
            statement.setInt(SECOND_PARAM_INDEX, userId);
            boolean result = statement.executeUpdate() == 1;
            logger.debug("The result of user id={} email updating is: {}", userId, result);
            return result;
        } catch (SQLException e) {
            logger.error("SQL exception happened in updateUserEmail method: ", e);
            throw new DaoException("SQL exception happened in updateUserEmail method", e);
        }
    }

    @Override
    public boolean isLoginExist(String login) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_ID_BY_LOGIN)) {
            statement.setString(FIRST_PARAM_INDEX, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean result = resultSet.isBeforeFirst();
                logger.debug("isLoginExist method was completed successfully. Result: {}", result);
                return result;
            }
        } catch (SQLException e) {
            logger.error("SQL exception happened in isLoginExist method: ", e);
            throw new DaoException("SQL exception happened in isLoginExist method", e);
        }
    }

    @Override
    public boolean isEmailExist(String email) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_ID_BY_EMAIL)) {
            statement.setString(FIRST_PARAM_INDEX, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean result = resultSet.isBeforeFirst();
                logger.debug("isEmailExist method was completed successfully. Result: {}", result);
                return result;
            }
        } catch (SQLException e) {
            logger.error("SQL exception happened in isEmailExist method: ", e);
            throw new DaoException("SQL exception happened in isEmailExist method", e);
        }
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String passwordHash) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(FIRST_PARAM_INDEX, login);
            statement.setString(SECOND_PARAM_INDEX, passwordHash);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalUser = mapper.mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("SQL exception happened in findUserByLoginAndPassword method: ", e);
            throw new DaoException("SQL exception happened in findUserByLoginAndPassword method", e);
        }
        logger.debug("findUserByLoginAndPassword method was completed successfully. User {} {}", login,
                ((optionalUser.isPresent()) ? " was found" : " doesn't exist"));
        return optionalUser;
    }

    @Override
    public boolean updateUserStatus(int userId, User.Status status) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_STATUS)) {
            statement.setInt(FIRST_PARAM_INDEX, status.ordinal() + 1);
            statement.setInt(SECOND_PARAM_INDEX, userId);
            boolean result = statement.executeUpdate() == 1;
            logger.debug("The result of user id={} status updating is: {}", userId, result);
            return result;
        } catch (SQLException e) {
            logger.error("SQL exception happened in updateUserStatus method: ", e);
            throw new DaoException("SQL exception happened in updateUserStatus method", e);
        }
    }

    @Override
    public boolean isUserExist(int userId, String passwordHash) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID_AND_PASSWORD)) {
            statement.setInt(FIRST_PARAM_INDEX, userId);
            statement.setString(SECOND_PARAM_INDEX, passwordHash);
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean result = resultSet.isBeforeFirst();
                logger.debug("isUserExist method was completed successfully. Result: {}", result);
                return result;
            }
        } catch (SQLException e) {
            logger.error("SQL exception happened in isUserExist method: ", e);
            throw new DaoException("SQL exception happened in isUserExist method", e);
        }
    }

    @Override
    public boolean updateUserRole(int userId, User.Role role) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_ROLE)) {
            statement.setInt(FIRST_PARAM_INDEX, role.ordinal() + 1);
            statement.setInt(SECOND_PARAM_INDEX, userId);
            boolean result = statement.executeUpdate() == 1;
            logger.debug("The result of user id={} role updating is: {}", userId, result);
            return result;
        } catch (SQLException e) {
            logger.error("SQL exception happened in updateUserRole method: ", e);
            throw new DaoException("SQL exception happened in updateUserRole method", e);
        }
    }
}
