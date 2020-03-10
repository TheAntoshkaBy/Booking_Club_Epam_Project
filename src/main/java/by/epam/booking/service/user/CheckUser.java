package by.epam.booking.service.user;

import by.epam.booking.entity.User;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.repository.assistant.user.Login;
import by.epam.booking.service.validation.NewUserValidator;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
        return user.getIsActive();
    }

    public static Map<String, Boolean> checkUserDataIsCorrect(
            String name,
            String surname,
            String login,
            String password,
            String email)
            throws ServiceException {

        Map<String, Boolean> map = new HashMap<>();

        boolean incorrectName = !NewUserValidator.getInstance().isCorrectName(name);
        boolean incorrectSurname = !NewUserValidator.getInstance().isCorrectSurName(surname);
        boolean incorrectLogin = !NewUserValidator.getInstance().isCorrectLogin(login);
        boolean incorrectPass = !NewUserValidator.getInstance().isCorrectPassword(password);
        boolean incorrectEmail = !NewUserValidator.getInstance().isCorrectEmail(email);

        map.put(ParameterName.PARAM_USERNAME_ERROR, incorrectName);
        map.put(ParameterName.PARAM_SURNAME_ERROR, incorrectSurname);
        map.put(ParameterName.PARAM_LOGIN_ERROR, incorrectLogin);
        map.put(ParameterName.PARAM_PASS_ERROR, incorrectPass);
        map.put(ParameterName.PARAM_EMAIL_ERROR, incorrectEmail);

        return map;
    }
}
