package by.epam.booking.servlet;

import by.epam.booking.command.CommandFactory;
import by.epam.booking.command.WebCommand;
import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.command.Router;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/controller")
public class MainControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandFactory commandFactory = new CommandFactory();
        WebCommand command = commandFactory.defineCommand(request);
        Router page = command.execute(request);
        if(page.getPageFormat() == PageChangeType.FORWARD)
        {
            getServletContext()
                    .getRequestDispatcher(page.getPage())
                    .forward(request, response);
        }else {
            response.sendRedirect(request.getContextPath() + page.getPage());
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
        super.destroy();
    }
}
