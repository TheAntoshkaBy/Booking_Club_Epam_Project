package by.epam.booking.connection;

import by.epam.booking.config.DataBaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 final class DataBaseConnection {

    static {
        loadDriver();
    }

     static Connection createConnection() {
        Connection dbConnection = null;
        String connectionString = DataBaseManager.getProperty("mysql.head")     +
                DataBaseManager.getProperty("mysql.host")                       +
                DataBaseManager.getProperty("mysql.port")                       +
                DataBaseManager.getProperty("mysql.name")                       +
                DataBaseManager.getProperty("mysql.verify.server.certificate")  +
                DataBaseManager.getProperty("mysql.useSSL")                     +
                DataBaseManager.getProperty("mysql.requireSSL")                 +
                DataBaseManager.getProperty("mysql.useDateTimeCode")            +
                DataBaseManager.getProperty("mysql.amp")                        +
                DataBaseManager.getProperty("mysql.useUnicode")                 +
                DataBaseManager.getProperty("mysql.encoding")                   +
                DataBaseManager.getProperty("mysql.serverTimeZone");
        try {
            dbConnection = DriverManager.getConnection(connectionString,
                    DataBaseManager.getProperty("mysql.user"),
                    DataBaseManager.getProperty("mysql.password"));

            if (!dbConnection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
        } catch (SQLException e) {
            System.out.println("Не удалось загрузить класс драйвера");
        }
        return dbConnection;
    }

    private static void loadDriver() {
        try {
            Class.forName(DataBaseManager.getProperty("mysql.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
