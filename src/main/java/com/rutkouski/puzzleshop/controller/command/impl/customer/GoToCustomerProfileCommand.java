package com.rutkouski.puzzleshop.controller.command.impl.customer;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import static com.rutkouski.puzzleshop.controller.command.PagePath.GO_TO_CUSTOMER_PROFILE_PAGE;

public class GoToCustomerProfileCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setPagePath(GO_TO_CUSTOMER_PROFILE_PAGE);
        return router;
    }
}
