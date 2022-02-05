package com.rutkouski.puzzleshop.model.dao.impl;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.entity.Order;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class OrderDaoImplTest {
    OrderDaoImpl orderDao;

    @BeforeClass
    public void init() {
        orderDao = OrderDaoImpl.getInstance();
    }

    @Test
    public void testFindAll() throws DaoException {
        List<Order> actual = orderDao.findAll();
        assertFalse(actual.isEmpty());
    }

    @Test
    public void testFindById() throws DaoException {
        int orderId = 3;
        Optional<Order> actual = orderDao.findById(orderId);
        assertTrue(actual.isPresent());
    }
}