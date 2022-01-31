package com.rutkouski.puzzleshop.model.dao;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.entity.Order;
import com.rutkouski.puzzleshop.model.entity.OrderItem;
import com.rutkouski.puzzleshop.model.entity.User;

import java.util.List;

public interface OrderDao extends BaseDao<Integer, Order> {

    List<Order> findAllOrdersByCustomerId(int customerId) throws DaoException;
    boolean updateOrderStatusById(int orderId, Order.Status status) throws DaoException;
    boolean createOrderItem(OrderItem orderItem) throws DaoException;
    List<OrderItem> findAllOrderItemsByOrderId(int orderId) throws DaoException;

}
