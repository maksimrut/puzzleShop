package com.rutkouski.puzzleshop.model.mapper.impl;

import com.rutkouski.puzzleshop.model.entity.Customer;
import com.rutkouski.puzzleshop.model.entity.User;
import com.rutkouski.puzzleshop.model.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.rutkouski.puzzleshop.model.mapper.ColumnName.*;

/**
 * The customer mapper
 *
 * @see RowMapper
 */
public class CustomerMapper implements RowMapper<Customer> {

    @Override
    public Optional<Customer> mapRow(ResultSet resultSet) {

        Customer customer = new Customer();
        try {
            customer.setId(resultSet.getInt(CUSTOMER_ID));
            customer.setLogin(resultSet.getString(LOGIN));
            customer.setPassword(resultSet.getString(PASSWORD));
            customer.setEmail(resultSet.getString(EMAIL));
            customer.setFirstName(resultSet.getString(FIRST_NAME));
            customer.setPhoneNumber(resultSet.getString(PHONE));
            customer.setRole(User.Role.valueOf(resultSet.getString(USER_ROLE).toUpperCase()));
            customer.setStatus(User.Status.valueOf(resultSet.getString(USER_STATUS).toUpperCase()));
            customer.setDiscount(resultSet.getInt(DISCOUNT_VALUE));
            return Optional.of(customer);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
