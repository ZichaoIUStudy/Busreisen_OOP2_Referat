package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.objectMethods;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Bus;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Activities to operate Bus Object in DB.
 */
public class BusDB {

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


    /**
     * Insert a single bus.
     * * check if the bus with this Kennzeichen already exists in DB, if not then initialize it.
     * @param conn
     * @param bus
     * @return the Bus id as int
     * @throws SQLException
     */
    public static int initBus(Connection conn, Bus bus) throws SQLException {
        // check if the bus with this Kennzeichen already exists in DB, if not then initialize it
        if (!BusreisenDB.getBus(conn, bus.getKennzeichen()).getKennzeichen().equals(bus.getKennzeichen()))
            return BusreisenDB.initBus(conn, bus);
        else {
            System.out.println("Der Bus mit Kennzeichen '" + bus.getKennzeichen() + "' ist bereits bei der DB!");
            return -1;
        }
    }

    /**
     * To insert multiple buses at once.
     * @param conn
     * @param busse
     * @throws SQLException
     */
    public static void initBusGroup(Connection conn, Bus[] busse) throws SQLException {
        for (Bus bus: busse) {
            initBus(conn, bus);
        }
    }

    /**
     * Update bus, checked by the kennezichen, if it's not found, show warning.
     * @param conn
     * @param bus
     * @throws SQLException
     */
    public static void updateBus(Connection conn, Bus bus) throws SQLException {
        if (BusreisenDB.getBusDBId(conn, bus.getKennzeichen()) == -1)
            System.out.println("Die Daten wurden in der Datenbank nicht gefunden!");
        else BusreisenDB.updateBus(conn, bus, BusreisenDB.getBusDBId(conn, bus.getKennzeichen()));
    }

    /**
     * Get busse by {@param kennzeichen} as a List. (should only have one element, because the kennzeichen is not allowed to be saved doubled)
     * @param conn
     * @param kennzeichen
     * @return return the list of Bus.
     * @throws SQLException
     */
    public static Bus getBus(Connection conn, String kennzeichen) throws SQLException {
        return BusreisenDB.getBus(conn, kennzeichen);
    }
}