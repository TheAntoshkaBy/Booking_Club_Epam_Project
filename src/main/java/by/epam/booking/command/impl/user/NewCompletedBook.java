package by.epam.booking.command.impl.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.User;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class NewCompletedBook implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();

        ArrayList<Integer> booksId = (ArrayList<Integer>) request.getSession().getAttribute("completedBooksId");
        Integer bookId = (Integer) request.getSession().getAttribute("userBookId");
        User user = new User();

        user.setLogin((String) request.getSession().getAttribute("login"));
        user.setBookId(bookId);
        UserLogic.userUpdate(user,user, UserInfoType.ADD_NEW_BOOK_COMPLETED);

        booksId.add(bookId);
        request.getSession().setAttribute("completedBooksId", booksId);
        request.getSession().setAttribute("userBookStatus", "completed");
        request.getSession().setAttribute("type","change");
        page.setPage(ConfigurationManager.getProperty("path.page.user"));
        page.setPageFormat(PageFormatList.FORWARD);
        return page;
    }
}
