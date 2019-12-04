package by.epam.booking.logic.user;

import by.epam.booking.logic.Logic;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.impl.user.search.SearchNameByLogin;
import by.epam.booking.specification.impl.user.search.SearchUserByLogin;
import by.epam.booking.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoByLoginLogic extends Logic {

    public static User searchUserByLogin(String login)
    {
        User buffUser = new User();
        ResultSet userInfo = UserRepository.getINSTANCE().query(new SearchUserByLogin(login));
        try {
            userInfo.next();
               buffUser.setLogin(userInfo.getString("login"));
               buffUser.setName(userInfo.getString("name"));
               buffUser.setSurname(userInfo.getString("surname"));
               buffUser.setEmail(userInfo.getString("email"));
               buffUser.setActive(userInfo.getBoolean("isActive"));
               buffUser.setRole(userInfo.getString("role"));

        } catch (SQLException e) {
            try {
                closeConnection(userInfo.getStatement().getConnection());
                closeStatement(userInfo.getStatement());
                return null;
            } catch (SQLException ex) {
                ex.printStackTrace();
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

    public static String getUserName(String login)
    {
        String name = null;
        ResultSet userInfo = UserRepository.getINSTANCE().query(new SearchNameByLogin(login));
        try {
            userInfo.next();
            name = userInfo.getString("name");
        }catch (SQLException e) {
            try {
                closeConnection(userInfo.getStatement().getConnection());
                closeStatement(userInfo.getStatement());
                return null;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return name;
    }

}
