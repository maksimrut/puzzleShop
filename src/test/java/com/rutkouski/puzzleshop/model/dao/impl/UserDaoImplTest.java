package com.rutkouski.puzzleshop.model.dao.impl;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.entity.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class UserDaoImplTest {
    UserDaoImpl userDao;

    @BeforeClass
    public void init(){
        userDao = UserDaoImpl.getInstance();
    }

    @Test
    public void testFindById() throws DaoException {
        int userId = 27;
        Optional<User> actual = userDao.findById(userId);
        assertTrue(actual.isPresent());
    }

    @Test
    public void testIsLoginExist() throws DaoException {
        String login = "thirdUser";
        boolean actual = userDao.isLoginExist(login);
        assertTrue(actual);
    }

    @Test
    public void testFindUserStatusById() throws DaoException {
        int userId = 27;
        User.Status expected = User.Status.ACTIVE;
        Optional<User.Status> optionalStatus = userDao.findUserStatusById(userId);
        User.Status actual = optionalStatus.get();
        assertEquals(actual, expected);
    }

    @Test
    public void testFindAll() throws DaoException {
        List<User> actual = userDao.findAll();
        assertFalse(actual.isEmpty());
    }

    @Test
    public void testIsEmailExist() throws DaoException {
        String email = "trewq@tut.by";
        boolean actual = userDao.isEmailExist(email);
        assertTrue(actual);
    }
}
