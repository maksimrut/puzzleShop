package com.rutkouski.puzzleshop.controller.command.impl.general;

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
import static com.rutkouski.puzzleshop.controller.command.PagePath.UPDATE_PASSWORD_PAGE;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.*;

public class UpdateUserPasswordCommand implements Command {
    static Logger logger = LogManager.getLogger();
    UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SESSION_USER);
        Map<String, String> formValues = new HashMap<>();
        formValues.put(OLD_PASSWORD, request.getParameter(OLD_PASSWORD));
        formValues.put(NEW_PASSWORD, request.getParameter(NEW_PASSWORD));
        formValues.put(CONFIRM_PASSWORD, request.getParameter(CONFIRM_PASSWORD));

        try {
            boolean result = userService.updateUserPasswordById(user.getId(), formValues);
            if (result) {
                request.setAttribute(PASSWORD_CHANGE_RESULT, result);
            } else {
                for (String key : formValues.keySet()) {
                    String checkResult = formValues.get(key);
                    if (!Boolean.parseBoolean(checkResult)) {
                        switch (checkResult) {
                            case INCORRECT_PASSWORD -> request.setAttribute(PASSWORD_CHANGE_RESULT, INCORRECT_MESSAGE);
                            case INVALID_PASSWORD -> request.setAttribute(PASSWORD_CHANGE_RESULT, INVALID_MESSAGE);
                            case PASSWORD_MISMATCH -> request.setAttribute(PASSWORD_CHANGE_RESULT, PASSWORD_MISMATCH);
                        }
                    }
                }
            }
            router.setPagePath(UPDATE_PASSWORD_PAGE);
            return router;
        } catch (ServiceException e) {
            logger.error("User password can not be updated: ", e);
            throw new CommandException("User password can not be updated: " + e);
        }
    }
}
