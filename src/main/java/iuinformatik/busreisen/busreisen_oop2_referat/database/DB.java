package iuinformatik.busreisen.busreisen_oop2_referat.database;

import iuinformatik.busreisen.busreisen_oop2_referat.GlobaleMethoden;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Alle Methoden ausser connect sind protected
public class DB {

    // Connection
    public static Connection connect() throws SQLException {

        try {
            // Get database credentials from DatabaseConfig class
            var jdbcUrl = DBConfig.getDbUrl();
            var user = DBConfig.getDbUsername();
            var password = DBConfig.getDbPassword();

            // Open a connection
            return DriverManager.getConnection(jdbcUrl, user, password);

        } catch (SQLException  e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    // Table operations
    protected static void drop(Connection conn, Table table) throws SQLException {
        String sql = "DROP TABLE IF EXISTS " + table;
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    protected static void create(Connection conn, Table table) throws SQLException {
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s (id SERIAL PRIMARY KEY)", table);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    protected static void addColumn(Connection conn, Table table, String attribute, DBType attributeType) throws SQLException {
        String sql = String.format("ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s", table, attribute, attributeType.toString());
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    protected static void addArrayColumn(Connection conn, Table table, String attribute, DBType attributeType) throws SQLException {
        String sql = String.format("ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s []", table, attribute, attributeType.toString());
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    protected static void dropColumn(Connection conn, Table table, String attribute) throws SQLException {
        String sql = String.format("ALTER TABLE %s DROP COLUMN IF EXISTS %s", table, attribute);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    // insert functions
    protected static int insertString(Connection conn, Table table, String attribute, String s) throws SQLException {
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

    protected static int insertInt(Connection conn, Table table, String attribute, int i) throws SQLException {
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
    protected static void updateString(Connection conn, Table table, String attribute, String s, int id) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = '%s' WHERE id = %s", table, attribute, s, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    protected static void updateInt(Connection conn, Table table, String attribute, int i, int id) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = %s WHERE id = %s", table, attribute, i, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    protected static void updateDouble(Connection conn, Table table, String attribute, Double d, int id) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = %s WHERE id = %s", table, attribute, d, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    protected static void updateDate(Connection conn, Table table, String attribute, Date date, int id) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = '%s' WHERE id = %s", table, attribute, date, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    protected static void updateTimStamp(Connection conn, Table table, String attribute, Timestamp timeStamp, int id) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = '%s' WHERE id = %s", table, attribute, timeStamp, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    protected static void updateBool(Connection conn, Table table, String attribute, boolean bool, int id) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = %s WHERE id = %s", table, attribute, bool, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    protected static void updateBoolArray(Connection conn, Table table, String attribute, boolean[] boolArray, int id) throws SQLException {
        String sqlArray = "{" + Arrays.toString(boolArray).substring(1, Arrays.toString(boolArray).length()-1) + "}";
        String sql = String.format("UPDATE %s SET %s = '%s' WHERE id = %s", table, attribute, sqlArray, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    // delete function (delete possible only with id)
    protected static void delete(Connection conn, Table table, int id) throws SQLException {
        String sql = String.format("DELETE FROM %s WHERE id = %s", table, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    // select functions
    protected static String selectString(Connection conn, Table table, String attribute, int id) throws SQLException {
        String sql = String.format("SELECT %s FROM %s WHERE id = %s", attribute, table, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getString(attribute);
        }
        return "Error";
    }

    protected static int selectInt(Connection conn, Table table, String attribute, int id) throws SQLException {
        String sql = String.format("SELECT %s FROM %s WHERE id = %s", attribute, table, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getInt(attribute);
        }
        return -1;
    }

    protected static double selectDouble(Connection conn, Table table, String attribute, int id) throws SQLException {
        String sql = String.format("SELECT %s FROM %s WHERE id = %s", attribute, table, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getDouble(attribute);
        }
        return -1;
    }

    protected static Date selectDate(Connection conn, Table table, String attribute, int id) throws SQLException {
        String sql = String.format("SELECT %s FROM %s WHERE id = %s", attribute, table, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getDate(attribute);
        }
        return Date.valueOf("1111-11-11");
    }

    protected static Timestamp selectTimStamp(Connection conn, Table table, String attribute, int id) throws SQLException {
        String sql = String.format("SELECT %s FROM %s WHERE id = %s", attribute, table, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getTimestamp(attribute);
        }
        return Timestamp.valueOf("1111-11-11 0:0:0");
    }

    protected static boolean selectBool(Connection conn, Table table, String attribute, int id) throws SQLException {
        String sql = String.format("SELECT %s FROM %s WHERE id = %s", attribute, table, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getBoolean(attribute);
        }
        return false;
    }

    protected static boolean[] selectBoolArray(Connection conn, Table table, String attribute, int id) throws SQLException {
        String sql = String.format("SELECT %s FROM %s WHERE id = %s", attribute, table, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Array sqlArray = rs.getArray(attribute);
            return GlobaleMethoden.convertToPrimitive((Boolean[]) sqlArray.getArray());
        }
        return new boolean[]{false};
    }

    // select id funtions
    protected static int selectIdPerInt(Connection conn, Table table, String searchAttribute, int i) throws SQLException {
        String sql = String.format("SELECT id FROM %s WHERE %s = %s", table, searchAttribute, i);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getInt("id");
        }
        return -1;
    }

    protected static List<Integer> selectIdsPerInt(Connection conn, Table table, String searchAttribute, int i) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String sql = String.format("SELECT id FROM %s WHERE %s = %s", table, searchAttribute, i);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("id"));
        }
        return ids;
    }

    protected static List<Integer> selectIdsPerString(Connection conn, Table table, String searchAttribute, String s) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String sql = String.format("SELECT id FROM %s WHERE %s = '%s'", table, searchAttribute, s);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("id"));
        }
        return ids;
    }

    protected static List<Integer> selectIdsPerString(Connection conn, Table table, String searchAttribute, String s, String subSearchAttribute, String ss) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String sql = String.format("SELECT id FROM %s WHERE %s = '%s' AND %s = '%s'", table, searchAttribute, s, subSearchAttribute, ss);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("id"));
        }
        return ids;
    }

}