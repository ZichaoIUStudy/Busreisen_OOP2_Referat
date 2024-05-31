package iuinformatik.busreisen.busreisen_oop2_referat;

import iuinformatik.busreisen.busreisen_oop2_referat.database.DB;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;

import java.sql.SQLException;

public class BusreisenVerwaltung {

    public static void showDriverId(String vorname, String name){
        try (var conn =  DB.connect()){
            System.out.println("_________Connected to the PostgreSQL database__________");
            try {
                conn.setAutoCommit(false);

                System.out.println(BusreisenDB.getFahrerDBIds(conn, vorname, name));

                System.out.println("_________Successfully print data from the database__________");
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
                conn.close();
            }
            System.out.println("_________Finished operation in the database__________");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
