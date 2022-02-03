package com.rutkouski.puzzleshop.model.service;

import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.Order;
import com.rutkouski.puzzleshop.model.entity.OrderItem;

import java.util.List;
import java.util.Optional;

/**
 * @author Maksim Rutkouski
 * <p>
 * The interface Order service.
 */
public interface OrderService {

    /**
     * Finds all orders list
     *
     * @return the list of all orders
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    List<Order> findAllOrders() throws ServiceException;

    /**
     * Finds order by id
     *
     * @param orderId the order id
     * @return the optional order
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    Optional<Order> findOrderById(int orderId) throws ServiceException;

    /**
     * Deletes order by id
     *
     * @param orderId the order id
     * @return the boolean
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    boolean deleteOrderById(int orderId) throws ServiceException;

    /**
     * Deletes order boolean.
     *
     * @param order the order for deleting
     * @return the boolean
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    boolean deleteOrder(Order order) throws ServiceException;

    /**
     * Creates order
     *
     * @param order the order to insert to database
     * @return the created order
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    Order createOrder(Order order) throws ServiceException;

    /**
     * Finds all orders by customer id
     *
     * @param customerId the customer id
     * @return the list of found orders
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    List<Order> findAllOrdersByCustomerId(int customerId) throws ServiceException;

    /**
     * Updates order status by id
     *
     * @param orderId the order id
     * @param status  the status to update
     * @return the boolean
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    boolean updateOrderStatusById(int orderId, Order.Status status) throws ServiceException;

    /**
     * Creates order item
     *
     * @param orderItem the order item to insert to database
     * @return the boolean
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    boolean createOrderItem(OrderItem orderItem) throws ServiceException;

    /**
     * Finds all order items by order id
     *
     * @param orderId the order id
     * @return the list of all order items of chosen order
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    List<OrderItem> findAllOrderItemsByOrderId(int orderId) throws ServiceException;

}
