package com.rutkouski.puzzleshop.controller.command.impl.general;

import com.rutkouski.puzzleshop.controller.Router;
import com.rutkouski.puzzleshop.controller.command.Command;
import com.rutkouski.puzzleshop.validator.LocaleValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.rutkouski.puzzleshop.controller.command.AttributeName.CURRENT_PAGE;
import static com.rutkouski.puzzleshop.controller.command.ParameterName.SESSION_LOCALE;

public class ChangeLocaleCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        String newLocale = request.getParameter(SESSION_LOCALE);
        if (LocaleValidator.validate(newLocale)) {
            session.setAttribute(SESSION_LOCALE, newLocale);
        } else {
            logger.warn("Incorrect locale parameter: {}", newLocale);
        }
        router.setPagePath(currentPage);
        return router;
    }
}
