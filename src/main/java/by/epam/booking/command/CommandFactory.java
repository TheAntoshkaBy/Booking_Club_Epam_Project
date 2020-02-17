package by.epam.booking.command;

import by.epam.booking.command.CommandEnumeration;
import by.epam.booking.command.WebCommand;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

    public WebCommand defineCommand(HttpServletRequest request) {
        WebCommand current = null;
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return null;
        }

        try {
            CommandEnumeration currentEnum = CommandEnumeration.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException ignored) {

        }
        return current;
    }

}
// FIXME: 14.02.2020