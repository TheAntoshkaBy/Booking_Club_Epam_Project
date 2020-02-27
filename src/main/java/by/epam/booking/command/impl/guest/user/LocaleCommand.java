package by.epam.booking.command.impl.guest.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.command.Router;
import by.epam.booking.type.PageChangeType;

import javax.servlet.http.HttpServletRequest;

public class LocaleCommand implements WebCommand {

    private static final String LOCALE = "locale";
    private static final String RUS = "ru_RU";
    private static final String ENG = "en_US";
    private static final String PATH_PAGE = "path.page.index";

    @Override
    public Router execute(HttpServletRequest request) {
        Router page = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        if (locale.equals(RUS)) {
            request.getSession().setAttribute(LOCALE, ENG);
        } else if (locale.equals(ENG)) {
            request.getSession().setAttribute(LOCALE, RUS);
        } else {
        }

        page.setPage(ConfigurationManager.getProperty(PATH_PAGE));
        page.setPageFormat(PageChangeType.FORWARD);
        return page;
    }
}
