package com.rutkouski.puzzleshop.controller.command.impl.admin;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.service.impl.PuzzleServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.rutkouski.puzzleshop.controller.command.PagePath.SHOW_ALL_GOODS_PAGE;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.PUZZLE_ID;

/**
 * The DeletePuzzleCommand allows to administrator
 * delete chosen puzzle
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */
public class DeletePuzzleCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private final PuzzleServiceImpl puzzleService = PuzzleServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        int puzzleId = Integer.parseInt(request.getParameter(PUZZLE_ID));

        try {
            puzzleService.deletePuzzleById(puzzleId);
            router.setPagePath(SHOW_ALL_GOODS_PAGE);
            return router;
        } catch (ServiceException e) {
            logger.error("Exception occurred in DeletePuzzleCommand class", e);
            throw new CommandException("Exception occurred in DeletePuzzleCommand class" + e);
        }
    }
}
