package com.rutkouski.puzzleshop.controller.command.impl.guest;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import static com.rutkouski.puzzleshop.controller.command.PagePath.REGISTRATION_PAGE;

/**
 * The GoToRegistrationCommand directs the {@link User}
 * to the registration page.
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */

public class GoToRegistrationCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(REGISTRATION_PAGE);
        return router;
    }
}
