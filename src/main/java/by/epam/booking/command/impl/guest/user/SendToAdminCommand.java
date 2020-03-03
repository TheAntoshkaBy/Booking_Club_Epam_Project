package by.epam.booking.command.impl.guest.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.command.Router;
import by.epam.booking.type.PageChangeType;

import javax.servlet.http.HttpServletRequest;

public class SendToAdminCommand implements WebCommand {

    private final String PATH_PAGE = "path.page.user.passive";

    @Override
    public Router execute(HttpServletRequest request) {
        Router page = new Router();
        System.out.println(request.getParameter("theme"));
        System.out.println(request.getParameter("message"));
        page.setPage(ConfigurationManager.getProperty(PATH_PAGE));
        page.setPageFormat(PageChangeType.FORWARD);
        return page;
    }
}
