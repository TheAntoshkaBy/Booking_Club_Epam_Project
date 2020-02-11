package by.epam.booking.command.impl.admin.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.command.validator.UserValidator;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.config.MessageManager;
import by.epam.booking.entity.Book;
import by.epam.booking.entity.User;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddBook implements WebCommand {
    private static final String PARAM_NAME = "name";
    private static final String PARAM_AUTHOR = "author";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_COUNT = "count";

    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
        String name = request.getParameter(PARAM_NAME);
        String author = request.getParameter(PARAM_AUTHOR);
        String description = request.getParameter(PARAM_DESCRIPTION);
        String count = request.getParameter(PARAM_COUNT);
        HttpSession session = request.getSession();

        Book book = new Book(name,author,description,Integer.parseInt(count));
        BookRepository.getInstance().add(book);

        page.setPage( ConfigurationManager.getProperty("path.page.book.add"));
        page.setPageFormat(PageFormatList.FORWARD);
        return page;


    }

}
