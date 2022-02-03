package com.rutkouski.puzzleshop.controller.command.impl.general;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import static com.rutkouski.puzzleshop.controller.command.PagePath.GO_TO_PROFILE_PAGE;

/**
 * The command directs the {@link User}
 * to the user profile page.
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */
public class GoToProfileCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setPagePath(GO_TO_PROFILE_PAGE);
        return router;
    }
}
