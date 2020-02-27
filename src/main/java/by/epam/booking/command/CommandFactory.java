package by.epam.booking.command;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

    private static final String HEAD_PARAMETER = "command";
    private static final String PARAMETER_NULL_VALUE = "error_step";

    public WebCommand defineCommand(HttpServletRequest request) {
        WebCommand current = null;
        String action = request.getParameter(HEAD_PARAMETER);
        if (action == null || action.isEmpty()) {
            action = PARAMETER_NULL_VALUE;
        }

        try {
            CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException ignored) {

        }
        return current;
    }

}
// FIXME: 14.02.2020