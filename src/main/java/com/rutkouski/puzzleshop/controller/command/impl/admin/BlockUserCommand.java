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

import static com.rutkouski.puzzleshop.controller.command.PagePath.TO_USER_MANAGEMENT_PAGE;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.USER_ID;

/**
 * The BlockUserCommand allows to administrator
 * change user status to BLOCKED
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */
public class BlockUserCommand implements Command {
    static Logger logger = LogManager.getLogger();
    UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        int userId = Integer.parseInt(request.getParameter(USER_ID));

        try {
            userService.updateUserStatusById(userId, User.Status.BLOCKED);
            router.setPagePath(TO_USER_MANAGEMENT_PAGE);
        } catch (ServiceException e) {
            logger.error("User status can not be updated: ", e);
            throw new CommandException("User status can not be updated: " + e);
        }
        return router;
    }
}
