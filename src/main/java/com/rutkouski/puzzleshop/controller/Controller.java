package com.rutkouski.puzzleshop.controller;

import java.io.*;

import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.controller.command.CommandFactory;
import com.rutkouski.puzzleshop.controller.command.ParameterName;
import com.rutkouski.puzzleshop.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.rutkouski.puzzleshop.controller.command.PagePath.ERROR_500_PAGE;

/**
 *
 * The {@link Controller} class is a main HttpServlet.
 * Overrides doPost and doGet methods by calling
 * own method processRequest (request, response).
 */

@WebServlet(name = "mainController", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    static Logger logger = LogManager.getLogger();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }

    private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(ParameterName.COMMAND);
        Command command = CommandFactory.getCommand(commandName);
        Router router = null;
        try {
            router = command.execute(request);
            switch (router.getRoute()) {
                case FORWARD -> request.getRequestDispatcher(router.getPagePath()).forward(request, response);
                case REDIRECT -> response.sendRedirect(request.getContextPath() + router.getPagePath());// TODO: 28.01.2022
            }
        } catch (CommandException e) {
            logger.error("Internal error occurred", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
