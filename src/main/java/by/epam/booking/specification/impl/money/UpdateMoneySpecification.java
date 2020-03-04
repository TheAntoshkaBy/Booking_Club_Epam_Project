package by.epam.booking.specification.impl.money;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.exception.SpecificationException;
import by.epam.booking.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//"INSERT INTO Booking_Club.MoneyStory ms (moneyBalance, authorOperationLogin, typeOperation, date) VALUES (?,?,?,?); "
public class UpdateMoneySpecification implements Specification {
   private Double money;
   private String userLogin;
   private String typeOperation;
   private long date;

    private static Logger logger = LogManager.getLogger();
    private String SQL_REQUEST =  "UPDATE "+USER_TABLE+" SET moneyBalance=? WHERE login=?";


    public UpdateMoneySpecification(Double money, String userLogin) {
        this.money = money;
        this.userLogin = userLogin;
    }

    @Override
    public PreparedStatement specify() throws SpecificationException {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_REQUEST);
            statement.setDouble(1,money);
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
