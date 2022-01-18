package com.rutkouski.puzzleshop.controller.command;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Interface of servlet command
 */

public interface Command {

    Router execute(HttpServletRequest request) throws CommandException;
}
