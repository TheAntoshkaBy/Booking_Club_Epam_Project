package by.epam.booking.command;

import javax.servlet.http.HttpServletRequest;

public interface WebCommand {
    Router execute(HttpServletRequest request);
}
