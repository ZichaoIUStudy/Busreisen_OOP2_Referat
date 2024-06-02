package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.objectMethods;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Buchung;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Busreise;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Activities to operate Buchung Object in DB.
 */
public class BuchungDB {

    /**
     * Insert a single Buchung.
     * check if the Buchung with this BuchungsId already exists in DB, if not then initialize it.
     * @param conn
     * @param buchung
     * @return the Buchung id as int
     * @throws SQLException
     */
    public static int initBuchung(Connection conn, Buchung buchung) throws SQLException {
        List<Integer> buchungsNrs = BusreisenDB.getBuchungDBIds(conn, buchung.getBusreise());
        if (buchungsNrs.isEmpty()) {
            buchung.setBuchungsNr(BusreisenDB.initBuchung(conn, buchung));
            return buchung.getBuchungsNr();
        }

        for (int i = 0; i < buchungsNrs.size(); i++) {
            if (buchungsNrs.get(i) != buchung.getBuchungsNr()) {
                buchung.setBuchungsNr(BusreisenDB.initBuchung(conn, buchung));
                return buchung.getBuchungsNr();            }
        }
        System.out.println("Die Buchung mit BuchungsNr " + buchung.getBuchungsNr() + " ist bereits bei der DB!");
        return -1;
    }

    /**
     * * To insert multiple Buchungen at once.
     * @param conn
     * @param buchungen
     * @throws SQLException
     */
    public static void initBuchungenGroup(Connection conn, Buchung[] buchungen) throws SQLException {
        for (Buchung buchung: buchungen) {
            initBuchung(conn, buchung);
        }
    }

    /**
     * Update Buchung, checked by the parameter plz from {@code Busreise}, if it's not found, show warning.
     * @param conn
     * @param buchung
     * @throws SQLException
     */
    public static void updateBuchung(Connection conn, Buchung buchung) throws SQLException {
        if (BusreisenDB.getBuchungByDBId(conn, buchung.getBuchungsNr()).getBusreise().getZielort().getPlz().equals("Error"))
            System.out.println("Die Daten wurden in der Datenbank nicht gefunden!");
        else BusreisenDB.updateBuchung(conn, buchung, buchung.getBuchungsNr());
    }

    /**
     * Get Buchungen by {@param busreise} as a List.
     * @param conn
     * @param busreise
     * @return return the list of Buchungen.
     * @throws SQLException
     */
    public static List<Buchung> getBuchung(Connection conn, Busreise busreise) throws SQLException {
        return BusreisenDB.getBuchung(conn, busreise);
    }
}
