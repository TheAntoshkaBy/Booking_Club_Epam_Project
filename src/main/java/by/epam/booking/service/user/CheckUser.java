package by.epam.booking.service.user;

import by.epam.booking.entity.User;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.repository.assistant.user.Login;

import java.sql.SQLException;

public class CheckUser {
    public static boolean changeLogin(String login) {
        if (login == null || login.isEmpty())
            return false;
        return !Login.isLoginExist(login);
    }

    public static boolean isUserConsist(String login, String password) throws ServiceException {
        boolean answer = false;
        try {
            answer = Login.checkLogIn(login, password);
        } catch (SQLException | RepositoryException e) {
            throw new ServiceException(e);
        }
        return answer;
    }

    public static boolean userIsActive(User user)
    {
        return user.isActive();
    }
}
