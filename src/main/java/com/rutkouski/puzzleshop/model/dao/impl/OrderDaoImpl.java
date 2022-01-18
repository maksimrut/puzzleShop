package com.rutkouski.puzzleshop.model.dao.impl;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.dao.OrderDao;
import com.rutkouski.puzzleshop.model.entity.Order;
import com.rutkouski.puzzleshop.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    static Logger logger = LogManager.getLogger();


    @Override
    public List<Order> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Order> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Order entity) throws DaoException {
        return false;
    }

    @Override
    public int create(Order entity) throws DaoException {
        return 0;
    }

    @Override
    public List<Order> findAllUserOrders(User user) {
        return null;
    }

    @Override
    public boolean updateOrderStatusById(int orderId, Order.Status status) {
        return false;
    }
}
