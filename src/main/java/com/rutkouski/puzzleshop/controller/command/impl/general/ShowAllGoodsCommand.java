package com.rutkouski.puzzleshop.controller.command.impl.general;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.Puzzle;
import com.rutkouski.puzzleshop.model.service.impl.PuzzleServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.PUZZLES_LIST;
import static com.rutkouski.puzzleshop.controller.command.PagePath.GOODS_PAGE;

public class ShowAllGoodsCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private final PuzzleServiceImpl puzzleService = PuzzleServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            List<Puzzle> puzzles = puzzleService.findAllPuzzles();
            request.setAttribute(PUZZLES_LIST, puzzles);
            router.setPagePath(GOODS_PAGE);
        } catch (ServiceException e) {
            logger.error("Exception occurred in ShowAllGoodsCommand class", e);
            throw new CommandException("Exception occurred in ShowAllGoodsCommand class", e);
        }
        return router;
    }
}
