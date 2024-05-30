package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Busreise;

import java.sql.Connection;
import java.sql.SQLException;

public class BusreisenTable {

    // DB Operations
    public static int initBusreise(Connection conn, Busreise busreise) throws SQLException {
        // check if it already exists in DB, if not then initialize it
        if (!(BusreisenDB.getBusreise(conn, busreise.getReiseNr()).getReiseNr()==(busreise.getReiseNr())))
            return BusreisenDB.initBusreise(conn, busreise);
        else {
            System.out.println("Die Reise mit ReiseNr " + busreise.getReiseNr() + " ist bereits bei der DB!");
            return -1;
        }
    }

    public static void initBusreisenGroup(Connection conn, Busreise[] busreisen) throws SQLException {
        for (Busreise busreise: busreisen) {
            initBusreise(conn, busreise);
        }
    }

    public static void updateBusreise(Connection conn, Busreise busreise) throws SQLException {
        if (BusreisenDB.getBusreiseDBId(conn, busreise.getReiseNr()) == -1)
            System.out.println("Die Daten wurden in der Datenbank nicht gefunden!");
        else BusreisenDB.updateBusreise(conn, busreise, BusreisenDB.getBuchungDBId(conn, busreise.getReiseNr()));
    }

    public static Busreise getBusreise(Connection conn, int reiseId) throws SQLException {
        return BusreisenDB.getBusreise(conn, reiseId);
    }
}
