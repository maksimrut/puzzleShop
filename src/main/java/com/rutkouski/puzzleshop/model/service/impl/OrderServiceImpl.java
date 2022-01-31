package com.rutkouski.puzzleshop.model.service.impl;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.dao.impl.OrderDaoImpl;
import com.rutkouski.puzzleshop.model.entity.Order;
import com.rutkouski.puzzleshop.model.entity.OrderItem;
import com.rutkouski.puzzleshop.model.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    static Logger logger = LogManager.getLogger();
    private static OrderServiceImpl instance;
    private final OrderDaoImpl orderDao = OrderDaoImpl.getInstance();

    private OrderServiceImpl() {
    }

    public static OrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Order> findAllOrders() throws ServiceException {
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            logger.error("Impossible to find all orders: ", e);
            throw new ServiceException("Impossible to find all orders: ", e);
        }
    }

    @Override
    public Optional<Order> findOrderById(int orderId) throws ServiceException {
        try {
            return orderDao.findById(orderId);
        } catch (DaoException e) {
            logger.error("Impossible to find order by id: ", e);
            throw new ServiceException("Impossible to find order by id: ", e);
        }
    }

    @Override
    public boolean deleteOrderById(int orderId) throws ServiceException {
        try {
            return orderDao.deleteById(orderId);
        } catch (DaoException e) {
            logger.error("Impossible to delete order by id: ", e);
            throw new ServiceException("Impossible to delete order by id: ", e);
        }
    }

    @Override
    public boolean deleteOrder(Order order) throws ServiceException {
        try {
            return orderDao.delete(order);
        } catch (DaoException e) {
            logger.error("Impossible to delete order: ", e);
            throw new ServiceException("Impossible to delete order: ", e);
        }
    }

    @Override
    public Order createOrder(Order order) throws ServiceException {
        try {
            return orderDao.create(order);
        } catch (DaoException e) {
            logger.error("Impossible to create order: ", e);
            throw new ServiceException("Impossible to create order: ", e);
        }
    }

    @Override
    public List<Order> findAllOrdersByCustomerId(int customerId) throws ServiceException {
        try {
            return orderDao.findAllOrdersByCustomerId(customerId);
        } catch (DaoException e) {
            logger.error("Impossible to find orders by customer id: ", e);
            throw new ServiceException("Impossible to find orders by customer id: ", e);
        }
    }

    @Override
    public boolean updateOrderStatusById(int orderId, Order.Status status) throws ServiceException {
        try {
            return orderDao.updateOrderStatusById(orderId, status);
        } catch (DaoException e) {
            logger.error("Impossible to update order status: ", e);
            throw new ServiceException("Impossible to update order status: ", e);
        }
    }

    @Override
    public boolean createOrderItem(OrderItem orderItem) throws ServiceException {
        try {
            return orderDao.createOrderItem(orderItem);
        } catch (DaoException e) {
            logger.error("Impossible to create order item: ", e);
            throw new ServiceException("Impossible to create order item: ", e);
        }
    }

    @Override
    public List<OrderItem> findAllOrderItemsByOrderId(int orderId) throws ServiceException {
        try {
            return orderDao.findAllOrderItemsByOrderId(orderId);
        } catch (DaoException e) {
            logger.error("Impossible to find order items by order id: ", e);
            throw new ServiceException("Impossible to find order items by order id: ", e);
        }
    }
}
