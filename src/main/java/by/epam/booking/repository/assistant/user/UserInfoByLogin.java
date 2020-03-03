package by.epam.booking.repository.assistant.user;

import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.impl.user.search.GetListOfCompletedBooksByUserLogin;
import by.epam.booking.specification.impl.user.search.SearchNameByLoginSpecification;
import by.epam.booking.specification.impl.user.search.SearchUserByLoginSpecification;
import by.epam.booking.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserInfoByLogin extends RepositoryHelper {

    private static Logger logger = LogManager.getLogger();

    public static User searchUserByLogin(String login) throws RepositoryException {
        User buffUser = new User();
        ResultSet userInfo = UserRepository.getINSTANCE().query(new SearchUserByLoginSpecification(login));
        try {
            userInfo.next();
               buffUser.setLogin(userInfo.getString("u.login"));
               buffUser.setName(userInfo.getString("u.name"));
               buffUser.setSurname(userInfo.getString("u.surname"));
               buffUser.setEmail(userInfo.getString("u.email"));
               buffUser.setActive(userInfo.getBoolean("u.isActive"));
               buffUser.setRole(userInfo.getString("u.role"));
               buffUser.setBookName(userInfo.getString("b.name"));
               buffUser.setMoneyBalance(userInfo.getDouble("u.moneyBalance"));
               buffUser.setReadingPlanName(userInfo.getString("r.name"));
               buffUser.setReadingPlanId(userInfo.getInt("r.idReadingPlan"));
               buffUser.setBookId(userInfo.getInt("u.bookId"));
        } catch (SQLException e) {
            try {
                closeConnection(userInfo.getStatement().getConnection());
                closeStatement(userInfo.getStatement());
                return null;
            } catch (SQLException | RepositoryException ex) {
                throw new RepositoryException(e);
            }
        }
        try {
            closeConnection(userInfo.getStatement().getConnection());
            closeStatement(userInfo.getStatement());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return buffUser;
    }

    public static String getUserName(String login) throws RepositoryException {
        String name = null;
        ResultSet userInfo = UserRepository.getINSTANCE().query(new SearchNameByLoginSpecification(login));
        try {
            userInfo.next();
            name = userInfo.getString("name");
            closeConnection(userInfo.getStatement().getConnection());
            closeStatement(userInfo.getStatement());
        }catch (SQLException e) {
            try {
                closeConnection(userInfo.getStatement().getConnection());
                closeStatement(userInfo.getStatement());
                return null;
            } catch (SQLException | RepositoryException ex) {
                throw new RepositoryException(e);
            }
        }
        return name;
    }
    public static ArrayList<Integer> getCompletedBooks(String login) throws RepositoryException {
        ArrayList<Integer> booksId = new ArrayList<>();
        ResultSet userInfo = UserRepository.getINSTANCE().query(new GetListOfCompletedBooksByUserLogin(login));
        try {
            while (userInfo.next()){
                booksId.add(userInfo.getInt("bkm.idBook"));
            }
            closeConnection(userInfo.getStatement().getConnection());
            closeStatement(userInfo.getStatement());

        }catch (SQLException | RepositoryException e) {
            try {
                closeConnection(userInfo.getStatement().getConnection());
                closeStatement(userInfo.getStatement());
                return null;
            } catch (SQLException | RepositoryException ex) {
                throw new RepositoryException(e);
            }
        }
        return booksId;
    }
}
