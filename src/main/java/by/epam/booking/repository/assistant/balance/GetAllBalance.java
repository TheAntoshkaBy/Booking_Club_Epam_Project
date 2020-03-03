package by.epam.booking.repository.assistant.balance;

import by.epam.booking.entity.Balance;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.MoneyStoreRepository;
import by.epam.booking.specification.impl.money.GetAllBalanceSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class GetAllBalance extends RepositoryHelper {

    private static Logger logger = LogManager.getLogger();

    public static ArrayList<Balance> getAll() throws RepositoryException, SQLException {
        ArrayList<Balance> balances = new ArrayList<>();
        ResultSet userInfo = MoneyStoreRepository.getINSTANCE().query(new GetAllBalanceSpecification());
        try {
            while (userInfo.next()) {
                Balance balance = new Balance();
                balance.setBalance(userInfo.getDouble("moneyBalance"));
                balance.setIdOperation(userInfo.getInt("idOperation"));
                balance.setAuthorLogin(userInfo.getString("authorOperationLogin"));
                balance.setType(userInfo.getString("typeOperation"));
                Date date = new Date(userInfo.getLong("date"));
                balance.setDate(date);
                balances.add(balance);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {
            closeConnection(userInfo.getStatement().getConnection());
            closeStatement(userInfo.getStatement());
        }
        return balances;
    }
}
