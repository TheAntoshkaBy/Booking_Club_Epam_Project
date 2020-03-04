package by.epam.booking.command.impl.guest.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.command.Router;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.book.GetAllBooks;
import by.epam.booking.repository.assistant.user.UserInfoByLogin;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToLibraryCommand implements WebCommand {

    private static final String PATH_PAGE = "path.page.library";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router page = new Router();
        HttpSession session = request.getSession();
        ArrayList<Book> books = null;

        String login = null;
        try {
            books = GetAllBooks.getAllBooks();
            login = UserInfoByLogin.getUserName((String) session.getAttribute(ParameterName.PARAM_USER_LOGIN));
        } catch (RepositoryException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        if (login == null) {
            request.setAttribute(ParameterName.PARAM_USER_INTERIM, "Guest");
        } else {
            request.setAttribute(ParameterName.PARAM_USER_INTERIM, login);
            ArrayList<Integer> booksId = (ArrayList<Integer>) request.getSession()
                    .getAttribute(ParameterName.PARAM_LIST_OF_USER_COMPLETED_BOOKS);

            if (booksId != null) {
                for (Book book : books) {
                    if (booksId.contains(book.getId())) {
                        book.setStatus(true);
                    }
                }

            }
        }
        request.setAttribute(ParameterName.PARAM_LIST_OF_BOOKS_INTERIM, books);
        page.setPage(ConfigurationManager.getProperty(PATH_PAGE));

        return page;
    }
}
