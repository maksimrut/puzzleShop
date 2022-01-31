package com.rutkouski.puzzleshop.controller.command.impl.admin;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.Order;
import com.rutkouski.puzzleshop.model.service.impl.CustomerServiceImpl;
import com.rutkouski.puzzleshop.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.rutkouski.puzzleshop.controller.command.PagePath.TO_ORDER_MANAGEMENT_PAGE;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.ORDER_ID;

public class CompleteOrderCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private static final int MAX_DISCOUNT_VALUE = 15;
    private static final int ADDITION_DISCOUNT_AFTER_ORDER = 1;
    private final OrderServiceImpl orderService = OrderServiceImpl.getInstance();
    private final CustomerServiceImpl customerService = CustomerServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));

        try {
            orderService.updateOrderStatusById(orderId, Order.Status.COMPLETED);
            Optional<Order> optionalOrder = orderService.findOrderById(orderId);
            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();
                int customerId = order.getCustomerId();
                int discount = customerService.findCustomerDiscountById(customerId);
                if (discount < MAX_DISCOUNT_VALUE) {
                    customerService.updateCustomerDiscountById(customerId, discount + ADDITION_DISCOUNT_AFTER_ORDER);
                }
            }
            router.setPagePath(TO_ORDER_MANAGEMENT_PAGE);
            return router;
        } catch (ServiceException e) {
            logger.error("Order can not be completed now: ", e);
            throw new CommandException("Order can not be completed now: ", e);
        }
    }
}
