package iuinformatik.busreisen.busreisen_oop2_referat.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Using java.io to get the database user information.
 * Data are fetched from application.properties file.
 */
public class DBConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = DBConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                System.exit(1);
            }

            // Load the properties file
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDbUrl() {
        return properties.getProperty("spring.datasource.url");
    }

    public static String getDbUsername() {
        return properties.getProperty("spring.datasource.username");
    }

    public static String getDbPassword() {
        return properties.getProperty("spring.datasource.password");
    }
}
