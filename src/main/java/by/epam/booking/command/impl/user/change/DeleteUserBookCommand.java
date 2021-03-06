package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.command.impl.BookingClubCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.entity.User;
import by.epam.booking.command.Router;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserBookCommand extends BookingClubCommand implements WebCommand {

    private static final String PARAM_VALUE_TO_PAGE = "change";
    private static final String PARAM_VALUE_BOOK_NAME = "--";
    private static final String PATH_PAGE = "path.page.user";
    private static final String MESSAGE_SAVE_CHANGED = "message.changed.Save";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router page = new Router();
        User user = new User();
        user.setLogin((String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN));
        user.setBookId(null);
        try {
            if(userLogic.userUpdate(user,user,UserInfoType.BOOK)){

                Book book = new Book();
                book.setId((Integer) request.getSession().getAttribute(ParameterName.PARAM_USER_BOOK_ID));
                book = bookLogic.bookGet(book,BookInfoType.ALL);
                book.setCount(book.getCount()+1);
                bookLogic.bookUpdate(book,book, BookInfoType.COUNT);

                request.getSession().setAttribute(ParameterName.PARAM_USER_BOOK_NAME,PARAM_VALUE_BOOK_NAME);
                request.getSession().setAttribute(ParameterName.PARAM_USER_BOOK_ID,null);
                logger.debug("User book "+ book + " deleted");
            }
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        page.setPageFormat(PageChangeType.REDIRECT);
        page.setPage(ConfigurationManager.getPath(PATH_PAGE));
        request.getSession().setAttribute(ParameterName.PARAM_TYPE_PROFILE,PARAM_VALUE_TO_PAGE);
        request.getSession().setAttribute(ParameterName.PARAM_CHANGE_SAVED,
                ConfigurationManager.getMessageProperty(MESSAGE_SAVE_CHANGED));
        return page;
    }
}
