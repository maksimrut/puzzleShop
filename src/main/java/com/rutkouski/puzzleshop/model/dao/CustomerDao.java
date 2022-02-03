package com.rutkouski.puzzleshop.model.dao;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.entity.Customer;

/**
 * @author Maksim Rutkouski
 * <p>
 * The interface Customer dao. Extends {@link BaseDao}
 */
public interface CustomerDao extends BaseDao<Integer, Customer> {

    /**
     * Finds discount by customer's id
     *
     * @param id the customer's id
     * @return the int customer's discount
     * @throws DaoException happens if the request to database could not be handled
     */
    int findDiscountByCustomerId(Integer id) throws DaoException;

    /**
     * Updates customer's discount
     *
     * @param id         the customer's id
     * @param discountId the discount id to update
     * @return the boolean result
     * @throws DaoException happens if the request to database could not be handled
     */
    boolean updateCustomerDiscount(Integer id, Integer discountId) throws DaoException;

}
