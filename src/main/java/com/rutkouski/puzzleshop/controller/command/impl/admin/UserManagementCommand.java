package com.rutkouski.puzzleshop.controller.command.impl.admin;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.User;
import com.rutkouski.puzzleshop.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.USER_LIST;
import static com.rutkouski.puzzleshop.controller.command.PagePath.USER_MANAGEMENT_PAGE;

/**
 * The command directs the administrator
 * to the user management page.
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */
public class UserManagementCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            List<User> users = userService.findAllUsers();
            request.setAttribute(USER_LIST, users);
            router.setPagePath(USER_MANAGEMENT_PAGE);
            return router;
        } catch (ServiceException e) {
            logger.error("Users cannot be found:", e);
            throw new CommandException("Users cannot be found:" + e);
        }
    }
}
