package by.epam.booking.command;

import by.epam.booking.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

    private static final String HEAD_PARAMETER = "command";
    private static final String PARAMETER_NULL_VALUE = "error_step";
    private static Logger logger = LogManager.getLogger();

    public WebCommand defineCommand(HttpServletRequest request) throws CommandException {
        WebCommand current = null;
        String action = request.getParameter(HEAD_PARAMETER);
        if (action == null || action.isEmpty()) {
            action = PARAMETER_NULL_VALUE;
        }

        try {
            CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            logger.error("Command is Error" , e);// FIXME: 27.02.2020 черещ щапятную
            throw new CommandException(e);
        }
        return current;
    }

}
