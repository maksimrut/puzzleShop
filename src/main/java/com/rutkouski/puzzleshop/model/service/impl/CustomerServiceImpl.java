package com.rutkouski.puzzleshop.model.service.impl;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.dao.impl.CustomerDaoImpl;
import com.rutkouski.puzzleshop.model.entity.Customer;
import com.rutkouski.puzzleshop.model.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {
    static Logger logger = LogManager.getLogger();
    private static CustomerServiceImpl instance;
    private CustomerDaoImpl customerDao = new CustomerDaoImpl();

    private CustomerServiceImpl() {
    }

    public static CustomerServiceImpl getInstance() {
        if (instance == null) {
            instance = new CustomerServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean createCustomer(Map<String, String> formValues) throws ServiceException {
        return false;
    }

    @Override
    public List<Customer> findAllCustomers() throws ServiceException {
        try {
            return customerDao.findAll();
        } catch (DaoException e) {
            logger.error("Impossible to find all customers: ", e);
            throw new ServiceException("Impossible to find all customers: ", e);
        }
    }

    @Override
    public Optional<Customer> findCustomerById(int id) throws ServiceException {
        try {
            return customerDao.findById(id);
        } catch (DaoException e) {
            logger.error("Impossible to find customer by id: ", e);
            throw new ServiceException("Impossible to find customer by id: ", e);
        }
    }

}
