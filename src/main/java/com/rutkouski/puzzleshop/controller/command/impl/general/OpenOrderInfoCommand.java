package com.rutkouski.puzzleshop.controller.command.impl.general;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.*;
import com.rutkouski.puzzleshop.model.service.impl.CustomerServiceImpl;
import com.rutkouski.puzzleshop.model.service.impl.OrderServiceImpl;
import com.rutkouski.puzzleshop.model.service.impl.PuzzleServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.*;
import static com.rutkouski.puzzleshop.controller.command.PagePath.ORDER_INFO_PAGE;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.ORDER_ID;

/**
 * The command shows to {@link User}
 * additional info about certain order
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */
public class OpenOrderInfoCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private final OrderServiceImpl orderService = OrderServiceImpl.getInstance();
    private final CustomerServiceImpl customerService = CustomerServiceImpl.getInstance();
    private final PuzzleServiceImpl puzzleService = PuzzleServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));

        try {
            Optional<Order> optionalOrder = orderService.findOrderById(orderId);
            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();
                int customerId = order.getCustomerId();
                int customerDiscount = customerService.findCustomerDiscountById(customerId);
                List<OrderItem> orderItems = orderService.findAllOrderItemsByOrderId(orderId);
                Map<Puzzle, Integer> puzzleItems = new HashMap<>();
                for (OrderItem orderItem : orderItems) {
                    int puzzleId = orderItem.getPuzzleId();
                    Optional<Puzzle> optionalPuzzle = puzzleService.findPuzzleById(puzzleId);
                    if (optionalPuzzle.isPresent()) {
                        Puzzle puzzle = optionalPuzzle.get();
                        int quantity = orderItem.getItemQuantity();
                        puzzleItems.put(puzzle, quantity);
                    }
                }
                Optional<Customer> optionalCustomer = customerService.findCustomerById(customerId);
                if (optionalCustomer.isPresent()) {
                    Customer customer = optionalCustomer.get();
                    request.setAttribute(ORDER, order);
                    request.setAttribute(DISCOUNT, customerDiscount);
                    request.setAttribute(PUZZLE_MAP, puzzleItems);
                    request.setAttribute(CUSTOMER, customer);
                }
                router.setPagePath(ORDER_INFO_PAGE);
            }
        } catch (ServiceException e) {
            logger.error("Order info can not be opened: ", e);
            throw new CommandException("Order info can not be opened: " + e);
        }
        return router;
    }
}
