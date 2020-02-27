package by.epam.booking.command.impl.admin.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.command.Router;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;

public class ChangeBookAuthorCommand implements WebCommand {

    private final String PATH = "path.page.book";
    private final String VALUE_FOR_PAGE = "settings";

    @Override
    public Router execute(HttpServletRequest request) {
        Router page;
        Book book;
        book = new Book();
        book.setId(Integer.parseInt(request.getParameter(ParameterName.PARAM_BOOK_ID)));
        book = BookLogic.bookGet(book, BookInfoType.ALL);
        request.setAttribute(ParameterName.PARAM_BOOK_ID, book.getId());
        request.setAttribute(ParameterName.BOOK_NAME_PARAMETER, book.getName());
        request.setAttribute(ParameterName.PARAM_BOOK_DESCRIPTION, book.getDescription());
        request.setAttribute(ParameterName.PARAM_BOOK_COUNT, book.getCount());
        request.setAttribute(ParameterName.PARAM_BOOK_COMMENT, book.getComments());

        if (!request.getParameter(ParameterName.PARAM_BOOK_AUTHOR).isEmpty()) {
            book.setAuthor(request.getParameter(ParameterName.PARAM_BOOK_AUTHOR));
            BookLogic.bookUpdate(book, book, BookInfoType.AUTHOR);
            request.setAttribute(ParameterName.PARAM_BOOK_AUTHOR, book.getAuthor());
        }

        request.getSession().setAttribute(ParameterName.PARAM_SETTINGS_FOR_BOOK_PAGE, VALUE_FOR_PAGE);
        page = new Router(PageChangeType.FORWARD, ConfigurationManager.getProperty(PATH));

        return page;
    }
}
