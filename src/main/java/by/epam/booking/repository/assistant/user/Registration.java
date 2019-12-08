package by.epam.booking.repository.assistant.user;

import by.epam.booking.config.MessageManager;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.enumeration.Role;
import by.epam.booking.specification.Specification;
import by.epam.booking.specification.impl.user.search.SearchEmailsByEmail;
import by.epam.booking.specification.impl.user.search.SearchLoginAndPassByLogin;
import by.epam.booking.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Registration extends RepositoryHelper {

    private static String returnedPage;

    public static boolean registration(String login, String password, String name, String surname, String email) {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        user.setActive(false);
        user.setRole(Role.USER);
        if(checkEqualsLogin(new SearchLoginAndPassByLogin(user))){
            returnedPage = MessageManager.getProperty("message.equal.login");
            return false;
        }else if(checkEqualsLogin(new SearchEmailsByEmail(user))){
            returnedPage = MessageManager.getProperty("message.equal.email");
            return false;
        }
        UserRepository.getINSTANCE().add(user);
        return true;
    }

    private static boolean checkEqualsLogin(Specification specification) {
        ResultSet checkLogIn = UserRepository.getINSTANCE().query(specification);
        boolean response = false;
        try {
            checkLogIn.next();
            response = checkLogIn.next();
            closeConnection(checkLogIn.getStatement().getConnection());
            closeStatement(checkLogIn.getStatement());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String getReturnedPage() {
        return returnedPage;
    }

    public static void setReturnedPage(String returnedPage) {
        Registration.returnedPage = returnedPage;
    }
}
