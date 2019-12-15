package by.epam.booking.command.impl.guest.plan;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.entity.ReadingPlan;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;
import by.epam.booking.repository.assistant.book.GetAllBooks;
import by.epam.booking.repository.assistant.plan.GetAllReadingPlans;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ToReadingPlansCommand implements WebCommand {
    private ArrayList<ReadingPlan> readingPlans;

    @Override
    public PageFormat execute(HttpServletRequest request) {
        readingPlans = GetAllReadingPlans.getAllPlans();
        request.setAttribute("plans", readingPlans);
        PageFormat page = new PageFormat(PageFormatList.FORWARD, ConfigurationManager.getProperty("path.page.plans"));
        return page;
    }
}
