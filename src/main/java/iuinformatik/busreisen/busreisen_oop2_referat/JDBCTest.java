package iuinformatik.busreisen.busreisen_oop2_referat;

import java.sql.*;

public class JDBCTest {
    public static void main(String[] args){
        try (var conn =  DB.connect()){
            System.out.println("_________Connected to the PostgreSQL database__________");

            try {
                conn.setAutoCommit(false);
                drop(conn); create(conn); insert(conn);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
                conn.close();
            }

            System.out.println("_________Successfully operated in the database__________");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    static void drop(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DROP TABLE IF EXISTS test");
        int n = ps.executeUpdate();
    }

    static void create(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("CREATE TABLE test (id INT, num INT)");
        int n = ps.executeUpdate();
    }

    static void insert(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO test (id, num) VALUES (?,?)");
        ps.setObject(1, 1); // id
        ps.setObject(2, 999); // num
        int n = ps.executeUpdate(); // 1
    }

}

