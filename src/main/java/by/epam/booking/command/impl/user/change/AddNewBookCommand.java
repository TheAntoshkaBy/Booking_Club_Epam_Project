package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.config.MessageManager;
import by.epam.booking.entity.Book;
import by.epam.booking.entity.User;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;

import javax.servlet.http.HttpServletRequest;

public class AddNewBookCommand implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {
        User user = new User();
        user.setLogin((String) request.getSession().getAttribute("login"));
        user.setBookId(Integer.parseInt(request.getParameter("bookId")));
        if(UserLogic.userUpdate(user,user, UserInfoType.BOOK)){
            user = UserLogic.userGet(user,UserInfoType.ALL);
            request.getSession().setAttribute("bookName",user.getBookName());
            request.getSession().setAttribute("userBookId",user.getBookId());
        }

        Book book = new Book();
        book.setId(Integer.parseInt(request.getParameter("bookId")));
        book = BookLogic.bookGet(book, BookInfoType.ALL);
        assert book != null;
        request.setAttribute("bookId", book.getId());
        request.setAttribute("name", book.getName());
        request.setAttribute("author",book.getAuthor());
        request.setAttribute("description",book.getDescription());
        request.setAttribute("count",book.getCount());

        PageFormat page = new PageFormat(PageFormatList.FORWARD, ConfigurationManager.getProperty("path.page.book"));
        return page;
    }
}
