package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Buchung;

import java.sql.Connection;
import java.sql.SQLException;

public class BuchungenTable {

    // DB Operations
    public static int initBuchung(Connection conn, Buchung buchung) throws SQLException {
        // check if it already exists in DB, if not then initialize it
        if (!(BusreisenDB.getBuchung(conn, buchung.getBuchungsNr()).getBuchungsNr()==(buchung.getBuchungsNr())))
            return BusreisenDB.initBuchung(conn, buchung);
        else {
            System.out.println("Die Buchung mit BuchungsNr " + buchung.getBuchungsNr() + " ist bereits bei der DB!");
            return -1;
        }
    }

    public static void initBuchungenGroup(Connection conn, Buchung[] buchungen) throws SQLException {
        for (Buchung buchung: buchungen) {
            initBuchung(conn, buchung);
        }
    }

    public static void updateBuchung(Connection conn, Buchung buchung) throws SQLException {
        if (BusreisenDB.getBuchungDBId(conn, buchung.getBuchungsNr()) == -1)
            System.out.println("Die Daten wurden in der Datenbank nicht gefunden!");
        else BusreisenDB.updateBuchung(conn, buchung, BusreisenDB.getBuchungDBId(conn, buchung.getBuchungsNr()));
    }

    public static Buchung getBuchung(Connection conn, int buchungsNr) throws SQLException {
        return BusreisenDB.getBuchung(conn, buchungsNr);
    }
}
