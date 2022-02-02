package com.rutkouski.puzzleshop.controller.command.impl.admin;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.exception.CommandException;
import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.Order;
import com.rutkouski.puzzleshop.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.ORDER_LIST;
import static com.rutkouski.puzzleshop.controller.command.PagePath.ORDER_MANAGEMENT_PAGE;

public class OrderManagementCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private final OrderServiceImpl orderService = OrderServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            List<Order> orders = orderService.findAllOrders();
            request.setAttribute(ORDER_LIST, orders);
            router.setPagePath(ORDER_MANAGEMENT_PAGE);
            return router;
        } catch (ServiceException e) {
            logger.error("Orders can not be found:", e);
            throw new CommandException("Orders can not be found:" + e);
        }
    }
}
