package iuinformatik.busreisen.busreisen_oop2_referat.database;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreiseDB.tables.Table;

import java.sql.*;
import java.util.Arrays;

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

    // insert functions
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

    // update functions
    public static void updateString(Connection conn, Table table, String attribute, String s, int id) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = '%s' WHERE id = %s", table, attribute, s, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    public static void updateInt(Connection conn, Table table, String attribute, int i, int id) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = %s WHERE id = %s", table, attribute, i, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    public static void updateDouble(Connection conn, Table table, String attribute, Double d, int id) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = %s WHERE id = %s", table, attribute, d, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    public static void updateDate(Connection conn, Table table, String attribute, Date date, int id) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = '%s' WHERE id = %s", table, attribute, date, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    public static void updateTimStamp(Connection conn, Table table, String attribute, Timestamp timeStamp, int id) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = '%s' WHERE id = %s", table, attribute, timeStamp, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    public static void updateBoolArray(Connection conn, Table table, String attribute, boolean[] boolArray, int id) throws SQLException {
        String sqlArray = "{" + Arrays.toString(boolArray).substring(1, Arrays.toString(boolArray).length()-1) + "}";
        String sql = String.format("UPDATE %s SET %s = '%s' WHERE id = %s", table, attribute, sqlArray, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    // delete function (delete possible only with id)
    public static void delete(Connection conn, Table table, int id) throws SQLException {
        String sql = String.format("DELETE FROM %s WHERE id = %s", table, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    // select functions
    public static String selectString(Connection conn, Table table, String attribute, int id) throws SQLException {
        String sql = String.format("SELECT %s FROM %s WHERE id = %s", attribute, table, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getString(attribute);
        }
        return "Error";
    }

    public static int selectInt(Connection conn, Table table, String attribute, int id) throws SQLException {
        String sql = String.format("SELECT %s FROM %s WHERE id = %s", attribute, table, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getInt(attribute);
        }
        return -1;
    }

    public static double selectDouble(Connection conn, Table table, String attribute, int id) throws SQLException {
        String sql = String.format("SELECT %s FROM %s WHERE id = %s", attribute, table, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getDouble(attribute);
        }
        return -1;
    }

    public static Date selectDate(Connection conn, Table table, String attribute, int id) throws SQLException {
        String sql = String.format("SELECT %s FROM %s WHERE id = %s", attribute, table, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getDate(attribute);
        }
        return Date.valueOf("0000-00-00");
    }

    public static Timestamp selectTimStamp(Connection conn, Table table, String attribute, int id) throws SQLException {
        String sql = String.format("SELECT %s FROM %s WHERE id = %s", attribute, table, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getTimestamp(attribute);
        }
        return Timestamp.valueOf("0000-00-00");
    }

    public static boolean[] selectBoolArray(Connection conn, Table table, String attribute, int id) throws SQLException {
        String sql = String.format("SELECT %s FROM %s WHERE id = %s", attribute, table, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Array sqlArray = rs.getArray(attribute);
            return convertToPrimitive((Boolean[]) sqlArray.getArray());
        }
        return new boolean[]{false};
    }

    // Boolean[] to boolean[]
    public static boolean[] convertToPrimitive(Boolean[] booleanObjects) {
        // Check if the input array is null
        if (booleanObjects == null) {
            return null;
        }

        // Create a new boolean[] array with the same length as the input array
        boolean[] booleanPrimitives = new boolean[booleanObjects.length];

        // Iterate through the Boolean[] array
        for (int i = 0; i < booleanObjects.length; i++) {
            // Unbox each Boolean object to its boolean primitive value
            // If the Boolean object is null, you can decide how to handle it
            // Here, we assume null should be converted to false
            booleanPrimitives[i] = booleanObjects[i] != null ? booleanObjects[i] : false;
        }

        return booleanPrimitives;
    }

}