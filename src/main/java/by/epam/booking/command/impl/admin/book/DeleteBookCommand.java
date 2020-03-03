package by.epam.booking.command.impl.admin.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.command.Router;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.book.GetAllBooks;
import by.epam.booking.repository.assistant.user.UserInfoByLogin;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class DeleteBookCommand implements WebCommand {

    private static final String GUEST_PARAMETER_VALUE = "Guest";
    private static final String BOOK_ID_EMPTY_PARAMETER_VALUE = null;
    private static final String EMPTY_BOOK_NAME_PARAMETER_VALUE = "--";
    private static final String PAGE_PATH = "path.page.library";
    private static Logger logger = LogManager.getLogger();


    @Override
    public Router execute(HttpServletRequest request) throws RepositoryException {
        Router page = new Router();
        HttpSession session = request.getSession();

        Integer bookId = Integer.parseInt(request.getParameter(ParameterName.PARAM_BOOK_ID));
        Book book = new Book();
        book.setId(bookId);
        BookLogic.bookUpdate(book, book, BookInfoType.DELETE);
        request.getSession().setAttribute(ParameterName.PARAM_USER_BOOK_ID, BOOK_ID_EMPTY_PARAMETER_VALUE);
        request.getSession().setAttribute(ParameterName.PARAM_USER_BOOK_NAME, EMPTY_BOOK_NAME_PARAMETER_VALUE);
        ArrayList<Book> books = GetAllBooks.getAllBooks();

        String login = UserInfoByLogin.getUserName((String) session.getAttribute(ParameterName.PARAM_USER_LOGIN));
        if (login == null) {
            request.setAttribute("user", GUEST_PARAMETER_VALUE);
        } else {
            request.setAttribute("user", login);
            ArrayList<Integer> booksId = (ArrayList<Integer>) request.getSession()
                    .getAttribute(ParameterName.PARAM_LIST_OF_USER_COMPLETED_BOOKS);
            if (booksId != null) {
                for (int i = 0; i < books.size(); i++) {
                    if (booksId.contains(books.get(i).getId())) {
                        books.get(i).setStatus(true);
                    }
                }

            }
        }

        logger.warn("Book delete!");
        request.setAttribute(ParameterName.PARAM_LIST_OF_BOOKS_INTERIM, books);
        page.setPage(ConfigurationManager.getProperty(PAGE_PATH));

        return page;
    }
}
