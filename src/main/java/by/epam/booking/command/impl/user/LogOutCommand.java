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
        request.getSession().setAttribute("user", null);
        request.getSession().setAttribute("surname", null);
        request.getSession().setAttribute("email", null);
        request.getSession().setAttribute("role", null);
        request.getSession().setAttribute("status", null );
        request.getSession().setAttribute("book", null);
        request.getSession().setAttribute("bookName", null);
        return page;
    }
}
