package by.epam.booking.command;

import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface WebCommand {
    Router execute(HttpServletRequest request) throws CommandException;// FIXME: 27.02.2020 оборачивать исключения!
}
