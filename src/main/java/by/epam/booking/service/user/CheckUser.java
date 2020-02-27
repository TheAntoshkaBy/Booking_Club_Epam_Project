package by.epam.booking.service.user;

import by.epam.booking.entity.User;
import by.epam.booking.repository.assistant.user.Login;

import java.sql.SQLException;

public class CheckUser {
    public static boolean changeLogin(String login) {
        if (login == null || login.isEmpty())
            return false;
        return !Login.isLoginExist(login);
    }

    public static boolean isUserConsist(String login, String password) {
        boolean answer = false;
        try {
            answer = Login.checkLogIn(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public static boolean userIsActive(User user)
    {
        return user.isActive();
    }
}
