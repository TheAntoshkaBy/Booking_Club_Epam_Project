package by.epam.booking.repository.assistant.user;

import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.impl.user.search.GetUserBookNameByIdBook;
import by.epam.booking.specification.impl.user.search.SearchNameByLogin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetBookName extends RepositoryHelper {
    public static String getBookName(String login)
    {
        String name = null;
        ResultSet userInfo = UserRepository.getINSTANCE().query(new GetUserBookNameByIdBook(login));
        try {
            userInfo.next();
            name = userInfo.getString("b.name");
        }catch (SQLException e) {

        }finally {
            try {
                closeConnection(userInfo.getStatement().getConnection());
                closeStatement(userInfo.getStatement());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return name;
    }
}
