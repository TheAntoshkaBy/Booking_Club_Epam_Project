package by.epam.booking.service.validation;


import by.epam.booking.service.user.CheckUser;

import java.util.regex.Pattern;

public class NewUserValidator {
    private static final String NAME_REG = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
    private static final String SURNAME_REG = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
    private static final String LOGIN_REG = "[A-Za-zА-Яа-я\\d\\-\\_]{0,45}";
    private static final String PASSWORD_REG = "(?=.*[a-zа-я])(?=.*\\d)([A-Za-zА-Яа-я\\d]{8,45})";
    private static final String EMAIL_REG = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}";

    private static NewUserValidator instance;

    private NewUserValidator() {
    }

    public static NewUserValidator getInstance() {
        if (instance == null) {
            instance = new NewUserValidator();
        }
        return instance;
    }

    public boolean isCorrectName(String name) {
        return name != null && Pattern.matches(NAME_REG, name);
    }
    public boolean isCorrectSurName(String surname) {
        return surname != null && Pattern.matches(SURNAME_REG, surname);
    }
    public boolean isCorrectLogin(String login) {
        return login != null && Pattern.matches(LOGIN_REG, login) && !CheckUser.isUserConsist(login);
    }
    public boolean isCorrectPassword(String password) {
        return password != null && Pattern.matches(PASSWORD_REG, password);
    }
    public boolean isCorrectEmail(String email) {
        return email != null && Pattern.matches(EMAIL_REG, email);
    }



}
