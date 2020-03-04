package by.epam.booking.servlet;

import by.epam.booking.command.CommandFactory;
import by.epam.booking.command.WebCommand;
import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.exception.ServiceException;
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
import java.sql.SQLException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/controller")
public class MainControllerServlet extends HttpServlet {

    private static Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (CommandException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (CommandException e) {
           throw new  ServletException(e);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {
        CommandFactory commandFactory = new CommandFactory();
        WebCommand command = commandFactory.defineCommand(request);
        Router page = command.execute(request);
        if(page.getPageFormat() == PageChangeType.FORWARD) {
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
