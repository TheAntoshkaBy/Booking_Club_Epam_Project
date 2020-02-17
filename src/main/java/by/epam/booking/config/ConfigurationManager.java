package by.epam.booking.config;

import by.epam.booking.format.PageFormat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public final class ConfigurationManager {

    private static Properties properties;

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
            InputStream resource = ConfigurationManager.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
