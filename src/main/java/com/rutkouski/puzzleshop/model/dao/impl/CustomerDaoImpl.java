package com.rutkouski.puzzleshop.model.dao.impl;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.dao.CustomerDao;
import com.rutkouski.puzzleshop.model.entity.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {
    static Logger logger = LogManager.getLogger();


    @Override
    public List<Customer> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Customer> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Customer entity) throws DaoException {
        return false;
    }

    @Override
    public int create(Customer entity) throws DaoException {
        return 0;
    }
}
