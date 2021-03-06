package by.epam.booking.command.impl.guest.plan;

import by.epam.booking.command.WebCommand;
import by.epam.booking.command.impl.BookingClubCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.entity.ReadingPlan;
import by.epam.booking.command.Router;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.service.plan.ReadingPlanInfoType;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ToConcreteReadingPlanCommand extends BookingClubCommand implements WebCommand {

    private static final String PATH_PAGE = "path.page.plan.book";
    private static final String GUEST = "Guest";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        ReadingPlan readingPlan = new ReadingPlan();
        readingPlan.setIdReadingPlan(Integer.parseInt(request.getParameter(ParameterName.PARAM_READING_PLAN_ID)));
        request.getSession().getAttribute(ParameterName.PARAM_NAME_OF_READING_PLAN);//
        try {
            readingPlanLogic.planGet(readingPlan, ReadingPlanInfoType.GET_ALL_BOOKS_OF_PLAN);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        String login = (String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN);
        if (login == null) {
            request.setAttribute(ParameterName.PARAM_USER_INTERIM, GUEST);
        } else {
            request.setAttribute(ParameterName.PARAM_USER_INTERIM, login);
            ArrayList<Integer> booksId = (ArrayList<Integer>) request.getSession()
                    .getAttribute(ParameterName.PARAM_LIST_OF_USER_COMPLETED_BOOKS);

            if (booksId != null) {
                for (Book book : readingPlan.getBooks()) {
                    if (booksId.contains(book.getId())) {
                        book.setStatus(true);
                    }
                }

            }
        }

        request.getSession().setAttribute(ParameterName.PARAM_READING_PLAN_ID_INTERIM, readingPlan.getIdReadingPlan());
        request.getSession().setAttribute(ParameterName.PARAM_READING_PLAN_BOOKS, readingPlan.getBooks());
        Router page = new Router(PageChangeType.FORWARD, ConfigurationManager.getPath(PATH_PAGE));

        return page;
    }
}
