package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс для конфигурации Properties
 */
public class ParametersProvider {
    private Properties properties;

    public ParametersProvider() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getAppUrl() {
        return properties.getProperty("app.url");
    }

}
