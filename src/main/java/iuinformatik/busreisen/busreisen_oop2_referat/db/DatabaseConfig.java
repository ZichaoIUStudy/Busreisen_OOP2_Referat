package iuinformatik.busreisen.busreisen_oop2_referat.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
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