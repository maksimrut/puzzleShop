package com.rutkouski.puzzleshop.controller;

import com.rutkouski.puzzleshop.controller.command.PagePath;

/**
 * @author Maksim Rutkouski
 *
 * The {@link Router} class contains
 * pagePath and route fields for request and response
 * RouteType defining
 */

public class Router {
    public enum RouteType {
        FORWARD,
        REDIRECT
    }

    private String pagePath = PagePath.INDEX_PAGE;
    private RouteType route = RouteType.FORWARD;

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public RouteType getRoute() {
        return route;
    }

    public void setRoute(RouteType route) {
        if (route != null) {
            this.route = route;
        }
    }
}
