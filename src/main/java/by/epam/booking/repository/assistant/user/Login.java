package by.epam.booking.repository.assistant.user;

import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.Specification;
import by.epam.booking.specification.impl.user.search.SearchLoginAndPassByLoginSpecification;
import by.epam.booking.entity.User;
import by.epam.booking.specification.impl.user.search.SearchLoginByLoginSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends RepositoryHelper {

    private static Logger logger = LogManager.getLogger();

    public static boolean checkLogIn(String login, String pass) throws SQLException, RepositoryException {

        if(login.isEmpty() || pass.isEmpty())
            return false;

        User user = new User();
        user.setLogin(login);
        ResultSet checkLogIn = UserRepository.getINSTANCE().query(new SearchLoginAndPassByLoginSpecification(user));

        User checkUser = new User();
        while (checkLogIn.next()){
            checkUser.setLogin(checkLogIn.getString("login"));
            checkUser.setPassword(checkLogIn.getString("password"));
        }
        if(checkUser.getLogin() == null || checkUser.getPassword() == null)
            return false;

        closeConnection(checkLogIn.getStatement().getConnection());
        closeStatement(checkLogIn.getStatement());
        return checkUser.getLogin().equals(login)
                && checkUser.getPassword().equals(pass);
    }

    public static boolean checkLogIn(String login){

        ResultSet loginUser = UserRepository.getINSTANCE().query(new SearchLoginByLoginSpecification(login));
        String searchedLogin=null;
            try {
                while (loginUser.next()){
                    searchedLogin = loginUser.getString("login");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    closeConnection(loginUser.getStatement().getConnection());
                    closeStatement(loginUser.getStatement());
                } catch (SQLException | RepositoryException e) {
                    e.printStackTrace();
                }

            }

        return (searchedLogin != null);
    }

    public static boolean changeLogIn(Specification specification){
        UserRepository.getINSTANCE().query(specification);
        try {
            closeConnection(UserRepository.getINSTANCE().getStatement().getConnection());
            closeStatement(UserRepository.getINSTANCE().getStatement());
        }catch (SQLException | RepositoryException e){
            e.printStackTrace();
        }
        return true;
    }
}
