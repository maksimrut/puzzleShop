package com.rutkouski.puzzleshop.model.dao;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<Integer, User> {
    List<User> findAllActiveUsers() throws DaoException;
    Optional<User> findUserByEmail(String email) throws DaoException;
    Optional<User> findUserByLoginAndPassword(String login, String passwordHash) throws DaoException;
    boolean updateUserFirstName(int userId, String firstName) throws DaoException;
    boolean updateUserPhoneNumber(int userId, String phoneNumber) throws DaoException;
    boolean updateUserPassword(int userId, String passwordHash) throws DaoException;
    boolean updateUserEmail(int userId, String email) throws DaoException;
    boolean updateUserStatus(int userId, User.Status status) throws DaoException;
    boolean isLoginExist(String login) throws DaoException;
    boolean isEmailExist(String email) throws DaoException;
    boolean isUserExist(int userId, String passwordHash) throws DaoException;
}
