package com.rutkouski.puzzleshop.model.dao.impl;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.dao.OrderDao;
import com.rutkouski.puzzleshop.model.entity.Order;
import com.rutkouski.puzzleshop.model.entity.OrderItem;
import com.rutkouski.puzzleshop.model.entity.User;
import com.rutkouski.puzzleshop.model.mapper.RowMapper;
import com.rutkouski.puzzleshop.model.mapper.impl.OrderMapper;
import com.rutkouski.puzzleshop.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.rutkouski.puzzleshop.model.mapper.ColumnName.*;

public class OrderDaoImpl implements OrderDao {
    static Logger logger = LogManager.getLogger();

    private static final String SQL_FIND_ALL = """
            SELECT orders.id, order_date, total_price, order_statuses.status, customer_id
            FROM orders
            JOIN order_statuses ON order_status_id = order_statuses.id""";
    private static final String SQL_FIND_BY_ID = """
            SELECT orders.id, order_date, total_price, order_statuses.status, customer_id
            FROM orders
            JOIN order_statuses ON order_status_id = order_statuses.id
            WHERE orders.id=?""";
    private static final String SQL_DELETE_BY_ID =
            "DELETE FROM orders WHERE orders.id=?";
    private static final String SQL_INSERT_NEW_ORDER =
            "INSERT INTO orders (order_date, total_price, customer_id) VALUES(?, ?, ?)";
    private static final String SQL_FIND_ALL_BY_CUSTOMER_ID = """
            SELECT orders.id, order_date, total_price, order_statuses.status, customer_id
            FROM orders
            JOIN order_statuses ON order_status_id = order_statuses.id
            WHERE customer_id=?""";
    private static final String SQL_UPDATE_ORDER_STATUS_BY_ID =
            "UPDATE orders SET order_status_id=? WHERE customer_id=?";
    private static final String SQL_INSERT_ORDER_ITEM =
            "INSERT INTO order_items (item_quantity, puzzle_id, order_id) VALUES(?, ?, ?)";
    private static final String SQL_FIND_ALL_ORDER_ITEMS_BY_ORDER_ID = """
            SELECT order_items.id, item_quantity, puzzle_id, order_id
            FROM order_items WHERE order_id=?""";


    private static OrderDaoImpl instance;
    private final RowMapper<Order> mapper = new OrderMapper();

    private OrderDaoImpl() {
    }

    public static OrderDaoImpl getInstance() {
        if (instance == null) {
            instance = new OrderDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orders;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            orders = mapper.mapRows(resultSet);
            logger.debug("findAll method was completed successfully. {} orders were found", orders.size());
        } catch (SQLException e) {
            logger.error("SQL exception happened in findAll method: ", e);
            throw new DaoException("SQL exception happened in findAll method", e);
        }
        return orders;
    }

