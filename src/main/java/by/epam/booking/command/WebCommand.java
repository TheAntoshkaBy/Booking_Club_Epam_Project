package by.epam.booking.command;

import by.epam.booking.format.PageFormat;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public interface WebCommand {
    PageFormat execute(HttpServletRequest request);
}
