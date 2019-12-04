package by.epam.booking.command.impl.guest.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.format.PageFormat;
import by.epam.booking.logic.book.GetAllBooksLogic;
import by.epam.booking.logic.user.UserInfoByLoginLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToLibraryCommand implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
        HttpSession session = request.getSession();
        String login = UserInfoByLoginLogic.getUserName((String) session.getAttribute("login"));
        if(login == null){
            request.setAttribute("user", "Guest");
        }else {
            request.setAttribute("user", login);
        }
        request.setAttribute("books",GetAllBooksLogic.getAllBooks());
        System.out.println(GetAllBooksLogic.getBooks());
        page.setPage( ConfigurationManager.getProperty("path.page.library"));
        return page;
    }
}
