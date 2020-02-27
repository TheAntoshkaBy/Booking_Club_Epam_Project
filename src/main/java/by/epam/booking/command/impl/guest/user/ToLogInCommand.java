package by.epam.booking.command.impl.guest.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.command.Router;
import by.epam.booking.type.PageChangeType;

import javax.servlet.http.HttpServletRequest;

public class ToLogInCommand implements WebCommand {

    private final String PATH_PAGE = "path.page.login";

    @Override
    public Router execute(HttpServletRequest request) {
        Router page = new Router(PageChangeType.REDIRECT, ConfigurationManager.getProperty(PATH_PAGE));
        return page;
    }
}
