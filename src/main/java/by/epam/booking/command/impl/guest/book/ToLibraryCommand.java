package by.epam.booking.command.impl.guest.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.format.PageFormat;
import by.epam.booking.repository.assistant.book.GetAllBooks;
import by.epam.booking.repository.assistant.user.UserInfoByLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToLibraryCommand implements WebCommand {

    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
        HttpSession session = request.getSession();
        String login = UserInfoByLogin.getUserName((String) session.getAttribute("login"));
        if(login == null){
            request.setAttribute("user", "Guest");
        }else {
            request.setAttribute("user", login);
        }
        request.setAttribute("books", GetAllBooks.getAllBooks());
        page.setPage( ConfigurationManager.getProperty("path.page.library"));

        return page;
    }
}
