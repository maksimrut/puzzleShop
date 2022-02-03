package com.rutkouski.puzzleshop.model.dao;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.entity.Order;
import com.rutkouski.puzzleshop.model.entity.OrderItem;

import java.util.List;

/**
 * @author Maksim Rutkouski
 * <p>
 * The interface Order dao. Extends {@link BaseDao}
 */
public interface OrderDao extends BaseDao<Integer, Order> {

    /**
     * Finds all orders by customer id
     *
     * @param customerId the customer id
     * @return the list ao found orders
     * @throws DaoException happens if the request to database could not be handled
     */
    List<Order> findAllOrdersByCustomerId(int customerId) throws DaoException;

    /**
     * Updates order status by id
     *
     * @param orderId the order's id
     * @param status  the order's status to update
     * @return the boolean result
     * @throws DaoException happens if the request to database could not be handled
     */
    boolean updateOrderStatusById(int orderId, Order.Status status) throws DaoException;

    /**
     * Creates order item
     *
     * @param orderItem the order item needed to insert into database
     * @return the boolean result
     * @throws DaoException happens if the request to database could not be handled
     */
    boolean createOrderItem(OrderItem orderItem) throws DaoException;

    /**
     * Finds all order items by order id
     *
     * @param orderId the order's id
     * @return the list off found order items
     * @throws DaoException happens if the request to database could not be handled
     */
    List<OrderItem> findAllOrderItemsByOrderId(int orderId) throws DaoException;

}
