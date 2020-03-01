package by.epam.booking.command.impl.guest.plan;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.ReadingPlan;
import by.epam.booking.command.Router;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.service.plan.ReadingPlanInfoType;
import by.epam.booking.service.plan.ReadingPlanLogic;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ToConcreteReadingPlanCommand implements WebCommand {

    private static final String PATH_PAGE = "path.page.plan.book";

    @Override
    public Router execute(HttpServletRequest request) throws SQLException, RepositoryException {

        ReadingPlan readingPlan = new ReadingPlan();
        readingPlan.setIdReadingPlan(Integer.parseInt(request.getParameter(ParameterName.PARAM_READING_PLAN_ID)));
        ReadingPlanLogic.planGet(readingPlan, ReadingPlanInfoType.GET_ALL_BOOKS_OF_PLAN);
        request.getSession().setAttribute(ParameterName.PARAM_READING_PLAN_BOOKS, readingPlan.getBooks());
        Router page = new Router(PageChangeType.FORWARD, ConfigurationManager.getProperty(PATH_PAGE));

        return page;
    }
}
