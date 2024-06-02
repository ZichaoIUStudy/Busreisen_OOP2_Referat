package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb;

import iuinformatik.busreisen.busreisen_oop2_referat.database.DB;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables.DefaultDaten;

import java.sql.SQLException;

/**
 * Two functions in this class are used to initialize default data in database "busreisen".
 * BusreisenDB.createTables();
 * DefaultDaten.defaultDatenBank();
 */
public class BusreisenDBVerwaltung {

    public static void createDefaultData(){

        try (var conn =  DB.connect()){
            System.out.println("_________Connected to the PostgreSQL database__________");
            try {
                conn.setAutoCommit(false);

                BusreisenDB.createTables(conn);
                DefaultDaten.defaultDatenBank(conn);

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
