package com.rutkouski.puzzleshop.model.mapper.impl;

import com.rutkouski.puzzleshop.model.entity.Order;
import com.rutkouski.puzzleshop.model.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.rutkouski.puzzleshop.model.mapper.ColumnName.*;

/**
 * The order mapper
 *
 * @see RowMapper
 */
public class OrderMapper implements RowMapper<Order> {

    @Override
    public Optional<Order> mapRow(ResultSet resultSet) {
        Order order = new Order();
        try {
            order.setId(resultSet.getInt(ORDER_ID));
            order.setDate(resultSet.getDate(ORDER_DATE).toLocalDate());
            order.setTotalPrice(resultSet.getBigDecimal(TOTAL_PRICE));
            order.setCustomerId(resultSet.getInt(ORDER_CUSTOMER_ID));
            order.setStatus(Order.Status.valueOf(resultSet.getString(ORDER_STATUS).toUpperCase()));
            return Optional.of(order);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
