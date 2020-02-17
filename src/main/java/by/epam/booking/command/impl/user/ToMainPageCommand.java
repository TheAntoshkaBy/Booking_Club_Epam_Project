package by.epam.booking.command.impl.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.format.PageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ToMainPageCommand implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        if (login == null) {
            page.setPage(ConfigurationManager.getProperty("path.page.registration"));
        } else {
            // FIXME: 11.02.2020
            ArrayList<Integer> booksId = (ArrayList<Integer>) request.getSession().getAttribute("completedBooksId");
            Integer bookId = (Integer) request.getSession().getAttribute("userBookId");
            if (booksId.contains(bookId)) {
                request.getSession().setAttribute("userBookStatus", "completed");
            } else {
                request.getSession().setAttribute("userBookStatus", null);
            }
            request.getSession().setAttribute("type", "see");
            page.setPage(ConfigurationManager.getProperty("path.page.user"));
        }
        return page;
    }
}
