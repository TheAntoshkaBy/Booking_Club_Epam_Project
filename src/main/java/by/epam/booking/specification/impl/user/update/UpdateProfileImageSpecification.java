package by.epam.booking.specification.impl.user.update;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.exception.SpecificationException;
import by.epam.booking.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateProfileImageSpecification implements Specification {

    private String userLogin;
    private String profileImage;
    private String SQL_REQUEST = "UPDATE "+USER_TABLE+" SET profile_image=? WHERE login=?";
    private static Logger logger = LogManager.getLogger();

    public UpdateProfileImageSpecification(String userLogin, String profileImage) {
        this.userLogin = userLogin;
        this.profileImage = profileImage;
    }

    @Override
    public PreparedStatement specify() throws SpecificationException {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_REQUEST);
            statement.setString(1,profileImage);
            statement.setString(2,userLogin);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new SpecificationException(e);
        }
        return statement;
    }

    @Override
    public boolean isUpdate() {
        return true;
    }
}
