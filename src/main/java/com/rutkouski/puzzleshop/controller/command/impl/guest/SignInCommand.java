package com.rutkouski.puzzleshop.controller.command.impl.guest;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.User;
import com.rutkouski.puzzleshop.model.service.impl.CustomerServiceImpl;
import com.rutkouski.puzzleshop.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.*;
import static com.rutkouski.puzzleshop.controller.command.PagePath.MAIN_PAGE;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.LOGIN;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.PASSWORD;

public class SignInCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final CustomerServiceImpl customerService = CustomerServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        try {
            Optional<User> userOptional = userService.findUserByLoginAndPassword(login, password);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (user.getStatus() == User.Status.ACTIVE) {
                    session.setAttribute(SESSION_USER, user);
                    session.setAttribute(SIGN_IN_RESULT, Boolean.TRUE);
                    if (user.getRole().equals(User.Role.CUSTOMER)) {
                        int discount = customerService.findCustomerDiscountById(user.getId());
                        session.setAttribute(USER_DISCOUNT, discount);
                    }
                }
            } else {
                session.setAttribute(SIGN_IN_RESULT, Boolean.FALSE);
            }
            router.setPagePath(MAIN_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
            return router;
        } catch (ServiceException e) {
            logger.error("Authentication can not be completed: ", e);
            throw new CommandException("Authentication can not be completed: ", e);
        }
    }
}
