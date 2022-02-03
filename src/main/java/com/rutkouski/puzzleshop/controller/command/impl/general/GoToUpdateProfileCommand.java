package com.rutkouski.puzzleshop.controller.command.impl.general;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import static com.rutkouski.puzzleshop.controller.command.PagePath.UPDATE_PROFILE_PAGE;

/**
 * The GoToUpdateProfileCommand directs the {@link User}
 * to the page for profile updating.
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */
public class GoToUpdateProfileCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setPagePath(UPDATE_PROFILE_PAGE);
        return router;
    }
}
