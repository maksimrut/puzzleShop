package com.rutkouski.puzzleshop.controller.command.impl.guest;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.User;
import com.rutkouski.puzzleshop.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.*;
import static com.rutkouski.puzzleshop.controller.command.PagePath.MAIN_PAGE;
import static com.rutkouski.puzzleshop.controller.command.PagePath.REGISTRATION_PAGE;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.*;

/**
 * Command provides users registration on website.
 * In case of correct input data {@link User} can sign in.
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */

public class RegistrationCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private static final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
//        HttpSession session = request.getSession();
        Map<String, String> formValues = new HashMap<>();
        formValues.put(LOGIN, request.getParameter(LOGIN));
        formValues.put(PASSWORD, request.getParameter(PASSWORD));
        formValues.put(CONFIRM_PASSWORD, request.getParameter(CONFIRM_PASSWORD));
        formValues.put(EMAIL, request.getParameter(EMAIL));
        try {
            boolean registrationResult = userService.registerNewUser(formValues);

            if (registrationResult) {
                router.setPagePath(MAIN_PAGE);
//                session.
            } else {
                router.setPagePath(REGISTRATION_PAGE);
                for (String key : formValues.keySet()) {
                    String validationResult = formValues.get(key);
                    if (Boolean.parseBoolean(validationResult)) {
                        switch (key) {
                            case LOGIN -> request.setAttribute(VALID_LOGIN, request.getParameter(LOGIN));
                            case EMAIL -> request.setAttribute(VALID_EMAIL, request.getParameter(EMAIL));
                        }
                    } else {
                        switch (validationResult) {
                            case NOT_UNIQUE_LOGIN_RESULT -> request.setAttribute(INVALID_LOGIN, NOT_UNIQUE_MESSAGE);
                            case INVALID_LOGIN_RESULT -> request.setAttribute(INVALID_LOGIN, INVALID_MESSAGE);
                            case PASSWORD_MISMATCH -> request.setAttribute(INVALID_PASSWORD, PASSWORD_MISMATCH);
                            case INVALID_PASSWORD_RESULT -> request.setAttribute(INVALID_PASSWORD, INVALID_MESSAGE);
                            case NOT_UNIQUE_EMAIL_RESULT -> request.setAttribute(INVALID_EMAIL, NOT_UNIQUE_MESSAGE);
                            case INVALID_EMAIL_RESULT -> request.setAttribute(INVALID_EMAIL, INVALID_MESSAGE);
                        }
                    }
                }
            }
            request.setAttribute(REGISTRATION_RESULT, registrationResult);
            return router;
        } catch (ServiceException e) {
            logger.error("User can not be registered: ", e);
            throw new CommandException("User can not be registered: ", e);
        }
    }
}
