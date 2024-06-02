package iuinformatik.busreisen.busreisen_oop2_referat.database;

import iuinformatik.busreisen.busreisen_oop2_referat.Util;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This file includes all the basic data communication functions from sql to java.
 * Using sql language, it has mainly 6 different parts, which are:
 * connection,
 * table operations,
 * insert,
 * update,
 * delete,
 * select.
 * It is generally based, so it is kind of independent of this project
 * (some functions are optimally adapted to fit the need of this project though),
 * all functions can not be directly accessed (set as protected) except {@code connect()}.
 */
public class DB {

    /**
     * The most important function, which connects sql database to java.
     * @return return a connection. The promise of the java file operating the data in database.
     * @throws SQLException
     */
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

    /**
     * DB table operations.
     * Including:
     * drop table,
     * create table,
     * add column,
     * add array column,
     * drop column,
     * SQL language is quite sensitive because the communication is the transformed using a String statement.
     * Hence, the direct String as type-in is highly unrecommended and unsafe. Using Enum {@code Table} to choose the insert data name
     * can reduce the risk to cause unexpected security problems. Even the {@code attribute} has type as String,
     * the insert format should be chosen like Table.Tablesname.attribute.
     * All the methods in this section should normally only be used once at the beginning of the application,
     * to initialize the database. The table operations are easily cause the unexpected data lose, or structure errors.
     *
     * @param conn
     * @param table
     * @throws SQLException
     */
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

    /**
     * DB insert data statement.
     * To insert a new line in the DB table. create an autogenerated primary key named as 'id'.
     * Only two ways to insert a new line, either with String {@param s} or with int {@param i}.
     * To initialize an object with multiple different parameters, the init function should include first an insert function,
     * and then add needed update functions after.
     * @param conn
     * @param table
     * @param attribute
     * @param s
     * @return always return the generated id.
     * @throws SQLException
     */
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

    /**
     * DB update data statement.
     * To update the data according to the id. In this section, depending on different data types, the update statements are written in 7 functions:
     * String,
     * Int,
     * Double,
     * Date,
     * Timestamp,
     * Bool,
     * BoolArray.
     * For using Date and Timestamp data type in the code, package {@code java.sql.Date} and {@code java.sql.Timestamp} are needed.
     * Update BoolArray statement is specially listed, because it's necessary for this project to operate with boolean array,
     * which the form of bus seats is saved.
     *
     * @param conn
     * @param table
     * @param attribute
     * @param s
     * @param id
     * @throws SQLException
     */
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

    /**
     * DB delete data statement.
     * Delete can only be accessed by knowing the id of the object.
     * Delete method is not recommend to use, because it will cause the primary key discontinuous,
     * rather update data than delete it.
     * @param conn
     * @param table
     * @param id
     * @throws SQLException
     */
    protected static void delete(Connection conn, Table table, int id) throws SQLException {
        String sql = String.format("DELETE FROM %s WHERE id = %s", table, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        int n = ps.executeUpdate();
    }

    /**
     * DB select data statement.
     * To get the data from DB, only access a single object according to its id (except {@code selectALL()} function).
     *
     * @param conn
     * @param table
     * @param attribute
     * @param id
     * @return different types of value depending on the name of the function.
     * @throws SQLException
     */
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
            return Util.convertToPrimitive((Boolean[]) sqlArray.getArray());
        }
        return new boolean[]{false};
    }

    /**
     * Single statement access the whole table, return the Object with type {@code java.sql.resulSet}.
     * @param conn
     * @param table
     * @return return the whole ResultSet from the sql DB, and it still needs be organized by the further steps to get more data information.
     * @throws SQLException
     */
    protected static ResultSet selectAll(Connection conn, Table table) throws SQLException {
        return conn.createStatement().executeQuery("SELECT * FROM %s;".formatted(table));
    }

    /**
     * To select the id or ids as {@code List<Integer>} of the giving attributes information from the database.
     *
     * @param conn
     * @param table
     * @param searchAttribute
     * @param i
     * @return int id or ids as {@code List<Integer>}
     * @throws SQLException
     */
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