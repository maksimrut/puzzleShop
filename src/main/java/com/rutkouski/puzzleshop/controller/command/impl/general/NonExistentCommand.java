package com.rutkouski.puzzleshop.controller.command.impl.general;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import static com.rutkouski.puzzleshop.controller.command.PagePath.ERROR_400_PAGE;

/**
 * The NonExistentCommand redirects the {@link User}
 * to project error page in case of incorrect command or hack attack.
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */

public class NonExistentCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(ERROR_400_PAGE);
        return router;
    }
}
