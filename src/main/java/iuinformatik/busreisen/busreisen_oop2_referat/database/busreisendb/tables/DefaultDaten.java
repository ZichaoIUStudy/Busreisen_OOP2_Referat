package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.objectMethods.AdresseDB;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.objectMethods.BusDB;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.objectMethods.FahrerDB;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.Fuehrerscheinklasse;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Adresse;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Bus;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Fahrer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class DefaultDaten {

    public static void defaultDatenBank(Connection conn) throws SQLException {

        // defalut busse
        Bus vw01 = new Bus();
        vw01.setKennzeichen("L-EK 6789");
        BusDB.setBusTypeEins(vw01);
        vw01.setZulassung(Date.valueOf("2021-03-01"));
        vw01.setTuevTermin(Date.valueOf("2025-03-01"));
        vw01.setGefahreneKilometer(124446.0);

        Bus vw02 = new Bus();
        vw02.setKennzeichen("L-FR 1011");
        BusDB.setBusTypeEins(vw02);
        vw02.setZulassung(Date.valueOf("2021-03-01"));
        vw02.setTuevTermin(Date.valueOf("2025-03-01"));
        vw02.setGefahreneKilometer(102759.0);

        Bus cityliner01 = new Bus();
        cityliner01.setKennzeichen("L-AF 1234");
        BusDB.setBusTypeZwei(cityliner01);
        cityliner01.setZulassung(Date.valueOf("2015-05-01"));
        cityliner01.setTuevTermin(Date.valueOf("2025-05-01"));
        cityliner01.setGefahreneKilometer(862345.0);

        Bus cityliner02 = new Bus();
        cityliner02.setKennzeichen("L-BX 5678");
        BusDB.setBusTypeZwei(cityliner02);
        cityliner02.setZulassung(Date.valueOf("2015-05-01"));
        cityliner02.setTuevTermin(Date.valueOf("2025-05-01"));
        cityliner02.setGefahreneKilometer(789456.0);

        Bus cityliner03 = new Bus();
        cityliner03.setKennzeichen("L-CG 9101");
        BusDB.setBusTypeZwei(cityliner03);
        cityliner03.setZulassung(Date.valueOf("2016-07-01"));
        cityliner03.setTuevTermin(Date.valueOf("2024-07-01"));
        cityliner03.setGefahreneKilometer(678901.0);

        Bus cityliner04 = new Bus();
        cityliner04.setKennzeichen("L-DH 2345");
        BusDB.setBusTypeZwei(cityliner04);
        cityliner04.setZulassung(Date.valueOf("2016-07-01"));
        cityliner04.setTuevTermin(Date.valueOf("2024-07-01"));
        cityliner04.setGefahreneKilometer(696332.0);

        Bus[] defaultbusse = new Bus[]{vw01, vw02, cityliner01, cityliner02, cityliner03, cityliner04};

        //default fahrer
        Fahrer fahrer1 = new Fahrer();
        fahrer1.setFahrerNr(1);
        fahrer1.setName("Müller");
        fahrer1.setVorname("Petra");
        fahrer1.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.B);

        Fahrer fahrer2 = new Fahrer();
        fahrer2.setFahrerNr(2);
        fahrer2.setName("Schmidt");
        fahrer2.setVorname("Thomas");
        fahrer2.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.B);

        Fahrer fahrer3 = new Fahrer();
        fahrer3.setFahrerNr(3);
        fahrer3.setName("Fischer");
        fahrer3.setVorname("Gabriele");
        fahrer3.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        Fahrer fahrer4 = new Fahrer();
        fahrer4.setFahrerNr(4);
        fahrer4.setName("Weber");
        fahrer4.setVorname("Wolfgang");
        fahrer4.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        Fahrer fahrer5 = new Fahrer();
        fahrer5.setFahrerNr(5);
        fahrer5.setName("Schulz");
        fahrer5.setVorname("Karin");
        fahrer5.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        Fahrer fahrer6 = new Fahrer();
        fahrer6.setFahrerNr(6);
        fahrer6.setName("Meyer");
        fahrer6.setVorname("Jürgen");
        fahrer6.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        Fahrer fahrer7 = new Fahrer();
        fahrer7.setFahrerNr(7);
        fahrer7.setName("Hoffmann");
        fahrer7.setVorname("Monika");
        fahrer7.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        Fahrer fahrer8 = new Fahrer();
        fahrer8.setFahrerNr(8);
        fahrer8.setName("Becker");
        fahrer8.setVorname("Hans");
        fahrer8.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        Fahrer[] defaultfahrer =  new Fahrer[] {fahrer1, fahrer2, fahrer3, fahrer4, fahrer5, fahrer6, fahrer7, fahrer8 };


        Adresse[] adressen = new Adresse[6];

        Adresse adresse1 = new Adresse();
        adresse1.setAdresseId(1);
        adresse1.setStrasse("Hauptstraße");
        adresse1.setHausnummer("1");
        adresse1.setPlz("04109");
        adresse1.setOrt("Leipzig");
        adresse1.setAdressTyp(1);
        adressen[0] = adresse1;

        Adresse adresse2 = new Adresse();
        adresse2.setAdresseId(2);
        adresse2.setStrasse("Bahnhofsstraße");
        adresse2.setHausnummer("3");
        adresse2.setPlz("01067");
        adresse2.setOrt("Dresden");
        adresse2.setAdressTyp(1);
        adressen[1] = adresse2;

        Adresse adresse3 = new Adresse();
        adresse3.setAdresseId(3);
        adresse3.setStrasse("Alexanderplatz");
        adresse3.setHausnummer("5");
        adresse3.setPlz("10178");
        adresse3.setOrt("Berlin");
        adresse3.setAdressTyp(1);
        adressen[2] = adresse3;

        Adresse adresse4 = new Adresse();
        adresse4.setAdresseId(4);
        adresse4.setStrasse("Marienplatz");
        adresse4.setHausnummer("7");
        adresse4.setPlz("80331");
        adresse4.setOrt("München");
        adresse4.setAdressTyp(1);
        adressen[3] = adresse4;

        Adresse adresse5 = new Adresse();
        adresse5.setAdresseId(5);
        adresse5.setStrasse("Domplatz");
        adresse5.setHausnummer("2");
        adresse5.setPlz("99084");
        adresse5.setOrt("Erfurt");
        adresse5.setAdressTyp(1);
        adressen[4] = adresse5;

        Adresse adresse6 = new Adresse();
        adresse6.setAdresseId(6);
        adresse6.setStrasse("Königstraße");
        adresse6.setHausnummer("4");
        adresse6.setPlz("30159");
        adresse6.setOrt("Hannover");
        adresse6.setAdressTyp(1);
        adressen[5] = adresse6;

        createDefaultBusse(conn, defaultbusse);
        FahrerDB.initFahrerGroup(conn, defaultfahrer);
        AdresseDB.initAdressenGroup(conn, adressen);
    }

    private static void createDefaultBusse(Connection conn, Bus[] busse) throws SQLException {
        BusDB.initBusGroup(conn, busse);
        for (Bus bus: busse) {
            BusDB.updateBus(conn, bus);
        }
    }
}
