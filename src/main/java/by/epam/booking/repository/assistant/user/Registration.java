package by.epam.booking.repository.assistant.user;

import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.type.UserRoleType;
import by.epam.booking.specification.Specification;
import by.epam.booking.specification.impl.user.search.SearchEmailsByEmailSpecification;
import by.epam.booking.specification.impl.user.search.SearchLoginAndPassByLoginSpecification;
import by.epam.booking.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Registration extends RepositoryHelper {

    private static Logger logger = LogManager.getLogger();
    private static String returnedPage;

    public static boolean registration(String login, String password, String name, String surname, String email)
            throws RepositoryException {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        user.setIsActive(false);
        user.setRole(UserRoleType.USER);
        if(checkEqualsLogin(new SearchLoginAndPassByLoginSpecification(user))){
            returnedPage = ConfigurationManager.getMessageProperty("message.equal.login");
            return false;
        }else if(checkEqualsLogin(new SearchEmailsByEmailSpecification(user))){
            returnedPage = ConfigurationManager.getMessageProperty("message.equal.email");
            return false;
        }
        UserRepository.getINSTANCE().add(user);
        return true;
    }

    private static boolean checkEqualsLogin(Specification specification) throws RepositoryException {
        ResultSet checkLogIn = UserRepository.getINSTANCE().query(specification);
        boolean response = false;
        try {
            try {
                checkLogIn.next();
                response = checkLogIn.next();
            }finally {
                closeConnection(checkLogIn.getStatement().getConnection());
                closeStatement(checkLogIn.getStatement());
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException(e);
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
