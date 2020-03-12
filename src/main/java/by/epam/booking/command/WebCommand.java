package by.epam.booking.command;

import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * The interface Web command.
 */
public interface WebCommand {
    /**
     * Execute router.
     *
     * @author Anton Vedenichev
     * @param request the request
     * @return the router
     * @throws CommandException the command exception
     */
    Router execute(HttpServletRequest request) throws CommandException;
}
