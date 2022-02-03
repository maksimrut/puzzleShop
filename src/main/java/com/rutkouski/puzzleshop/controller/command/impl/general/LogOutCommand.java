package com.rutkouski.puzzleshop.controller.command.impl.general;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The command provides session invalidating for {@link User}
 *
 * @see com.rutkouski.puzzleshop.controller.command.Command
 */
public class LogOutCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        session.invalidate();
        logger.debug("Session was finished");
        return router;
    }
}
