package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Adresse;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Busreise;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BusreisenTable {

    // DB Operations
    public static int initBusreise(Connection conn, Busreise busreise) throws SQLException {
        // check if it already exists in DB, if not then initialize it
        List<Integer> reiseNrs = BusreisenDB.getBusreiseDBIds(conn, busreise.getZielort());
        if (reiseNrs.isEmpty()) {
            busreise.setReiseNr(BusreisenDB.initBusreise(conn, busreise));
            return busreise.getReiseNr();
        }

        for (int i = 0; i < reiseNrs.size(); i++) {
            if (reiseNrs.get(i) != busreise.getReiseNr()) {
                busreise.setReiseNr(BusreisenDB.initBusreise(conn, busreise));
                return busreise.getReiseNr();
            }
        }
        System.out.println("Die Reise mit ReiseNr " + busreise.getReiseNr() + " ist bereits bei der DB!");
        return -1;
    }

    public static void initBusreisenGroup(Connection conn, Busreise[] busreisen) throws SQLException {
        for (Busreise busreise: busreisen) {
            initBusreise(conn, busreise);
        }
    }

    public static void updateBusreise(Connection conn, Busreise busreise) throws SQLException {
        if (BusreisenDB.getBusreiseByDBId(conn, busreise.getReiseNr()).getZielort().getPlz().equals("Error"))
            System.out.println("Die Daten wurden in der Datenbank nicht gefunden!");
        else BusreisenDB.updateBusreise(conn, busreise, busreise.getReiseNr());
    }

    public static List<Busreise> getBusreise(Connection conn, Adresse zielOrt) throws SQLException {
        return BusreisenDB.getBusreise(conn, zielOrt);
    }
}
