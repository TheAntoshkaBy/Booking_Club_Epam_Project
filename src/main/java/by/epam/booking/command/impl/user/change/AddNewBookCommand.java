package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.entity.User;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.command.Router;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;

public class AddNewBookCommand implements WebCommand {

    private static String PATH_PAGE = "path.page.book";

    @Override
    public Router execute(HttpServletRequest request) {
        User user = new User();
        user.setLogin((String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN));
        user.setBookId(Integer.parseInt(request.getParameter(ParameterName.PARAM_BOOK_ID)));
        if(UserLogic.userUpdate(user,user, UserInfoType.BOOK)){
            user = UserLogic.userGet(user,UserInfoType.ALL);
            request.getSession().setAttribute(ParameterName.PARAM_USER_BOOK_NAME,user.getBookName());
            request.getSession().setAttribute(ParameterName.PARAM_USER_BOOK_ID,user.getBookId());
        }

        Book book = new Book();
        book.setId(Integer.parseInt(request.getParameter(ParameterName.PARAM_BOOK_ID)));
        book = BookLogic.bookGet(book, BookInfoType.ALL);
        assert book != null;
        request.setAttribute(ParameterName.PARAM_BOOK_ID, book.getId());
        request.setAttribute(ParameterName.BOOK_NAME_PARAMETER, book.getName());
        request.setAttribute(ParameterName.PARAM_BOOK_AUTHOR,book.getAuthor());
        request.setAttribute(ParameterName.PARAM_BOOK_DESCRIPTION,book.getDescription());
        book.setCount(book.getCount()-1);
        request.setAttribute(ParameterName.PARAM_BOOK_COUNT,book.getCount());

        BookLogic.bookUpdate(book,book,BookInfoType.COUNT);
        Router page = new Router(PageChangeType.FORWARD, ConfigurationManager.getProperty(PATH_PAGE));
        return page;
    }
}
