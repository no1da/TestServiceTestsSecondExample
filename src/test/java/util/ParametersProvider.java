package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * Класс для работы с конфигурационными свойствами.
 * <p>
 * Этот класс загружает свойства из файла config.properties
 * и предоставляет методы для доступа к ним.
 * </p>
 */
public final class ParametersProvider {
    protected static Properties properties;
    /**
     * Конструктор класса Config.
     * <p>
     * Конструктор загружает свойства из файла config.properties.
     * Если файл не найден, выводится предупреждение в консоль.
     * </p>
     */
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
    /**
     * Получает Property страницы из конфигурационных свойств.
     *
     * @return строка, представляющая содержание Property.
     */
    public static String getApi(final String key) {
        return properties.getProperty(key);
    }

}
