package com.rutkouski.puzzleshop.controller.command.impl.customer;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.Order;
import com.rutkouski.puzzleshop.model.entity.OrderItem;
import com.rutkouski.puzzleshop.model.entity.Puzzle;
import com.rutkouski.puzzleshop.model.entity.User;
import com.rutkouski.puzzleshop.model.service.impl.OrderServiceImpl;
import com.rutkouski.puzzleshop.model.service.impl.PuzzleServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.BASKET;
import static com.rutkouski.puzzleshop.controller.command.AttributeName.SESSION_USER;
import static com.rutkouski.puzzleshop.controller.command.PagePath.CREATED_ORDER_RESULT_PAGE;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.ORDER_COST;

public class CreateOrderCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private final PuzzleServiceImpl puzzleService = PuzzleServiceImpl.getInstance();
    private final OrderServiceImpl orderService = OrderServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String orderCost = request.getParameter(ORDER_COST);
        BigDecimal totalCost = new BigDecimal(orderCost);

        User user = (User) session.getAttribute(SESSION_USER);
        int customerId = user.getId();
        Map<Integer, Integer> basket = (Map<Integer, Integer>) session.getAttribute(BASKET);
        Order order = new Order(LocalDate.now(), totalCost, customerId);
        try {
            Order createdOrder = orderService.createOrder(order);
            for (var entry : basket.entrySet()) {
                int puzzleId = entry.getKey();
                int quantity = entry.getValue();
                Optional<Puzzle> puzzle = puzzleService.findPuzzleById(puzzleId);
                if (puzzle.isPresent()) {
                    OrderItem orderItem = new OrderItem(quantity, puzzleId, createdOrder.getId());
                    orderService.createOrderItem(orderItem);
                }
            }
            basket.clear();
            router.setPagePath(CREATED_ORDER_RESULT_PAGE);
//            router.setRoute(Router.RouteType.REDIRECT); todo
        } catch (ServiceException e) {
            logger.error("Order can not be created: ", e);
            throw new CommandException("Order can not be created: ", e);
        }
        return router;
    }
}
