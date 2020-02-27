package by.epam.booking.command.impl.admin.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.command.Router;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;

public class AddBookCommand implements WebCommand {
    private static final String PAGE_PATH = "path.page.book.add";

    @Override
    public Router execute(HttpServletRequest request) {
        Router page = new Router();
        String name = request.getParameter(ParameterName.BOOK_NAME_PARAMETER);
        String author = request.getParameter(ParameterName.PARAM_BOOK_AUTHOR);
        String description = request.getParameter(ParameterName.PARAM_BOOK_DESCRIPTION);
        String count = request.getParameter(ParameterName.PARAM_BOOK_COUNT);

        Book book = new Book(name, author, description, Integer.parseInt(count));
        BookRepository.getInstance().add(book);

        page.setPage(ConfigurationManager.getProperty(PAGE_PATH));
        page.setPageFormat(PageChangeType.FORWARD);
        return page;


    }

}
