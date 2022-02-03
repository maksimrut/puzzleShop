package com.rutkouski.puzzleshop.model.service.impl;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.dao.impl.CustomerDaoImpl;
import com.rutkouski.puzzleshop.model.dao.impl.UserDaoImpl;
import com.rutkouski.puzzleshop.model.entity.Customer;
import com.rutkouski.puzzleshop.model.entity.User;
import com.rutkouski.puzzleshop.model.service.CustomerService;
import com.rutkouski.puzzleshop.util.PasswordEncryptor;
import com.rutkouski.puzzleshop.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.PASSWORD_MISMATCH;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.*;

/**
 * The {@link CustomerServiceImpl} class provides logic for future access to
 * customers table in the database
 */
public class CustomerServiceImpl implements CustomerService {
    static Logger logger = LogManager.getLogger();
    private static CustomerServiceImpl instance;
    private final UserDaoImpl userDao = UserDaoImpl.getInstance();
    private final CustomerDaoImpl customerDao = CustomerDaoImpl.getInstance();

    private CustomerServiceImpl() {
    }

    public static CustomerServiceImpl getInstance() {
        if (instance == null) {
            instance = new CustomerServiceImpl();
        }
        return instance;
    }

    @Override
    public Customer registerNewCustomer(Map<String, String> formValues) throws ServiceException {
        boolean result;
        Customer registeredCustomer = null;
        String login = formValues.get(LOGIN);
        String password = formValues.get(PASSWORD);
        String confirmPassword = formValues.get(CONFIRM_PASSWORD);
        String email = formValues.get(EMAIL);

        try {
            String loginCheckResult = UserValidator.isLoginValid(login)
                    ? (!userDao.isLoginExist(login) ? TRUE : NOT_UNIQUE_LOGIN_RESULT)
                    : INVALID_LOGIN_RESULT;
            String passwordCheckResult = UserValidator.isPasswordValid(password)
                    ? (password.equals(confirmPassword) ? TRUE : PASSWORD_MISMATCH)
                    : INVALID_PASSWORD_RESULT;
            String emailCheckResult = UserValidator.isEmailValid(email)
                    ? (!userDao.isEmailExist(email) ? TRUE : NOT_UNIQUE_EMAIL_RESULT)
                    : INVALID_EMAIL_RESULT;

            result = Boolean.parseBoolean(loginCheckResult) && Boolean.parseBoolean(passwordCheckResult)
                    && Boolean.parseBoolean(emailCheckResult);

            if (result) {
                User.Role role = User.Role.CUSTOMER;
                String encryptedPassword = PasswordEncryptor.encrypt(password);

                Customer customer = new Customer(login, encryptedPassword, email, role);
                registeredCustomer = customerDao.create(customer);
            } else {
                formValues.remove(CONFIRM_PASSWORD, confirmPassword);
                formValues.replace(LOGIN, loginCheckResult);
                formValues.replace(PASSWORD, passwordCheckResult);
                formValues.replace(EMAIL, emailCheckResult);
            }
        } catch (DaoException e) {
            logger.error("Impossible to create new user ", e);
            throw new ServiceException("Impossible to create new user ", e);
        }
        return registeredCustomer;
    }

    @Override
    public List<Customer> findAllCustomers() throws ServiceException {
        try {
            return customerDao.findAll();
        } catch (DaoException e) {
            logger.error("Impossible to find all customers ", e);
            throw new ServiceException("Impossible to find all customers ", e);
        }
    }

    @Override
    public Optional<Customer> findCustomerById(int id) throws ServiceException {
        try {
            return customerDao.findById(id);
        } catch (DaoException e) {
            logger.error("Impossible to find customer by id ", e);
            throw new ServiceException("Impossible to find customer by id ", e);
        }
    }

    @Override
    public int findCustomerDiscountById(Integer customerId) throws ServiceException {
        try {
            return customerDao.findDiscountByCustomerId(customerId);
        } catch (DaoException e) {
            logger.error("Impossible to find discount by id ", e);
            throw new ServiceException("Impossible to find discount by id ", e);
        }
    }

    @Override
    public boolean updateCustomerDiscountById(Integer customerId, Integer discountId) throws ServiceException {
        try {
            return customerDao.updateCustomerDiscount(customerId, discountId);
        } catch (DaoException e) {
            logger.error("Impossible to update customer discount ", e);
            throw new ServiceException("Impossible to update customer discount ", e);
        }
    }
}
