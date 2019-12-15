package by.epam.booking.command.impl.guest.plan;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.entity.ReadingPlan;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;
import by.epam.booking.repository.assistant.plan.GetAllReadingPlans;
import by.epam.booking.service.plan.ReadingPlanInfoType;
import by.epam.booking.service.plan.ReadingPlanLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ToConcreteReadingPlanCommand implements WebCommand {
    private ArrayList<Book> books;
    private ReadingPlan readingPlan;

    @Override
    public PageFormat execute(HttpServletRequest request) {
        readingPlan = new ReadingPlan();
        readingPlan.setIdReadingPlan(Integer.parseInt(request.getParameter("idReadingPlan")));
        ReadingPlanLogic.planGet(readingPlan, ReadingPlanInfoType.GET_ALL_BOOKS_OF_PLAN);
        System.out.println(readingPlan.getBooks());
        PageFormat page = new PageFormat(PageFormatList.FORWARD, ConfigurationManager.getProperty("path.page.plans"));
        return page;
    }
}
