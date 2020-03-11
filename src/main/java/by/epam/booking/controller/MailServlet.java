package by.epam.booking.controller;

import by.epam.booking.command.Router;
import by.epam.booking.command.impl.guest.user.message.SendToAdminCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MailServlet")
public class MailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        Router page = null;
        // FIXME: 11.03.2020 убрать этот сервлет.
        SendToAdminCommand sendToAdmin = new SendToAdminCommand();
        page = sendToAdmin.execute(request);

        response.sendRedirect(request.getContextPath() + page.getPage());
    }
}