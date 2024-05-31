package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Bus;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class BusseTable {

    // VW 9-Sitzer-Busse
    public static void setBusTypeEins(Bus bus) {
        bus.setBusTyp(BusTyp.KLEINBUS);
        bus.setKostenProKilometer(0.5);
    }

    // Cityliner
    public static void setBusTypeZwei(Bus bus) {
        bus.setBusTyp(BusTyp.REISEBUS);
        bus.setKostenProKilometer(1.2);
    }


    // DB Operations

    public static int initBus(Connection conn, Bus bus) throws SQLException {
        // check if the bus with this Kennzeichen already exists in DB, if not then initialize it
        if (!BusreisenDB.getBus(conn, bus.getKennzeichen()).getKennzeichen().equals(bus.getKennzeichen()))
            return BusreisenDB.initBus(conn, bus);
        else {
            System.out.println("Der Bus mit Kennzeichen '" + bus.getKennzeichen() + "' ist bereits bei der DB!");
            return -1;
        }
    }

    public static void initBusGroup(Connection conn, Bus[] busse) throws SQLException {
        for (Bus bus: busse) {
            initBus(conn, bus);
        }
    }

    public static void updateBus(Connection conn, Bus bus) throws SQLException {
        if (BusreisenDB.getBusDBId(conn, bus.getKennzeichen()) == -1)
            System.out.println("Die Daten wurden in der Datenbank nicht gefunden!");
        else BusreisenDB.updateBus(conn, bus, BusreisenDB.getBusDBId(conn, bus.getKennzeichen()));
    }

    public static Bus getBus(Connection conn, String kennzeichen) throws SQLException {
        return BusreisenDB.getBus(conn, kennzeichen);
    }
}