package iuinformatik.busreisen.busreisen_oop2_referat.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB {

    // Connection
    public static Connection connect() throws SQLException {

        try {
            // Get database credentials from DatabaseConfig class
            var jdbcUrl = DatabaseConfig.getDbUrl();
            var user = DatabaseConfig.getDbUsername();
            var password = DatabaseConfig.getDbPassword();

            // Open a connection
            return DriverManager.getConnection(jdbcUrl, user, password);

        } catch (SQLException  e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    // Table operations
    public static void drop(Connection conn, String tableName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DROP TABLE IF EXISTS (?))");
        ps.setObject(1, tableName); // name
        int n = ps.executeUpdate();
    }

    public static void create(Connection conn, String tableName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS (?)) (id SERIAL PRIMARY KEY)");
        ps.setObject(1,tableName); // name
        int n = ps.executeUpdate();
    }

    public static void create(Connection conn, String tableName, String attributeName, DBType attributType) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS (?)) (id SERIAL PRIMARY KEY" +
                ", (?) (?))");
        ps.setObject(1,tableName);
        ps.setObject(2,attributeName);
        ps.setObject(3,attributType);
        int n = ps.executeUpdate();
    }


    // Table functions
    // waiting to be changed.
    public static void insert(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO test (id, num) VALUES (DEFAULT,?)");
        ps.setObject(1, 999); // num
        int n = ps.executeUpdate(); // 1
    }
}