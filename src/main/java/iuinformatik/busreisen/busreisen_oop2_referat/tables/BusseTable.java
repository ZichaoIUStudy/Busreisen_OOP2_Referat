package iuinformatik.busreisen.busreisen_oop2_referat.tables;

import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Bus;

import java.sql.Date;

public class BusseTable {

    // VW 9-Sitzer-Busse
    public void setBusTypeEins(Bus bus) {
        bus.setBusTyp(BusTyp.Kleinbus);
        bus.setKostenProKilometer(0.5);
    }

    // Cityliner
    public void setBusTypeZwei(Bus bus) {
        bus.setBusTyp(BusTyp.Reisebus);
        bus.setKostenProKilometer(1.2);
    }

    public void createDefaultBusse() {
        Bus VW_T6_01 = new Bus();
        setBusTypeEins(VW_T6_01);
        VW_T6_01.setKennzeichen("L-EK 6789");
        VW_T6_01.setZulassung(Date.valueOf("2021-03-01"));
        VW_T6_01.setTuevTermin(Date.valueOf("2025-03-01"));
        VW_T6_01.setGefahreneKilometer(124.446);

        Bus VW_T6_02 = new Bus();
        setBusTypeEins(VW_T6_02);
        VW_T6_02.setKennzeichen("L-FR 1011");
        VW_T6_02.setZulassung(Date.valueOf("2021-03-01"));
        VW_T6_02.setTuevTermin(Date.valueOf("2025-03-01"));
        VW_T6_02.setGefahreneKilometer(102.759);

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
    }
}