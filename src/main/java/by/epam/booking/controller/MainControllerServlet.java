package by.epam.booking.controller;

import by.epam.booking.command.CommandFactory;
import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.command.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Main controller servlet.
 * Responsible for the execution of all commands on the site.
 * @since 1.0
 * @author Anton Vedenichev
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/controller")
public class MainControllerServlet extends HttpServlet {

    private final String ERROR_PAGE = "path.page.err.step";
    private static Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CommandFactory commandFactory = new CommandFactory();
        Router page = null;
        WebCommand command = null;
        try {
            command = commandFactory.defineCommand(request);
            page = command.execute(request);
        } catch (CommandException e) {
            logger.error(e);
        }

        if(page == null){
            page = new Router();
            page.setPage(ConfigurationManager.getPath(ERROR_PAGE));
            page.setPageFormat(PageChangeType.REDIRECT);
        }else if(page.getPageFormat() == PageChangeType.FORWARD) {
            getServletContext()
                    .getRequestDispatcher(page.getPage())
                    .forward(request, response);
        }else {
            response.sendRedirect(request.getContextPath() + page.getPage());
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().destroyPool();
        } catch (ConnectionPoolException e) {
            logger.error("Destroy fail " + e);
        }
        super.destroy();
    }
}
