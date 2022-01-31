package com.rutkouski.puzzleshop.model.dao.impl;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.dao.CustomerDao;
import com.rutkouski.puzzleshop.model.entity.Customer;
import com.rutkouski.puzzleshop.model.mapper.RowMapper;
import com.rutkouski.puzzleshop.model.mapper.impl.CustomerMapper;
import com.rutkouski.puzzleshop.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Optional;

import static com.rutkouski.puzzleshop.model.mapper.ColumnName.DISCOUNT_VALUE;

public class CustomerDaoImpl implements CustomerDao {
    static Logger logger = LogManager.getLogger();

    private static final String SQL_FIND_ALL = """
            SELECT customers.id, login, password, email, first_name, phone, role, status, discount
            FROM users
            JOIN customers ON users.id=customers.id
            JOIN roles ON users.role_id=roles.id
			JOIN user_statuses ON users.status_id=user_statuses.id""";
    private static final String SQL_FIND_BY_ID = """
            SELECT customers.id, login, password, email, first_name, phone, role, status, discount
            FROM users
            JOIN customers ON users.id=customers.id
            JOIN roles ON users.role_id=roles.id
			JOIN user_statuses ON users.status_id=user_statuses.id
            WHERE customers.id=?""";
    private static final String SQL_FIND_DISCOUNT_BY_CUSTOMER_ID = """
            SELECT discount FROM customers
            WHERE customers.id=?""";
    private static final String SQL_UPDATE_CUSTOMER_DISCOUNT =
            "UPDATE customers SET discount=? WHERE customers.id=?";
    private static final String SQL_INSERT_NEW_USER = """
            INSERT INTO users (login, password, email, first_name, phone, role_id, status_id)
			VALUES (?, ?, ?, ?, ?, ?, ?)""";
    private static final String SQL_INSERT_NEW_CUSTOMER =
            "INSERT INTO customers (customers.id) VALUES (?)";
    private static CustomerDaoImpl instance;
    private final RowMapper<Customer> mapper = new CustomerMapper();

    private CustomerDaoImpl() {
    }

    public static CustomerDaoImpl getInstance() {
        if (instance == null) {
            instance = new CustomerDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Customer> findAll() throws DaoException {
        List<Customer> customers;
        try(Connection connection = CustomConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = statement.executeQuery()) {
            customers = mapper.mapRows(resultSet);
            logger.debug("findAll method was completed successfully. {} customers were found", customers.size());
        } catch (SQLException e) {
            logger.error("SQL exception happened in findAll method: ", e);
            throw new DaoException("SQL exception happened in findAll method", e);
        }
        return customers;
    }

    @Override
    public Optional<Customer> findById(Integer id) throws DaoException {
        Optional<Customer> optionalCustomer = Optional.empty();
        try(Connection connection = CustomConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setInt(FIRST_PARAM_INDEX, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalCustomer = mapper.mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("SQL exception happened in findById method: ", e);
            throw new DaoException("SQL exception happened in findById method", e);
        }
        logger.debug("findById method was completed successfully. Customer with id={} {}", id,
                ((optionalCustomer.isPresent()) ? " is found" : " doesn't exist"));
        return optionalCustomer;
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
        throw new UnsupportedOperationException("Customer deleting can be realized only through user deleting!");
    }

    @Override
    public boolean delete(Customer entity) throws DaoException {
        throw new UnsupportedOperationException("Customer deleting can be realized only through user deleting!");
    }

    @Override
    public Customer create(Customer customer) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().takeConnection();
                PreparedStatement userStatement = connection.prepareStatement(SQL_INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS);
        PreparedStatement customerStatement = connection.prepareStatement(SQL_INSERT_NEW_CUSTOMER)) {
            try {
                connection.setAutoCommit(false);
                userStatement.setString(FIRST_PARAM_INDEX, customer.getLogin());
                userStatement.setString(SECOND_PARAM_INDEX, customer.getPassword());
                userStatement.setString(THIRD_PARAM_INDEX, customer.getEmail());
                userStatement.setString(FOURTH_PARAM_INDEX, customer.getFirstName());
                userStatement.setString(FIFTH_PARAM_INDEX, customer.getPhoneNumber());
                userStatement.setInt(SIXTH_PARAM_INDEX, customer.getRole().ordinal() + 1);
                userStatement.setInt(SEVENTH_PARAM_INDEX, customer.getStatus().ordinal() + 1);

                userStatement.executeUpdate();
                try(ResultSet resultSet = userStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int customerId = resultSet.getInt(FIRST_PARAM_INDEX);
                        customer.setId(customerId);
                    }
                    customerStatement.setInt(FIRST_PARAM_INDEX, customer.getId());
                    customerStatement.executeUpdate();
                    connection.commit();
                }
            } catch (SQLException e) {
                connection.rollback();
                logger.error("SQL exception happened in create method: ", e);
                throw new DaoException("SQL exception happened in create method: ", e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            logger.error("SQL exception happened in create method: ", e);
            throw new DaoException("SQL exception happened in create method: ", e);
        }
        logger.debug("Customer {} was created successfully", customer);
        return customer;
    }

    @Override
    public int findDiscountByCustomerId(Integer id) throws DaoException {
        int discount = 0;
        try(Connection connection = CustomConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_DISCOUNT_BY_CUSTOMER_ID)) {
            statement.setInt(FIRST_PARAM_INDEX, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    discount = resultSet.getInt(DISCOUNT_VALUE);
                }
            }
            logger.debug("Customer (id {}) discount is: {}", id, discount);
            return discount;
        } catch (SQLException e) {
            logger.error("SQL exception happened in findDiscountByCustomerId method: ", e);
            throw new DaoException("SQL exception happened in findDiscountByCustomerId method", e);
        }
    }

    @Override
    public boolean updateCustomerDiscount(Integer id, Integer discountId) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().takeConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_CUSTOMER_DISCOUNT)) {
            statement.setInt(FIRST_PARAM_INDEX, discountId);
            statement.setInt(SECOND_PARAM_INDEX, id);
            boolean result = statement.executeUpdate() == 1;
            logger.debug("The result of customer id={} discount updating is: {}", id, result);
            return result;
        } catch (SQLException e) {
            logger.error("SQL exception happened in updateCustomerDiscount method: ", e);
            throw new DaoException("SQL exception happened in updateCustomerDiscount method", e);
        }
    }
}
