package com.rutkouski.puzzleshop.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.CURRENT_PAGE;

/**
 * @author Maksim Rutkouski
 * <p>
 * The filter for creating current page for locale changing
 */
@WebFilter(urlPatterns = {"/*"})
public class CurrentPageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String currentPage = httpServletRequest.getRequestURL().toString();

        if (currentPage.contains("jsp/")) {
            int index = currentPage.indexOf("jsp/");
            currentPage = currentPage.substring(index);
            httpServletRequest.getSession().setAttribute(CURRENT_PAGE, currentPage);
        } else if (currentPage.contains("controller") && !httpServletRequest.getParameterMap().isEmpty()
                && httpServletRequest.getQueryString() != null
                && !httpServletRequest.getQueryString().contains("command=change_locale")) {
            int index = currentPage.indexOf("controller");
            currentPage = currentPage.substring(index) + "?" + httpServletRequest.getQueryString();
            httpServletRequest.getSession().setAttribute(CURRENT_PAGE, currentPage);
        }
        chain.doFilter(httpServletRequest, response);
    }
}
