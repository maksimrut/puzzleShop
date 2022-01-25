package com.rutkouski.puzzleshop.model.dao;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.entity.Customer;

import java.math.BigDecimal;

public interface CustomerDao extends BaseDao<Integer, Customer> {

    int findDiscountByCustomerId(Integer id) throws DaoException;
    boolean updateCustomerDiscount(Integer id, Integer discountId) throws DaoException;
    boolean updateCustomerBalance(Integer id, BigDecimal balance) throws DaoException;
}
