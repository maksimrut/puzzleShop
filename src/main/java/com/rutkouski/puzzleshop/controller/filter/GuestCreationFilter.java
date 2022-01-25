package com.rutkouski.puzzleshop.controller.filter;

import com.rutkouski.puzzleshop.model.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.SESSION_USER;

/**
 *
 * The new user (Guest creation) filter.
 */

@WebFilter(urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE})
// TODO: 21.01.2022 learn dispatcher types 
public class GuestCreationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        Optional<Object> userOptional = Optional.ofNullable(session.getAttribute(SESSION_USER));
        if (userOptional.isEmpty()) {
            User user = new User();
            user.setRole(User.Role.GUEST);
            session.setAttribute(SESSION_USER, user);
        }
        chain.doFilter(request, response);
    }
}
