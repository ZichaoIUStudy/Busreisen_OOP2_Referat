package iuinformatik.busreisen.busreisen_oop2_referat;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDBVerwaltung;

/**
 * Initialize database in a PostgreSQL database.
 * Including:
 * first step: create all tables
 * second step: insert all the default Data, which are written in DefaultDaten.java
 * under database.busreisendb.table folder.
 * In the Database, there should be already an empty database named "busreisen" existing.
 */
public class DBInitialize {

    public static void main(String args[]) {

        BusreisenDBVerwaltung.createDefaultData();

    }
}
