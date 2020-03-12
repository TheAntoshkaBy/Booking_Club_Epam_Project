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
     * The interface Action command.
     * Perform action according to command that was received from servlet.
     *
     * @param request - get user request
     * @return the router (Page and type of transition method to page)
     * @throws CommandException the command exception
     * @since 1.0
     * @author Anton Vedenichev
     */
    Router execute(HttpServletRequest request) throws CommandException;
}
