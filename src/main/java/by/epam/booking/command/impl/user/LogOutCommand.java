package by.epam.booking.command.impl.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.format.PageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements WebCommand {

    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
        page.setPage( ConfigurationManager.getProperty("path.page.login"));
        HttpSession session = request.getSession();
        session.setAttribute("login", null);
        return page;
    }
}
