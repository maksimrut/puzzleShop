package com.rutkouski.puzzleshop.model.service;

import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Maksim Rutkouski
 *
 * The interface User service
 */
public interface UserService {

    /**
     * Finds optional user by login and password .
     *
     * @param login    the user login
     * @param password the user password
     * @return the optional User
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws ServiceException;

    /**
     * @return the list of all users
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    List<User> findAllUsers() throws ServiceException;

    /**
     * Finds user by id
     *
     * @param id the user id
     * @return the optional User
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    Optional<User> findUserById(int id) throws ServiceException;

    /**
     * @return the list of all active users
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    List<User> findAllActiveUsers() throws ServiceException;

    /**
     * Finds user by email
     *
     * @param email the user email
     * @return the optional User
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    Optional<User> findUserByEmail(String email) throws ServiceException;

    /**
     * Deletes user by id
     *
     * @param id the user id
     * @return the boolean
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    boolean deleteUserById(int id) throws ServiceException;

    /**
     * Deletes user
     *
     * @param user the user for deleting
     * @return the boolean
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    boolean deleteUser(User user) throws ServiceException;

    /**
     * Updates user first name by id
     *
     * @param userId    the user id
     * @param firstName the first name to update
     * @return the boolean
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    boolean updateUserFirstNameById(int userId, String firstName) throws ServiceException;

    /**
     * Updates user phone number by id
     *
     * @param userId      the user id
     * @param phoneNumber the phone number to update
     * @return the boolean
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    boolean updateUserPhoneNumberById(int userId, String phoneNumber) throws ServiceException;

    /**
     * Updates user password by id
     *
     * @param userId     the user id
     * @param formValues the form values of current and new passwords
     * @return the boolean
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    boolean updateUserPasswordById(int userId, Map<String, String> formValues) throws ServiceException;

    /**
     * Updates user email by id
     *
     * @param userId    the user id
     * @param formValue the form value of email to update
     * @return the boolean
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    boolean updateUserEmailById(int userId, Map<String, String> formValue) throws ServiceException;

    /**
     * Updates user status by id
     *
     * @param userId the user id
     * @param status the status to update
     * @return the boolean
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    boolean updateUserStatusById(int userId, User.Status status) throws ServiceException;

    /**
     * Changes user role
     *
     * @param userId the user id
     * @param role   the user role to update
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateUserRole(int userId, User.Role role) throws ServiceException;
}
