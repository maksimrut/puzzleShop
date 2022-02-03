package com.rutkouski.puzzleshop.controller.command.impl.customer;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.Puzzle;
import com.rutkouski.puzzleshop.model.service.impl.PuzzleServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.*;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.*;
import static com.rutkouski.puzzleshop.controller.command.PagePath.BASKET_PAGE;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.PUZZLE_ID;

/**
 * The command provides immediate basket recounting
 * while adding item of order
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */
public class RecountOrderWhileAddingItemCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private final PuzzleServiceImpl puzzleService = PuzzleServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        Map<Integer, Integer> basket = (Map<Integer, Integer>) session.getAttribute(BASKET);
        Integer puzzleId = Integer.parseInt(request.getParameter(PUZZLE_ID));
        Integer currentItemQuantity = basket.get(puzzleId);
        basket.put(puzzleId, ++currentItemQuantity);

        Set<Integer> itemIdSet = basket.keySet();
        List<Puzzle> basketItems = new ArrayList<>();
        try {
            for (Integer id : itemIdSet) {
                Optional<Puzzle> puzzle = puzzleService.findPuzzleById(id);
                puzzle.ifPresent(basketItems::add);
            }
            int discount = (int) session.getAttribute(USER_DISCOUNT);
            BigDecimal totalCost = puzzleService.calculatePuzzleSet(basket, discount);

            request.setAttribute(TOTAL_COST, totalCost);
            request.setAttribute(BASKET_ITEMS_LIST, basketItems);
            router.setPagePath(BASKET_PAGE);
        } catch (ServiceException e) {
            logger.error("Error occurred in RecountOrderWhileAddingItemCommand: ", e);
            throw new CommandException("Error occurred in RecountOrderWhileAddingItemCommand: " + e);
        }
        return router;
    }
}
