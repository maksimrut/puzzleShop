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

import static com.rutkouski.puzzleshop.controller.command.AttributeName.*;
import static com.rutkouski.puzzleshop.controller.command.PagePath.UPDATE_PROFILE_PAGE;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.FIRST_NAME;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.PHONE_NUMBER;

public class UpdateUserProfileCommand implements Command {
    static Logger logger = LogManager.getLogger();
    UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SESSION_USER);
        int userId = user.getId();
        String firstName = request.getParameter(FIRST_NAME);
        String phoneNumber = request.getParameter(PHONE_NUMBER);

        try {
            boolean updateNameResult = userService.updateUserFirstNameById(userId, firstName);
            if (updateNameResult) {
                session.setAttribute(USER_NAME, firstName);
            } else {
                request.setAttribute(NAME_CHANGE_RESULT, INCORRECT_MESSAGE);
            }
            boolean updatePhoneResult = userService.updateUserPhoneNumberById(userId, phoneNumber);
            if (updatePhoneResult) {
                session.setAttribute(USER_PHONE_NUMBER, phoneNumber);
            } else {
                request.setAttribute(PHONE_CHANGE_RESULT, INCORRECT_MESSAGE);
            }
            if (updateNameResult && updatePhoneResult) {
                request.setAttribute(PROFILE_CHANGE_RESULT, VALID_PROFILE_DATA);
            }
            router.setPagePath(UPDATE_PROFILE_PAGE);
            return router;
        } catch (ServiceException e) {
            logger.error("User data can not be updated: ", e);
            throw new CommandException("User data can not be updated: " + e);
        }
    }
}
