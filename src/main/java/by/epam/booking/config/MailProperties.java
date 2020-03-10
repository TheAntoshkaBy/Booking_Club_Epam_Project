package by.epam.booking.config;

import by.epam.booking.exception.PropertiesException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class MailProperties {

    private static Properties properties;
    private static final String RESOURCE = "mail.properties";
    private static Logger logger = LogManager.getLogger();

    static {
        try {
            loadProperties();
        } catch (PropertiesException e) {
            logger.error("Properties error "+ e);
        }
    }
    private MailProperties() { }
    public static String getProperty(String key) {
        return properties.getProperty(key);

    }

    private static void loadProperties() throws PropertiesException {
        try {
            properties = new Properties();
            InputStream resource = ConfigurationManager.class.getClassLoader().getResourceAsStream(RESOURCE);
            properties.load(resource);
        } catch (IOException e) {
            logger.error("Properties error "+ e);
            throw new PropertiesException(e);
        }
    }


}