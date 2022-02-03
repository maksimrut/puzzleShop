package com.rutkouski.puzzleshop.controller.command.impl.general;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import static com.rutkouski.puzzleshop.controller.command.PagePath.UPDATE_PASSWORD_PAGE;

/**
 * The GoToUpdatePasswordCommand directs the {@link User}
 * to the password updating page.
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */
public class GoToUpdatePasswordCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setPagePath(UPDATE_PASSWORD_PAGE);
        return router;
    }
}
