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

    public static boolean moneyExecutor(Double moneyBalanceAdd, String login, String type, long date)
            throws RepositoryException, SQLException {

        Statement stGetBalance = null;
        Statement stSetBalance = null;
        Statement stNewMoneyOperation = null;

        try {
            try {
                Specification getSpecification = new GetUserMoneySpecification(login);

                ResultSet rsGetBalance = getSpecification.specify().executeQuery();//k1

                stGetBalance = rsGetBalance.getStatement();
                System.out.println(stGetBalance.getConnection().getAutoCommit());
                Double newBalance = 0d;
                while (rsGetBalance.next()) {
                    newBalance = rsGetBalance.getDouble(1);
                }

                newBalance+= moneyBalanceAdd;

                Specification specificationForSetBalance = new UpdateMoneySpecification(newBalance,login);//k2
                stSetBalance = specificationForSetBalance.specify();

                System.out.println(stSetBalance.getConnection().getAutoCommit());

                stGetBalance.getConnection().commit();
                stSetBalance.getConnection().commit();

                System.out.println(stGetBalance.getConnection().getAutoCommit());
                System.out.println(stSetBalance.getConnection().getAutoCommit());
                Specification specificationSetNewMoneyCheck = new AddMoneyCheckSpecification(moneyBalanceAdd,login,type,date);
                stNewMoneyOperation = specificationSetNewMoneyCheck.specify();
                System.out.println(stNewMoneyOperation.getConnection().getAutoCommit());
                stNewMoneyOperation.getConnection().commit();
                System.out.println(stNewMoneyOperation.getConnection().getAutoCommit());
            }finally {

                stGetBalance.getConnection().setAutoCommit(true);
                stSetBalance.getConnection().setAutoCommit(true);
                stNewMoneyOperation.getConnection().setAutoCommit(true);

                closeConnection(stGetBalance.getConnection());
                closeConnection(stSetBalance.getConnection());
                closeConnection(stNewMoneyOperation.getConnection());
            }

        } catch (SQLException | SpecificationException e){
            stGetBalance.getConnection().rollback();
            stSetBalance.getConnection().rollback();
            stNewMoneyOperation.getConnection().rollback();
            logger.error(e);
            throw new RepositoryException(e);
        }
        return true;
    }
}
