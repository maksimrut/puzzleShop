package com.rutkouski.puzzleshop.controller.command.impl.admin;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import static com.rutkouski.puzzleshop.controller.command.PagePath.GO_TO_ADD_PUZZLE_PAGE;

/**
 * The command directs the administrator
 * to the add puzzle page.
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */
public class GoToAddPuzzleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setPagePath(GO_TO_ADD_PUZZLE_PAGE);
        return router;
    }
}
