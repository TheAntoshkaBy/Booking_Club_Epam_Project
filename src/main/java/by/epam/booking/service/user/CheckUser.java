package by.epam.booking.service.user;

import by.epam.booking.entity.User;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.repository.assistant.user.Login;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class CheckUser {

    private static Logger logger = LogManager.getLogger();

    public static boolean changeLogin(String login) throws ServiceException{
        if (login == null || login.isEmpty())
            return false;
        try {
            return !Login.checkLogIn(login);
        } catch (RepositoryException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public static boolean isUserConsist(String login, String password) throws ServiceException {
        boolean answer = false;
        try {
            answer = Login.checkLogIn(login, password);
        } catch (RepositoryException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return answer;
    }

    public static boolean isUserConsist(String login) throws ServiceException {
        boolean answer = false;
        try {
            answer = Login.checkLogIn(login);
        } catch (RepositoryException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return answer;
    }

    public static boolean userIsActive(User user)
    {
        return user.isActive();
    }
}
