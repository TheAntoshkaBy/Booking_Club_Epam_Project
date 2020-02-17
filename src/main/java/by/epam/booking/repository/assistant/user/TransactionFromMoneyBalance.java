package by.epam.booking.repository.assistant.user;

import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.specification.Specification;
import by.epam.booking.specification.impl.money.AddMoneyCheck;
import by.epam.booking.specification.impl.money.GetUserMoney;
import by.epam.booking.specification.impl.money.UpdateMoneySpecification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionFromMoneyBalance extends RepositoryHelper {
    public static boolean moneyExecutor(Double moneyBalanceAdd, String login, String type, long date){

        Statement stGetBalance = null;
        Statement stSetBalance = null;
        Statement stNewMoneyOperation = null;

        try {
            Specification getSpecification = new GetUserMoney(login);


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

            Specification specificationSetNewMoneyCheck = new AddMoneyCheck(moneyBalanceAdd,login,type,date);
            stNewMoneyOperation = specificationSetNewMoneyCheck.specify();


            stNewMoneyOperation.getConnection().commit();
        }catch (SQLException e) {
            System.err.println("SQLState: " + e.getSQLState() + "Error Message: " + e.getMessage());
            // откат транзакции при ошибке
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
