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

    private static Bus[] defaultBusseTable() {
        Bus vw01 = new Bus();
        setBusTypeEins(vw01);
        vw01.setKennzeichen("L-EK 6789");
        vw01.setZulassung(Date.valueOf("2021-03-01"));
        vw01.setTuevTermin(Date.valueOf("2025-03-01"));
        vw01.setGefahreneKilometer(124.446);

        Bus vw02 = new Bus();
        setBusTypeEins(vw02);
        vw02.setKennzeichen("L-FR 1011");
        vw02.setZulassung(Date.valueOf("2021-03-01"));
        vw02.setTuevTermin(Date.valueOf("2025-03-01"));
        vw02.setGefahreneKilometer(102.759);

        Bus cityliner01 = new Bus();
        setBusTypeZwei(cityliner01);
        cityliner01.setKennzeichen("L-AF 1234");
        cityliner01.setZulassung(Date.valueOf("2015-05-01"));
        cityliner01.setTuevTermin(Date.valueOf("2025-05-01"));
        cityliner01.setGefahreneKilometer(862.345);

        Bus cityliner02 = new Bus();
        setBusTypeZwei(cityliner02);
        cityliner02.setKennzeichen("L-BX 5678");
        cityliner02.setZulassung(Date.valueOf("2015-05-01"));
        cityliner02.setTuevTermin(Date.valueOf("2025-05-01"));
        cityliner02.setGefahreneKilometer(789.456);

        Bus cityliner03 = new Bus();
        setBusTypeZwei(cityliner03);
        cityliner03.setKennzeichen("L-CG 9101");
        cityliner03.setZulassung(Date.valueOf("2016-07-01"));
        cityliner03.setTuevTermin(Date.valueOf("2024-07-01"));
        cityliner03.setGefahreneKilometer(678.901);

        Bus cityliner04 = new Bus();
        setBusTypeZwei(cityliner04);
        cityliner04.setKennzeichen("L-DH 2345");
        cityliner04.setZulassung(Date.valueOf("2016-07-01"));
        cityliner04.setTuevTermin(Date.valueOf("2024-07-01"));
        cityliner04.setGefahreneKilometer(696.332);

        return new Bus[]{vw01, vw02, cityliner01, cityliner02, cityliner03, cityliner04};
    }

    // DB Operations
    public static void createDefaultBusse(Connection conn) throws SQLException {
        initBusGroup(conn, defaultBusseTable());
        for (Bus bus: defaultBusseTable()) {
            updateBus(conn, bus);
        }
    }

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