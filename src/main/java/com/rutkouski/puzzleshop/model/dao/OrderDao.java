package com.rutkouski.puzzleshop.model.dao;

import com.rutkouski.puzzleshop.model.entity.Order;
import com.rutkouski.puzzleshop.model.entity.User;

import java.util.List;

public interface OrderDao extends BaseDao<Integer, Order> {

    List<Order> findAllUserOrders(User user);
    boolean updateOrderStatusById(int orderId, Order.Status status);
}
