package iuinformatik.busreisen.busreisen_oop2_referat.database;

import iuinformatik.busreisen.busreisen_oop2_referat.tables.Table;

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
    public static void drop(Connection conn, Table table) throws SQLException {
        String sql = "DROP TABLE IF EXISTS " + table;
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    public static void create(Connection conn, Table table) throws SQLException {
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s (id SERIAL PRIMARY KEY)", table);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    public static void addColumn(Connection conn, Table table, String attributeName, DBType attributeType) throws SQLException {
        String sql = String.format("ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s", table, attributeName, attributeType.toString());
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    public static void addArrayColumn(Connection conn, Table table, String attributeName, DBType attributeType) throws SQLException {
        String sql = String.format("ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s []", table, attributeName, attributeType.toString());
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    public static void dropColumn(Connection conn, Table table, String attributeName) throws SQLException {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s", table, attributeName);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    // Table functions
    // waiting to be changed.
    public static void insertString(Connection conn, Table table, String attributeName, String string) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO ? (?) VALUES (?)")) {
            ps.setObject(1, table);
            ps.setObject(2, attributeName);
            ps.setObject(3, string);
            int n = ps.executeUpdate(); // 1
        }
    }

    // insert only once, then only update:
    //UPDATE courses
    //SET published_date = '2020-08-01'
    //WHERE course_id = 3;

}