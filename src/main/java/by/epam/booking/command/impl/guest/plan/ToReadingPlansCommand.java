package by.epam.booking.command.impl.guest.plan;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.ReadingPlan;
import by.epam.booking.command.Router;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.plan.GetAllReadingPlans;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToReadingPlansCommand implements WebCommand {

    private static final String PATH_PAGE = "path.page.plans";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        ArrayList<ReadingPlan> readingPlans = null;
        try {
            readingPlans = GetAllReadingPlans.getAllPlans();
        } catch (RepositoryException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(ParameterName.PARAM_ALL_READING_PLANS, readingPlans);
        Router page = new Router(PageChangeType.FORWARD, ConfigurationManager.getProperty(PATH_PAGE));

        return page;
    }
}
