package com.rutkouski.puzzleshop.controller.filter;

import com.rutkouski.puzzleshop.model.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.SESSION_USER;
import static com.rutkouski.puzzleshop.controller.command.PagePath.*;

/**
 * @author Maksim Rutkouski
 * <p>
 * The filter for jsp access for different user roles
 */
@WebFilter(urlPatterns = {"*.jsp"})
public class JspAccessFilter implements Filter {
    static Logger logger = LogManager.getLogger();
    private static final String START_URI = "/index.jsp";
    private Set<String> guestPages;
    private Set<String> customerPages;
    private Set<String> adminPages;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        guestPages = Set.of(INDEX_PAGE
                , MAIN_PAGE
                , ABOUT_US_PAGE
                , GOODS_PAGE
                , REGISTRATION_PAGE
                , ERROR_403_PAGE
                , ERROR_500_PAGE
                , ERROR_404_PAGE);
        customerPages = Set.of(INDEX_PAGE
                , MAIN_PAGE
                , ABOUT_US_PAGE
                , ORDER_INFO_PAGE
                , GO_TO_PROFILE_PAGE
                , UPDATE_PASSWORD_PAGE
                , UPDATE_PROFILE_PAGE
                , GOODS_PAGE
                , BASKET_PAGE
                , CREATED_ORDER_RESULT_PAGE
                , CUSTOMER_ORDERS_LIST_PAGE
                , ERROR_403_PAGE
                , ERROR_500_PAGE
                , ERROR_404_PAGE);
        adminPages = Set.of(INDEX_PAGE
                , MAIN_PAGE
                , ABOUT_US_PAGE
                , ORDER_INFO_PAGE
                , GO_TO_PROFILE_PAGE
                , UPDATE_PASSWORD_PAGE
                , UPDATE_PROFILE_PAGE
                , GOODS_PAGE
                , USER_MANAGEMENT_PAGE
                , ORDER_MANAGEMENT_PAGE
                , EDIT_PUZZLE_PAGE
                , GO_TO_ADD_PUZZLE_PAGE
                , ERROR_403_PAGE
                , ERROR_500_PAGE
                , ERROR_404_PAGE);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String requestURI = httpServletRequest.getServletPath();
        boolean isGuestPage = guestPages.stream().anyMatch(requestURI::contains);
        boolean isCustomerPage = customerPages.stream().anyMatch(requestURI::contains);
        boolean isAdminPage = adminPages.stream().anyMatch(requestURI::contains);
        Object userAttribute = session.getAttribute(SESSION_USER);
        Optional<Object> optionalUser = Optional.ofNullable(userAttribute);
        if (optionalUser.isPresent()) {
            User user = (User) optionalUser.get();
            User.Role role = user.getRole();
            if (role == User.Role.GUEST && isGuestPage) {
                chain.doFilter(httpServletRequest, httpServletResponse);
            } else if (role == User.Role.CUSTOMER && isCustomerPage) {
                chain.doFilter(httpServletRequest, httpServletResponse);
            } else if (role == User.Role.ADMIN && isAdminPage) {
                chain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            if (isGuestPage || requestURI.equals(START_URI)) {
                chain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                logger.warn("Page doesn't exist");
                httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }
    }
}
