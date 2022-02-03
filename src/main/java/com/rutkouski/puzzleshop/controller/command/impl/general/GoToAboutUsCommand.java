package com.rutkouski.puzzleshop.controller.command.impl.general;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import static com.rutkouski.puzzleshop.controller.command.PagePath.ABOUT_US_PAGE;

/**
 * The command directs the {@link User}
 * to the about us page.
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */
public class GoToAboutUsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setPagePath(ABOUT_US_PAGE);
        return router;
    }
}
