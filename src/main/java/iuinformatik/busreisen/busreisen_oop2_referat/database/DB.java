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
        String safeTableName = tableName.trim();
        String sql = "DROP TABLE IF EXISTS " +safeTableName;
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    public static void create(Connection conn, String tableName) throws SQLException {
        String safeTableName = tableName.trim();
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s (id SERIAL PRIMARY KEY)",safeTableName);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    public static void addColumn(Connection conn, String tableName, String attributeName, DBType attributeType) throws SQLException {
        String safeTableName = tableName.trim();
        String safeAttributeName = attributeName.trim();
        String sql = String.format("ALTER TABLE %s ADD %s %s", safeTableName, safeAttributeName, attributeType);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    public static void dropColumn(Connection conn, String tableName, String attributeName) throws SQLException {
        String safeTableName = tableName.trim();
        String safeAttributeName = attributeName.trim();
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s", safeTableName,safeAttributeName);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    // Table functions
    // waiting to be changed.
    public static void insert(Connection conn, int id, int grade, String name, String gender) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO students (id, grade, name, gender) VALUES (?,?,?,?)")) {
            ps.setObject(1, id); // 注意：索引从1开始
            ps.setObject(2, grade); // grade
            ps.setObject(3, name); // name
            ps.setObject(4, gender); // gender
            int n = ps.executeUpdate(); // 1
        }
    }
}