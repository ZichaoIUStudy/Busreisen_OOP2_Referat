package iuinformatik.busreisen.busreisen_oop2_referat.database;

import iuinformatik.busreisen.busreisen_oop2_referat.tables.Table;

import java.sql.*;

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

    public static void addColumn(Connection conn, Table table, String attribute, DBType attributeType) throws SQLException {
        String sql = String.format("ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s", table, attribute, attributeType.toString());
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    public static void addArrayColumn(Connection conn, Table table, String attribute, DBType attributeType) throws SQLException {
        String sql = String.format("ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s []", table, attribute, attributeType.toString());
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    public static void dropColumn(Connection conn, Table table, String attribute) throws SQLException {
        String sql = String.format("ALTER TABLE %s DROP COLUMN IF EXISTS %s", table, attribute);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }


    // Table functions
    // waiting to be changed.
    public static int insertString(Connection conn, Table table, String attribute, String s) throws SQLException {
        String sql = String.format("INSERT INTO %s (%s) VALUES ('%s')", table, attribute, s);
        try (PreparedStatement ps = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {
            int n = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return -1;
            }
        }
    }

    public static int insertInt(Connection conn, Table table, String attribute, int i) throws SQLException {
        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", table, attribute, i);
        try (PreparedStatement ps = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {
            int n = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return -1;
            }
        }
    }

    public static void updateString(Connection conn, Table table, String attribute, String s, int id) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = '%s' WHERE id = %s", table, attribute, s, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    public static void updateDouble(Connection conn, Table table, String attribute, Double d, int id) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = %s WHERE id = %s", table, attribute, d, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

}