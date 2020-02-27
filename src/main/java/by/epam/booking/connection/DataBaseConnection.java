package by.epam.booking.connection;

import by.epam.booking.config.DataBaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

final class DataBaseConnection {

    private static final String HEAD = "mysql.head";
    private static final String HOST = "mysql.host";
    private static final String PORT = "mysql.port";
    private static final String NAME = "mysql.name";
    private static final String CERTIFICATE = "mysql.verify.server.certificate";
    private static final String SSL = "mysql.useSSL";
    private static final String REQ_SSL = "mysql.requireSSL";
    private static final String DATE_TIME = "mysql.useDateTimeCode";
    private static final String AMP = "mysql.amp";
    private static final String UNICODE = "mysql.useUnicode";
    private static final String TIME_ZONE = "mysql.serverTimeZone";
    private static final String USER = "mysql.user";
    private static final String PASSWORD = "mysql.password";
    private static final String DRIVER = "mysql.driver";

    static {
        loadDriver();
    }

    static Connection createConnection() {
        Connection dbConnection = null;
        String connectionString = DataBaseManager.getProperty(HEAD)     +
                DataBaseManager.getProperty(HOST)                       +
                DataBaseManager.getProperty(PORT)                       +
                DataBaseManager.getProperty(NAME)                       +
                DataBaseManager.getProperty(CERTIFICATE)                +
                DataBaseManager.getProperty(SSL)                        +
                DataBaseManager.getProperty(REQ_SSL)                    +
                DataBaseManager.getProperty(DATE_TIME)                  +
                DataBaseManager.getProperty(AMP)                        +
                DataBaseManager.getProperty(UNICODE)                    +
                DataBaseManager.getProperty(TIME_ZONE);
        try {
            dbConnection = DriverManager.getConnection(connectionString,
                    DataBaseManager.getProperty(USER),
                    DataBaseManager.getProperty(PASSWORD));

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
            Class.forName(DataBaseManager.getProperty(DRIVER));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();// FIXME: 27.02.2020
        }
    }

}
