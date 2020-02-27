package by.epam.booking.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigurationManager {

    private static Properties properties;
    private static final String RESOURCE = "config.properties";

    static {
        loadProperties();
    }
    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return properties.getProperty(key);

    }

    private static void loadProperties() {
        try {
            properties = new Properties();
            InputStream resource = ConfigurationManager.class.getClassLoader().getResourceAsStream(RESOURCE);
            properties.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
