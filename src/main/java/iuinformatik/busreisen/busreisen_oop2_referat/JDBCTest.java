package iuinformatik.busreisen.busreisen_oop2_referat;

import iuinformatik.busreisen.busreisen_oop2_referat.database.*;
import java.sql.*;

public class JDBCTest {
    public static void main(String[] args){
        try (var conn =  DB.connect()){
            System.out.println("_________Connected to the PostgreSQL database__________");

            try {
                conn.setAutoCommit(false);
                DB.drop(conn, "testTable"); DB.create(conn, "testTable", "name", DBType.VARCHAR); DB.insert(conn);
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

}

