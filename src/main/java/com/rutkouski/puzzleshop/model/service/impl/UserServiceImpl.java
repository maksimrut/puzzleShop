package com.rutkouski.puzzleshop.model.service.impl;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.dao.impl.UserDaoImpl;
import com.rutkouski.puzzleshop.model.entity.User;
import com.rutkouski.puzzleshop.model.service.UserService;
import com.rutkouski.puzzleshop.util.PasswordEncryptor;
import com.rutkouski.puzzleshop.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.*;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.*;

/**
 * The {@link UserServiceImpl} class provides logic for future access to
 * users table in the database
 */
public class UserServiceImpl implements UserService {
    static Logger logger = LogManager.getLogger();
    private static UserServiceImpl instance;
    private final UserDaoImpl userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws ServiceException {
        Optional<User> optionalUser = Optional.empty();
        try {
            if (UserValidator.isLoginValid(login) && UserValidator.isPasswordValid(password)) {
                String encryptedPassword = PasswordEncryptor.encrypt(password);
                optionalUser = userDao.findUserByLoginAndPassword(login, encryptedPassword);
            }
            return optionalUser;
        } catch (DaoException e) {
            logger.error("Impossible to find user by login and password ", e);
            throw new ServiceException("Impossible to find user by login and password ", e);
        }
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            logger.error("Impossible to find all users ", e);
            throw new ServiceException("Impossible to find all users ", e);
        }
    }

    @Override
    public Optional<User> findUserById(int id) throws ServiceException {
        try {
            return userDao.findById(id);
        } catch (DaoException e) {
            logger.error("Impossible to find user by id ", e);
            throw new ServiceException("Impossible to find user by id ", e);
        }
    }

    @Override
    public List<User> findAllActiveUsers() throws ServiceException {
        try {
            return userDao.findAllActiveUsers();
        } catch (DaoException e) {
            logger.error("Impossible to find all active users ", e);
            throw new ServiceException("Impossible to find all active users ", e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws ServiceException {
        try {
            return userDao.findUserByEmail(email);
        } catch (DaoException e) {
            logger.error("Impossible to find user by email ", e);
            throw new ServiceException("Impossible to find user by email ", e);
        }
    }

    @Override
    public boolean deleteUserById(int id) throws ServiceException {
        try {
            return userDao.deleteById(id);
        } catch (DaoException e) {
            logger.error("Impossible to delete user by id ", e);
            throw new ServiceException("Impossible to delete user by id ", e);
        }
    }

    @Override
    public boolean deleteUser(User user) throws ServiceException {
        try {
            return userDao.delete(user);
        } catch (DaoException e) {
            logger.error("Impossible to delete user ", e);
            throw new ServiceException("Impossible to delete user ", e);
        }
    }

    @Override
    public boolean updateUserFirstNameById(int userId, String firstName) throws ServiceException {
        try {
            return UserValidator.isNameValid(firstName) && userDao.updateUserFirstName(userId, firstName);
        } catch (DaoException e) {
            logger.error("Impossible to update user's first name ", e);
            throw new ServiceException("Impossible to update user's first name ", e);
        }
    }

    @Override
    public boolean updateUserPhoneNumberById(int userId, String phoneNumber) throws ServiceException {
        try {
            return UserValidator.isPhoneNumberValid(phoneNumber) && userDao.updateUserPhoneNumber(userId, phoneNumber);
        } catch (DaoException e) {
            logger.error("Impossible to update user's phone number ", e);
            throw new ServiceException("Impossible to update user's phone number ", e);
        }
    }

    @Override
    public boolean updateUserPasswordById(int userId, Map<String, String> formValues) throws ServiceException {
        String oldPassword = formValues.get(OLD_PASSWORD);
        try {
            boolean result = UserValidator.isPasswordValid(oldPassword)
                    && userDao.isUserExist(userId, PasswordEncryptor.encrypt(oldPassword));
            if (result) {
                String newPassword = formValues.get(NEW_PASSWORD);
                String confirmPassword = formValues.get(CONFIRM_PASSWORD);
                String passwordCheckResult = UserValidator.isPasswordValid(newPassword)
                        ? (newPassword.equals(confirmPassword) ? TRUE : PASSWORD_MISMATCH)
                        : INVALID_PASSWORD;
                result = Boolean.parseBoolean(passwordCheckResult);

                if (result) {
                    String passwordHash = PasswordEncryptor.encrypt(newPassword);
                    result = userDao.updateUserPassword(userId, passwordHash);
                } else {
                    formValues.clear();
                    formValues.put(NEW_PASSWORD, passwordCheckResult);
                    logger.debug("Invalid or mismatching password was entered");
                }
            } else {
                formValues.clear();
                formValues.put(OLD_PASSWORD, INCORRECT_PASSWORD);
                logger.debug("Incorrect password was entered");
            }
            return result;
        } catch (DaoException e) {
            logger.error("Impossible to update user's password ", e);
            throw new ServiceException("Impossible to update user's password ", e);
        }
    }

    @Override
    public boolean updateUserEmailById(int userId, Map<String, String> formValue) throws ServiceException {
        String email = formValue.get(EMAIL);
        try {
            String emailCheckResult = UserValidator.isEmailValid(email)
                    ? (!userDao.isEmailExist(email) ? TRUE : NOT_UNIQUE_EMAIL_RESULT)
                    : INVALID_EMAIL_RESULT;
            boolean result = Boolean.parseBoolean(emailCheckResult);
            if (result) {
                result = userDao.updateUserEmail(userId, email);
            } else {
                formValue.replace(EMAIL, emailCheckResult);
            }
            return result;
        } catch (DaoException e) {
            logger.error("Impossible to update user's email ", e);
            throw new ServiceException("Impossible to update user's email ", e);
        }
    }

    @Override
    public boolean updateUserStatusById(int userId, User.Status status) throws ServiceException {
        try {
            return userDao.updateUserStatus(userId, status);
        } catch (DaoException e) {
            logger.error("Impossible to update user's status ", e);
            throw new ServiceException("Impossible to update user's status ", e);
        }
    }

    @Override
    public boolean updateUserRole(int userId, User.Role role) throws ServiceException {
        try {
            return userDao.updateUserRole(userId, role);
        } catch (DaoException e) {
            logger.error("Impossible to update user's role ", e);
            throw new ServiceException("Impossible to update user's role ", e);
        }
    }
}
