package iuinformatik.busreisen.busreisen_oop2_referat.database.busreiseDB.tables;

import iuinformatik.busreisen.busreisen_oop2_referat.database.DB;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreiseDB.DBFunktionen;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Bus;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Busreise;

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

    public static void createDefaultBusse(Connection conn) throws SQLException {
        Bus VW_01 = new Bus();
        setBusTypeEins(VW_01);
        VW_01.setKennzeichen("L-EK 6789");
        VW_01.setZulassung(Date.valueOf("2021-03-01"));
        VW_01.setTuevTermin(Date.valueOf("2025-03-01"));
        VW_01.setGefahreneKilometer(124.446);

        Bus VW_02 = new Bus();
        setBusTypeEins(VW_02);
        VW_02.setKennzeichen("L-FR 1011");
        VW_02.setZulassung(Date.valueOf("2021-03-01"));
        VW_02.setTuevTermin(Date.valueOf("2025-03-01"));
        VW_02.setGefahreneKilometer(102.759);

        Bus Cityliner01 = new Bus();
        setBusTypeZwei(Cityliner01);
        Cityliner01.setKennzeichen("L-AF 1234");
        Cityliner01.setZulassung(Date.valueOf("2015-05-01"));
        Cityliner01.setTuevTermin(Date.valueOf("2025-05-01"));
        Cityliner01.setGefahreneKilometer(862.345);

        Bus Cityliner02 = new Bus();
        setBusTypeZwei(Cityliner02);
        Cityliner02.setKennzeichen("L-BX 5678");
        Cityliner02.setZulassung(Date.valueOf("2015-05-01"));
        Cityliner02.setTuevTermin(Date.valueOf("2025-05-01"));
        Cityliner02.setGefahreneKilometer(789.456);

        Bus Cityliner03 = new Bus();
        setBusTypeZwei(Cityliner03);
        Cityliner03.setKennzeichen("L-CG 9101");
        Cityliner03.setZulassung(Date.valueOf("2016-07-01"));
        Cityliner03.setTuevTermin(Date.valueOf("2024-07-01"));
        Cityliner03.setGefahreneKilometer(678.901);

        Bus Cityliner04 = new Bus();
        setBusTypeZwei(Cityliner04);
        Cityliner04.setKennzeichen("L-DH 2345");
        Cityliner04.setZulassung(Date.valueOf("2016-07-01"));
        Cityliner04.setTuevTermin(Date.valueOf("2024-07-01"));
        Cityliner04.setGefahreneKilometer(696.332);

        // check if the bus with this Kennzeichen already exists in DB, if not then initialize it
        if (!DBFunktionen.getBus(conn, VW_01.getKennzeichen()).getKennzeichen().equals(VW_01.getKennzeichen()))
            DBFunktionen.initBus(conn, VW_01);

    }
}