package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb;

import iuinformatik.busreisen.busreisen_oop2_referat.database.DB;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables.AdressenTable;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables.BusseTable;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables.FahrerTable;

import java.sql.SQLException;

// all the functions which can be directly applied on user interface.
public class BusreisenDBVerwaltung {

    public static void buildTableBase(){
        try (var conn =  DB.connect()){
            System.out.println("_________Connected to the PostgreSQL database__________");
            try {
                conn.setAutoCommit(false);

                BusreisenDB.createTables(conn);
                System.out.println("_________Successfully create tables in the database__________");

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

    public static void insertDefaultData(){

        try (var conn =  DB.connect()){
            System.out.println("_________Connected to the PostgreSQL database__________");
            try {
                conn.setAutoCommit(false);

                BusseTable.createDefaultBusse(conn);
                AdressenTable.createDefaultAdressen(conn);
                FahrerTable.createDefaultFahrer(conn);

                System.out.println("_________Successfully initialize data in the database__________");
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
