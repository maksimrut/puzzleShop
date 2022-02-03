package com.rutkouski.puzzleshop.controller.filter;

import com.rutkouski.puzzleshop.exception.ServiceException;
import com.rutkouski.puzzleshop.model.entity.User;
import com.rutkouski.puzzleshop.model.service.impl.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.ACCOUNT_BLOCKAGE_MESSAGE;
import static com.rutkouski.puzzleshop.controller.command.AttributeName.SESSION_USER;
import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

/**
 * @author Maksim Rutkouski
 * <p>
 * The filter for blocking access for users with status BLOCKED
 */
@WebFilter(urlPatterns = {"/*"})
public class StatusAccessFilter implements Filter {
    static Logger logger = LogManager.getLogger();
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute(SESSION_USER);
        try {
            Optional<User.Status> optionalStatus = userService.findUserStatusById(user.getId());
            User.Status status = User.Status.ACTIVE;
            if (optionalStatus.isPresent()) {
                status = optionalStatus.get();
            }
            if (status == User.Status.BLOCKED) {
                User newUser = new User();
                newUser.setRole(User.Role.GUEST);
                session.setAttribute(SESSION_USER, newUser);
                logger.info("An attempt to access from blocked account");
                httpServletResponse.sendError(SC_FORBIDDEN, ACCOUNT_BLOCKAGE_MESSAGE);
            } else {
                chain.doFilter(httpServletRequest, httpServletResponse);
            }
        } catch (ServiceException e) {
            logger.error("Impossible to check the user status , e");
            httpServletResponse.sendError(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
