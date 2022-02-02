package com.rutkouski.puzzleshop.controller.command.impl.admin;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.Puzzle;
import com.rutkouski.puzzleshop.model.service.impl.PuzzleServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.PUZZLE_TO_EDIT;
import static com.rutkouski.puzzleshop.controller.command.PagePath.EDIT_PUZZLE_PAGE;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.PUZZLE_ID;

public class GoToEditPuzzleCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private final PuzzleServiceImpl puzzleService = PuzzleServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        int puzzleId = Integer.parseInt(request.getParameter(PUZZLE_ID));

        try {
            Puzzle puzzle = puzzleService.findPuzzleById(puzzleId).get();
            request.setAttribute(PUZZLE_TO_EDIT, puzzle);
            router.setPagePath(EDIT_PUZZLE_PAGE);
        } catch (ServiceException e) {
            logger.error("Exception occurred in GoToEditPuzzleCommand class", e);
            throw new CommandException("Exception occurred in GoToEditPuzzleCommand class" + e);
        }
        return router;
    }
}
