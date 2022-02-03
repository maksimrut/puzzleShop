package com.rutkouski.puzzleshop.model.service;

import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.Customer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Maksim Rutkouski
 * <p>
 * The interface Customer service.
 */
public interface CustomerService {

    /**
     * Registers new customer
     *
     * @param formValues the form values of all data of new customer
     * @return the registered customer
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    Customer registerNewCustomer(Map<String, String> formValues) throws ServiceException;

    /**
     * Finds all customers
     *
     * @return the list of all customers
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    List<Customer> findAllCustomers() throws ServiceException;

    /**
     * Finds customer by id
     *
     * @param id the customer id
     * @return the optional customer
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    Optional<Customer> findCustomerById(int id) throws ServiceException;

    /**
     * Finds customer discount by id
     *
     * @param customerId the customer id
     * @return the int customer discount
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    int findCustomerDiscountById(Integer customerId) throws ServiceException;

    /**
     * Updates customer discount by id
     *
     * @param customerId the customer id
     * @param discountId the discount id
     * @return the boolean result of discount updating
     * @throws ServiceException in case if exception occurred on the DAO level
     */
    boolean updateCustomerDiscountById(Integer customerId, Integer discountId) throws ServiceException;
}
