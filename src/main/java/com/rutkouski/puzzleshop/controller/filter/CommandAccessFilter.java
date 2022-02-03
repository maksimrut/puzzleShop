package com.rutkouski.puzzleshop.controller.filter;

import com.rutkouski.puzzleshop.controller.command.CommandType;
import com.rutkouski.puzzleshop.model.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Optional;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.SESSION_USER;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.COMMAND;

/**
 * @author Maksim Rutkouski
 * <p>
 * The filter for command access distribution for different user roles
 */
@WebFilter(urlPatterns = {"/*"})
public class CommandAccessFilter implements Filter {
    static Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String commandStr = request.getParameter(COMMAND);
        try {
            CommandType commandType = CommandType.valueOf(commandStr.toUpperCase());
            EnumSet<User.Role> allowedRoles = commandType.getAllowedRoles();
            Optional<Object> optionalUser = Optional.ofNullable(session.getAttribute(SESSION_USER));
            if (optionalUser.isPresent()) {
                User user = (User) optionalUser.get();
                logger.debug("User role is " + user.getRole());
                if (allowedRoles.contains(user.getRole())) {
                    chain.doFilter(httpServletRequest, httpServletResponse);
                } else {
                    httpServletResponse.sendError(404);
                }
            } else {
                if (allowedRoles.contains(User.Role.GUEST)) {
                    chain.doFilter(httpServletRequest, httpServletResponse);
                } else {
                    httpServletResponse.sendError(404);
                }
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.debug("No such command");
            chain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
