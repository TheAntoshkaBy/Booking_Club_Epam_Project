package by.epam.booking.command.impl.guest.plan;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.ReadingPlan;
import by.epam.booking.command.Router;
import by.epam.booking.repository.assistant.plan.GetAllReadingPlans;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ToReadingPlansCommand implements WebCommand {

    private static final String PATH_PAGE = "path.page.plans";

    @Override
    public Router execute(HttpServletRequest request) {

        ArrayList<ReadingPlan> readingPlans = GetAllReadingPlans.getAllPlans();
        request.setAttribute(ParameterName.PARAM_ALL_READING_PLANS, readingPlans);
        Router page = new Router(PageChangeType.FORWARD, ConfigurationManager.getProperty(PATH_PAGE));

        return page;
    }
}
