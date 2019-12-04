package by.epam.booking.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageManager {
    private static Properties properties;

    static {
        loadProperties();
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);

    }

    private static void loadProperties() {
        try {
            properties = new Properties();
            InputStream resource = ConfigurationManager.class.getClassLoader().getResourceAsStream("message.properties");
            properties.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private MessageManager() { }
}