    @Override
    public Optional<Order> findById(Integer id) throws DaoException {
        Optional<Order> optionalOrder = Optional.empty();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setInt(FIRST_PARAM_INDEX, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalOrder = mapper.mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("SQL exception happened in findById method: ", e);
            throw new DaoException("SQL exception happened in findById method", e);
        }
        logger.debug("findById method was completed successfully. Order with id={} {}", id,
                ((optionalOrder.isPresent()) ? " is found" : " doesn't exist"));
        return optionalOrder;
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setInt(FIRST_PARAM_INDEX, id);
            boolean result = statement.executeUpdate() == 1;
            logger.debug("deleteById method was completed successfully. Result: {}", result);
            return result;
        } catch (SQLException e) {
            logger.error("SQL exception happened in deleteById method: ", e);
            throw new DaoException("SQL exception happened in deleteById method", e);
        }
    }

    @Override
    public boolean delete(Order order) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setInt(FIRST_PARAM_INDEX, order.getId());
            boolean result = statement.executeUpdate() == 1;
            logger.debug("delete(Order order) method was completed successfully. Result: {}", result);
            return result;
        } catch (SQLException e) {
            logger.error("SQL exception happened in delete(Order order) method: ", e);
            throw new DaoException("SQL exception happened in delete(Order order) method", e);
        }
    }

    @Override
    public Order create(Order order) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(FIRST_PARAM_INDEX, Date.valueOf(order.getDate()));
            statement.setBigDecimal(SECOND_PARAM_INDEX, order.getTotalPrice());
            statement.setInt(THIRD_PARAM_INDEX, order.getCustomerId());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int orderId = resultSet.getInt(FIRST_PARAM_INDEX);
                    order.setId(orderId);
                    logger.info("create(Order order) method was completed successfully. Order id={} was created", orderId);
                }
                return order;
            }
        } catch (SQLException e) {
            logger.error("SQL exception happened in create(Order order) method: ", e);
            throw new DaoException("SQL exception happened in create(Order order) method", e);
        }
    }

    @Override
    public List<Order> findAllOrdersByCustomerId(int customerId) throws DaoException {
        List<Order> orders;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_BY_CUSTOMER_ID)) {
            statement.setInt(FIRST_PARAM_INDEX, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                orders = mapper.mapRows(resultSet);
                logger.debug("findAll method was completed successfully. {} orders were found", orders.size());
                return orders;
            }
        } catch (SQLException e) {
            logger.error("SQL exception happened in findAllOrdersByCustomerId method: ", e);
            throw new DaoException("SQL exception happened in findAllOrdersByCustomerId method", e);
        }
    }

    @Override
    public boolean updateOrderStatusById(int orderId, Order.Status status) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER_STATUS_BY_ID)) {
            statement.setInt(FIRST_PARAM_INDEX, status.ordinal() + 1);
            statement.setInt(SECOND_PARAM_INDEX, orderId);
            boolean result = statement.executeUpdate() == 1;
            logger.debug("The result of order id={} status updating is: {}", orderId, result);
            return result;
        } catch (SQLException e) {
            logger.error("SQL exception happened in updateOrderStatusById method: ", e);
            throw new DaoException("SQL exception happened in updateOrderStatusById method", e);
        }
    }

    @Override
    public boolean createOrderItem(OrderItem orderItem) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ORDER_ITEM)) {
            statement.setInt(FIRST_PARAM_INDEX, orderItem.getItemQuantity());
            statement.setInt(SECOND_PARAM_INDEX, orderItem.getPuzzleId());
            statement.setInt(THIRD_PARAM_INDEX, orderItem.getOrderId());
            boolean result = statement.executeUpdate() == 1;
            logger.debug("The result of OrderItem creating is result is: {}", result);
            return result;
        } catch (SQLException e) {
            logger.error("SQL exception happened in createOrderItem method: ", e);
            throw new DaoException("SQL exception happened in createOrderItem method", e);
        }
    }

    @Override
    public List<OrderItem> findAllOrderItemsByOrderId(int orderId) throws DaoException {
        List<OrderItem> orderItems = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ORDER_ITEMS_BY_ORDER_ID)) {
            statement.setInt(FIRST_PARAM_INDEX, orderId);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    OrderItem foundOrderItem = extractOrderItemsValues(resultSet);
                    orderItems.add(foundOrderItem);
                }
                logger.debug("findAllOrderItemsByOrderId method was completed successfully. {} order items were found", orderItems.size());
                return orderItems;
            }
        } catch (SQLException e) {
            logger.error("SQL exception happened in findAllOrderItemsByOrderId method: ", e);
            throw new DaoException("SQL exception happened in findAllOrderItemsByOrderId method", e);
        }
    }

    private OrderItem extractOrderItemsValues(ResultSet resultSet) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(resultSet.getInt(ORDER_ITEM_ID));
        orderItem.setItemQuantity(resultSet.getInt(ITEM_QUANTITY));
        orderItem.setPuzzleId(resultSet.getInt(ITEM_ID));
        orderItem.setOrderId(resultSet.getInt(ORDER_ITEMS_ORDER_ID));
        return orderItem;
    }
}
