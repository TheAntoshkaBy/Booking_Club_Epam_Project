package by.epam.booking.command.impl.admin.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.command.Router;
import by.epam.booking.type.PageChangeType;

import javax.servlet.http.HttpServletRequest;

public class GoToAddBookCommand implements WebCommand {

    private static String PAGE_PATH = "path.page.book.add";

    @Override
    public Router execute(HttpServletRequest request) {
        Router page;
        page = new Router(PageChangeType.FORWARD, ConfigurationManager.getPath(PAGE_PATH));
        return page;
    }
}
