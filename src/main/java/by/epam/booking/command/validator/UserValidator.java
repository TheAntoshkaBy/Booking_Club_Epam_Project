package by.epam.booking.command.validator;

import by.epam.booking.repository.assistant.user.Login;

import java.sql.SQLException;

public class UserValidator {
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
}
