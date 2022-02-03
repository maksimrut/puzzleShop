package com.rutkouski.puzzleshop.model.dao;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Maksim Rutkouski
 * <p>
 * The interface User dao. Extends {@link BaseDao}
 */
public interface UserDao extends BaseDao<Integer, User> {
    /**
     * Finds all active users list.
     *
     * @return the list of all active users
     * @throws DaoException happens if the request to database could not be handled
     */
    List<User> findAllActiveUsers() throws DaoException;

    /**
     * Finds user by email
     *
     * @param email the user's email
     * @return the optional user
     * @throws DaoException happens if the request to database could not be handled
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Finds user by login and encrypted password
     *
     * @param login        the user's login
     * @param passwordHash the user's encrypted password
     * @return the optional user
     * @throws DaoException happens if the request to database could not be handled
     */
    Optional<User> findUserByLoginAndPassword(String login, String passwordHash) throws DaoException;

    /**
     * Updates user first name
     *
     * @param userId    the user's id
     * @param firstName the user's first name to update
     * @return the boolean result
     * @throws DaoException happens if the request to database could not be handled
     */
    boolean updateUserFirstName(int userId, String firstName) throws DaoException;

    /**
     * Updates user phone number
     *
     * @param userId      the user's id
     * @param phoneNumber the user's phone number to update
     * @return the boolean result
     * @throws DaoException happens if the request to database could not be handled
     */
    boolean updateUserPhoneNumber(int userId, String phoneNumber) throws DaoException;

    /**
     * Updates user password
     *
     * @param userId       the user's id
     * @param passwordHash the new user's encrypted password to update
     * @return the boolean result
     * @throws DaoException happens if the request to database could not be handled
     */
    boolean updateUserPassword(int userId, String passwordHash) throws DaoException;

    /**
     * Updates user email
     *
     * @param userId the user's id
     * @param email  the user's email to update
     * @return the boolean result
     * @throws DaoException happens if the request to database could not be handled
     */
    boolean updateUserEmail(int userId, String email) throws DaoException;

    /**
     * Updates user status
     *
     * @param userId the user's id
     * @param status the user's status to update
     * @return the boolean result
     * @throws DaoException happens if the request to database could not be handled
     */
    boolean updateUserStatus(int userId, User.Status status) throws DaoException;

    /**
     * Checks if login exist
     *
     * @param login the user's login
     * @return the boolean result
     * @throws DaoException happens if the request to database could not be handled
     */
    boolean isLoginExist(String login) throws DaoException;

    /**
     * Checks if email exist
     *
     * @param email the user's email
     * @return the boolean result
     * @throws DaoException happens if the request to database could not be handled
     */
    boolean isEmailExist(String email) throws DaoException;

    /**
     * Checks if user exist
     *
     * @param userId       the user's id
     * @param passwordHash the user's encrypted password
     * @return the boolean result
     * @throws DaoException happens if the request to database could not be handled
     */
    boolean isUserExist(int userId, String passwordHash) throws DaoException;

    /**
     * Updates user role
     *
     * @param userId the user's id
     * @param role   the user's role
     * @return the boolean result
     * @throws DaoException happens if the request to database could not be handled
     */
    boolean updateUserRole(int userId, User.Role role) throws DaoException;

    /**
     * Finds user's status by id
     *
     * @param userId the user's id
     * @return the optional of User.Status
     * @throws DaoException happens if the request to database could not be handled
     */
    Optional<User.Status> findUserStatusById(int userId) throws DaoException;
}
