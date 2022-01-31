package com.rutkouski.puzzleshop.model.service;

import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.Order;
import com.rutkouski.puzzleshop.model.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAllOrders() throws ServiceException;
    Optional<Order> findOrderById(int orderId) throws ServiceException;
    boolean deleteOrderById(int orderId) throws ServiceException;
    boolean deleteOrder(Order order) throws ServiceException;
    Order createOrder(Order order) throws ServiceException;
    List<Order> findAllOrdersByCustomerId(int customerId) throws ServiceException;
    boolean updateOrderStatusById(int orderId, Order.Status status) throws ServiceException;
    boolean createOrderItem(OrderItem orderItem) throws ServiceException;
    List<OrderItem> findAllOrderItemsByOrderId(int orderId) throws ServiceException;

}
