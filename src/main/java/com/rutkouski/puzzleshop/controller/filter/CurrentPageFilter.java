package com.rutkouski.puzzleshop.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.CURRENT_PAGE;

@WebFilter(urlPatterns = {"*.jsp"}, dispatcherTypes = {DispatcherType.FORWARD}
    , initParams = {@WebInitParam(name = "PAGES_ROOT_DIRECTORY", value = "/jsps", description = "Pages Param")
        , @WebInitParam(name = "INDEX_PAGE", value = "/index.jsp", description = "Pages Param")})
public class CurrentPageFilter implements Filter {
    private String rootDirectory;
    private String indexPage;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        rootDirectory = filterConfig.getInitParameter("PAGES_ROOT_DIRECTORY");
        indexPage = filterConfig.getInitParameter("INDEX_PAGE");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        String currentPage = indexPage;
        int rootDirectoryFirstIndex = requestURI.indexOf(rootDirectory);
        if (rootDirectoryFirstIndex != -1) {
            currentPage = requestURI.substring(rootDirectoryFirstIndex);
        }
        httpServletRequest.getSession().setAttribute(CURRENT_PAGE, currentPage);
        chain.doFilter(request, response);
    }
}
