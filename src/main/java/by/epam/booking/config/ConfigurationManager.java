package by.epam.booking.config;

import by.epam.booking.exception.PropertiesException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigurationManager {

    private static final String RESOURCE = "config.properties";
    private final static String DB_RESOURCE = "database.properties";
    private static final String MAIL_RESOURCE = "mail.properties";
    private static final String MESS_RESOURCE = "message.properties";
    private static Properties pathProperties;
    private static Properties dataBaseProperties;
    private static Properties mailProperties;
    private static Properties messageProperties;
    private static Logger logger = LogManager.getLogger();

    static {
        try {
            loadProperties();
        } catch (PropertiesException e) {
            logger.error("Properties error" + e);
        }
    }

    private ConfigurationManager() {
    }

    public static String getPath(String key) {
        return pathProperties.getProperty(key);
    }

    public static String getBaseProperty(String key) {
        return dataBaseProperties.getProperty(key);
    }

    public static String getMailProperty(String key) {
        return mailProperties.getProperty(key);
    }

    public static String getMessageProperty(String key) {
        return messageProperties.getProperty(key);
    }

    private static void loadProperties() throws PropertiesException {
        try {

            pathProperties = new Properties();
            InputStream resource = ConfigurationManager.class.getClassLoader().getResourceAsStream(RESOURCE);
            pathProperties.load(resource);

            dataBaseProperties = new Properties();
            InputStream dataBaseLoad = ConfigurationManager.class.getClassLoader().getResourceAsStream(DB_RESOURCE);
            dataBaseProperties.load(dataBaseLoad);

            mailProperties = new Properties();
            InputStream mailLoad = ConfigurationManager.class.getClassLoader().getResourceAsStream(MAIL_RESOURCE);
            mailProperties.load(mailLoad);


            messageProperties = new Properties();
            InputStream messageLoad = ConfigurationManager.class.getClassLoader().getResourceAsStream(MESS_RESOURCE);
            messageProperties.load(messageLoad);


        } catch (IOException e) {
            logger.error("Properties error" + e);
            throw new PropertiesException(e);
        }
    }


}
