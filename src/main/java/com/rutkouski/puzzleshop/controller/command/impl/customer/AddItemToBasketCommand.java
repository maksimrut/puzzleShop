package com.rutkouski.puzzleshop.controller.command.impl.customer;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.BASKET;
import static com.rutkouski.puzzleshop.controller.command.PagePath.SHOW_ALL_GOODS_PAGE;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.PUZZLE_ID;

/**
 * The command provides adding to the basker
 * chosen by {@link User} goods
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */
public class AddItemToBasketCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        Map<Integer, Integer> basket = (Map<Integer, Integer>) session.getAttribute(BASKET);

        if (basket == null) {
            basket = new HashMap<>();
            session.setAttribute(BASKET, basket);
        }
        Integer puzzleId = Integer.parseInt(request.getParameter(PUZZLE_ID));
        if (!basket.containsKey(puzzleId)) {
            basket.put(puzzleId, 0);
        }
        Integer currentItemQuantity = basket.get(puzzleId);
        basket.put(puzzleId, ++currentItemQuantity);

        router.setPagePath(SHOW_ALL_GOODS_PAGE);
        logger.debug("AddItemToBasketCommand was completed successfully");
        return router;
    }
}
