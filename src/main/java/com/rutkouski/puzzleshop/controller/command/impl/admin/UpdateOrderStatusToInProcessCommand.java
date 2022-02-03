package com.rutkouski.puzzleshop.controller.command.impl.admin;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.Order;
import com.rutkouski.puzzleshop.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.rutkouski.puzzleshop.controller.command.PagePath.TO_ORDER_MANAGEMENT_PAGE;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.ORDER_ID;

/**
 * The command updates the order status
 * from CANCELLED to IN_PROCESS
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */
public class UpdateOrderStatusToInProcessCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private final OrderServiceImpl orderService = OrderServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));

        try {
            orderService.updateOrderStatusById(orderId, Order.Status.IN_PROCESS);
            router.setPagePath(TO_ORDER_MANAGEMENT_PAGE);
            return router;
        } catch (ServiceException e) {
            logger.error("Order status can not be updated now: ", e);
            throw new CommandException("Order status can not be updated now: " + e);
        }
    }
}
