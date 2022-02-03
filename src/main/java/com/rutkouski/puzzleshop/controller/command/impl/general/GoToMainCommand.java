package com.rutkouski.puzzleshop.controller.command.impl.general;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import static com.rutkouski.puzzleshop.controller.command.PagePath.MAIN_PAGE;

/**
 * The command directs the {@link User}
 * to the main page.
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */
public class GoToMainCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(MAIN_PAGE);
        return router;
    }
}
