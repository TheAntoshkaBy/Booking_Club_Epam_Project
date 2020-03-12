package by.epam.booking.command.impl.admin.book;

import by.epam.booking.command.Router;
import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class AddBookCommand implements WebCommand {

    private static final String PAGE_PATH_TO_ADD_BOOK = "path.page.book.add";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router page = new Router();
        String name = request.getParameter(ParameterName.BOOK_NAME_PARAMETER);
        String author = request.getParameter(ParameterName.PARAM_BOOK_AUTHOR);
        String description = request.getParameter(ParameterName.PARAM_BOOK_DESCRIPTION);
        String count = request.getParameter(ParameterName.PARAM_BOOK_COUNT);

        Book book = new Book(name, author, description, Integer.parseInt(count));
        try {
            BookRepository.getInstance().add(book);
        } catch (RepositoryException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        logger.debug("Add book " + book + " to library");
        page.setPage(ConfigurationManager.getPath(PAGE_PATH_TO_ADD_BOOK));
        page.setPageFormat(PageChangeType.REDIRECT);
        return page;


    }

}
