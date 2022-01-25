package com.rutkouski.puzzleshop.model.service;

import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.Customer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerService {

    boolean createCustomer(Map<String, String> formValues) throws ServiceException;
    List<Customer> findAllCustomers() throws ServiceException;
    Optional<Customer> findCustomerById(int id) throws ServiceException;

}
