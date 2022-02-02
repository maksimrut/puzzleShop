package com.rutkouski.puzzleshop.controller.command.impl.admin;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.rutkouski.puzzleshop.controller.command.PagePath.TO_USER_MANAGEMENT_PAGE;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.USER_ID;

public class DeleteUserCommand implements Command {
    static Logger logger = LogManager.getLogger();
    UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        int userId = Integer.parseInt(request.getParameter(USER_ID));

        try {
            userService.deleteUserById(userId);
            router.setPagePath(TO_USER_MANAGEMENT_PAGE);
        } catch (ServiceException e) {
            logger.error("User can not be deleted: ", e);
            throw new CommandException("User can not be deleted: " + e);
        }
        return router;
    }
}
