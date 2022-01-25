package com.rutkouski.puzzleshop.controller.command.impl.general;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.rutkouski.puzzleshop.controller.command.PagePath.MAIN_PAGE;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.SESSION_LOCALE;

public class GoToMainCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(MAIN_PAGE);
        return router;
    }
}
