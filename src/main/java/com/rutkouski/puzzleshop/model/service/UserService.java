package com.rutkouski.puzzleshop.model.service;

import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserByLoginAndPassword(String login, String password) throws ServiceException;
    boolean registerNewUser(Map<String, String> formValues) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;
    Optional<User> findUserById(int id) throws ServiceException;
    List<User> findAllActiveUsers() throws ServiceException;
    Optional<User> findUserByEmail(String email) throws ServiceException;
    boolean deleteUserById(int id) throws ServiceException;
    boolean deleteUser(User user) throws ServiceException;
    boolean updateUserFirstNameById(int userId, String firstName) throws ServiceException;
    boolean updateUserPhoneNumberById(int userId, String phoneNumber) throws ServiceException;
    boolean updateUserPasswordById(int userId, Map<String, String> formValues) throws ServiceException;
    boolean updateUserEmailById(int userId, Map<String, String> formValue) throws ServiceException;
    boolean updateUserStatusById(int userId, User.Status status) throws ServiceException;
}
