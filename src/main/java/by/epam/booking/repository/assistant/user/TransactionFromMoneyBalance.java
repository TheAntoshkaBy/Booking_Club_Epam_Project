package by.epam.booking.repository.assistant.user;

import by.epam.booking.exception.RepositoryException;
import by.epam.booking.exception.SpecificationException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.specification.Specification;
import by.epam.booking.specification.impl.money.AddMoneyCheckSpecification;
import by.epam.booking.specification.impl.money.GetUserMoneySpecification;
import by.epam.booking.specification.impl.money.UpdateMoneySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionFromMoneyBalance extends RepositoryHelper {

    private static Logger logger = LogManager.getLogger();

    public static boolean moneyExecutor(Double moneyBalanceAdd, String login, String type, long date) throws RepositoryException {

        Statement stGetBalance = null;
        Statement stSetBalance = null;
        Statement stNewMoneyOperation = null;

        try {
            Specification getSpecification = new GetUserMoneySpecification(login);


            ResultSet rsGetBalance = getSpecification.specify().executeQuery();

            stGetBalance = rsGetBalance.getStatement();
            Double newBalance = 0d;
            while (rsGetBalance.next()) {
                newBalance = rsGetBalance.getDouble(1);
            }

            newBalance+= moneyBalanceAdd;

            Specification specificationForSetBalance = new UpdateMoneySpecification(newBalance,login);
            stSetBalance = specificationForSetBalance.specify();

            stGetBalance.getConnection().commit();
            stSetBalance.getConnection().commit();

            Specification specificationSetNewMoneyCheck = new AddMoneyCheckSpecification(moneyBalanceAdd,login,type,date);
            stNewMoneyOperation = specificationSetNewMoneyCheck.specify();


            stNewMoneyOperation.getConnection().commit();
        }catch (SQLException | SpecificationException e) {
            try {
                stGetBalance.getConnection().rollback();
                stSetBalance.getConnection().rollback();
                stNewMoneyOperation.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }finally {
            try {
                closeConnection(stGetBalance.getConnection());
                closeConnection(stSetBalance.getConnection());
                closeConnection(stNewMoneyOperation.getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeStatement(stGetBalance);
            closeStatement(stSetBalance);
            closeStatement(stNewMoneyOperation);
        }

        return true;
    }
}
