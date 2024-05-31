package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables;

import iuinformatik.busreisen.busreisen_oop2_referat.enums.Fuehrerscheinklasse;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Adresse;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Bus;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Busreise;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Fahrer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class DefaultDaten {

    private static void defaultDatenBank() {

        // defalut busse
        Bus vw01 = new Bus("L-EK 6789");
        BusseTable.setBusTypeEins(vw01);
        vw01.setZulassung(Date.valueOf("2021-03-01"));
        vw01.setTuevTermin(Date.valueOf("2025-03-01"));
        vw01.setGefahreneKilometer(124.446);

        Bus vw02 = new Bus("L-FR 1011");
        BusseTable.setBusTypeEins(vw02);
        vw02.setZulassung(Date.valueOf("2021-03-01"));
        vw02.setTuevTermin(Date.valueOf("2025-03-01"));
        vw02.setGefahreneKilometer(102.759);

        Bus cityliner01 = new Bus("L-AF 1234");
        BusseTable.setBusTypeZwei(cityliner01);
        cityliner01.setZulassung(Date.valueOf("2015-05-01"));
        cityliner01.setTuevTermin(Date.valueOf("2025-05-01"));
        cityliner01.setGefahreneKilometer(862.345);

        Bus cityliner02 = new Bus("L-BX 5678");
        BusseTable.setBusTypeZwei(cityliner02);
        cityliner02.setZulassung(Date.valueOf("2015-05-01"));
        cityliner02.setTuevTermin(Date.valueOf("2025-05-01"));
        cityliner02.setGefahreneKilometer(789.456);

        Bus cityliner03 = new Bus("L-CG 9101");
        BusseTable.setBusTypeZwei(cityliner03);
        cityliner03.setZulassung(Date.valueOf("2016-07-01"));
        cityliner03.setTuevTermin(Date.valueOf("2024-07-01"));
        cityliner03.setGefahreneKilometer(678.901);

        Bus cityliner04 = new Bus("L-DH 2345");
        BusseTable.setBusTypeZwei(cityliner04);
        cityliner04.setZulassung(Date.valueOf("2016-07-01"));
        cityliner04.setTuevTermin(Date.valueOf("2024-07-01"));
        cityliner04.setGefahreneKilometer(696.332);

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

        // default adresse
        Adresse[] adressen = new Adresse[60];

        Adresse adresse1 = new Adresse();
        adresse1.setAdresseId(1);
        adresse1.setStrasse("Hauptstraße");
        adresse1.setHausnummer("1");
        adresse1.setPlz("04109");
        adresse1.setOrt("Leipzig");
        adressen[0] = adresse1;

        Adresse adresse2 = new Adresse();
        adresse2.setAdresseId(2);
        adresse2.setStrasse("Bahnhofsstraße");
        adresse2.setHausnummer("3");
        adresse2.setPlz("01067");
        adresse2.setOrt("Dresden");
        adressen[1] = adresse2;

        Adresse adresse3 = new Adresse();
        adresse3.setAdresseId(3);
        adresse3.setStrasse("Alexanderplatz");
        adresse3.setHausnummer("5");
        adresse3.setPlz("10178");
        adresse3.setOrt("Berlin");
        adressen[2] = adresse3;

        Adresse adresse4 = new Adresse();
        adresse4.setAdresseId(4);
        adresse4.setStrasse("Marienplatz");
        adresse4.setHausnummer("7");
        adresse4.setPlz("80331");
        adresse4.setOrt("München");
        adressen[3] = adresse4;

        Adresse adresse5 = new Adresse();
        adresse5.setAdresseId(5);
        adresse5.setStrasse("Domplatz");
        adresse5.setHausnummer("2");
        adresse5.setPlz("99084");
        adresse5.setOrt("Erfurt");
        adressen[4] = adresse5;

        Adresse adresse6 = new Adresse();
        adresse6.setAdresseId(6);
        adresse6.setStrasse("Königstraße");
        adresse6.setHausnummer("4");
        adresse6.setPlz("30159");
        adresse6.setOrt("Hannover");
        adressen[5] = adresse6;

        /*
        Adresse adresse7 = new Adresse();
        adresse7.setStrasse("Domkloster");
        adresse7.setHausnummer("1");
        adresse7.setPlz("50667");
        adresse7.setOrt("Köln");
        adressen[6] = adresse7;

        Adresse adresse8 = new Adresse();
        adresse8.setStrasse("Zeil");
        adresse8.setHausnummer("10");
        adresse8.setPlz("60313");
        adresse8.setOrt("Frankfurt am Main");
        adressen[7] = adresse8;

        Adresse adresse9 = new Adresse();
        adresse9.setStrasse("Königstraße");
        adresse9.setHausnummer("6");
        adresse9.setPlz("70173");
        adresse9.setOrt("Stuttgart");
        adressen[8] = adresse9;

        Adresse adresse10 = new Adresse();
        adresse10.setStrasse("Königsstraße");
        adresse10.setHausnummer("8");
        adresse10.setPlz("90402");
        adresse10.setOrt("Nürnberg");
        adressen[9] = adresse10;

        Adresse adresse11 = new Adresse();
        adresse11.setStrasse("Leipziger Straße");
        adresse11.setHausnummer("9");
        adresse11.setPlz("04109");
        adresse11.setOrt("Leipzig");
        adressen[10] = adresse11;

        Adresse adresse12 = new Adresse();
        adresse12.setStrasse("Augustusplatz");
        adresse12.setHausnummer("10");
        adresse12.setPlz("01067");
        adresse12.setOrt("Dresden");
        adressen[11] = adresse12;

        Adresse adresse13 = new Adresse();
        adresse13.setStrasse("Friedrichstraße");
        adresse13.setHausnummer("12");
        adresse13.setPlz("10178");
        adresse13.setOrt("Berlin");
        adressen[12] = adresse13;

        Adresse adresse14 = new Adresse();
        adresse14.setStrasse("Karlsplatz");
        adresse14.setHausnummer("13");
        adresse14.setPlz("80331");
        adresse14.setOrt("München");
        adressen[13] = adresse14;

        Adresse adresse15 = new Adresse();
        adresse15.setStrasse("Anger");
        adresse15.setHausnummer("14");
        adresse15.setPlz("99084");
        adresse15.setOrt("Erfurt");
        adressen[14] = adresse15;

        Adresse adresse16 = new Adresse();
        adresse16.setStrasse("Ernst-August-Platz");
        adresse16.setHausnummer("15");
        adresse16.setPlz("30159");
        adresse16.setOrt("Hannover");
        adressen[15] = adresse16;

        Adresse adresse17 = new Adresse();
        adresse17.setStrasse("Hohe Straße");
        adresse17.setHausnummer("16");
        adresse17.setPlz("50667");
        adresse17.setOrt("Köln");
        adressen[16] = adresse17;

        Adresse adresse18 = new Adresse();
        adresse18.setStrasse("Goethestraße");
        adresse18.setHausnummer("17");
        adresse18.setPlz("60313");
        adresse18.setOrt("Frankfurt am Main");
        adressen[17] = adresse18;

        Adresse adresse19 = new Adresse();
        adresse19.setStrasse("Eberhardstraße");
        adresse19.setHausnummer("18");
        adresse19.setPlz("70173");
        adresse19.setOrt("Stuttgart");
        adressen[18] = adresse19;

        Adresse adresse20 = new Adresse();
        adresse20.setStrasse("Plärrer");
        adresse20.setHausnummer("19");
        adresse20.setPlz("90402");
        adresse20.setOrt("Nürnberg");
        adressen[19] = adresse20;

        Adresse adresse21 = new Adresse();
        adresse21.setStrasse("Nikolaistraße");
        adresse21.setHausnummer("20");
        adresse21.setPlz("04109");
        adresse21.setOrt("Leipzig");
        adressen[20] = adresse21;

        Adresse adresse22 = new Adresse();
        adresse22.setStrasse("Wilsdruffer Straße");
        adresse22.setHausnummer("21");
        adresse22.setPlz("01067");
        adresse22.setOrt("Dresden");
        adressen[21] = adresse22;

        Adresse adresse23 = new Adresse();
        adresse23.setStrasse("Unter den Linden");
        adresse23.setHausnummer("22");
        adresse23.setPlz("10178");
        adresse23.setOrt("Berlin");
        adressen[22] = adresse23;

        Adresse adresse24 = new Adresse();
        adresse24.setStrasse("Kaufingerstraße");
        adresse24.setHausnummer("23");
        adresse24.setPlz("80331");
        adresse24.setOrt("München");
        adressen[23] = adresse24;

        Adresse adresse25 = new Adresse();
        adresse25.setStrasse("Domstraße");
        adresse25.setHausnummer("24");
        adresse25.setPlz("99084");
        adresse25.setOrt("Erfurt");
        adressen[24] = adresse25;

        Adresse adresse26 = new Adresse();
        adresse26.setStrasse("Lister Meile");
        adresse26.setHausnummer("25");
        adresse26.setPlz("30159");
        adresse26.setOrt("Hannover");
        adressen[25] = adresse26;

        Adresse adresse27 = new Adresse();
        adresse27.setStrasse("Breite Straße");
        adresse27.setHausnummer("26");
        adresse27.setPlz("50667");
        adresse27.setOrt("Köln");
        adressen[26] = adresse27;

        Adresse adresse28 = new Adresse();
        adresse28.setStrasse("Biebergasse");
        adresse28.setHausnummer("27");
        adresse28.setPlz("60313");
        adresse28.setOrt("Frankfurt am Main");
        adressen[27] = adresse28;

        Adresse adresse29 = new Adresse();
        adresse29.setStrasse("Rotebühlplatz");
        adresse29.setHausnummer("28");
        adresse29.setPlz("70173");
        adresse29.setOrt("Stuttgart");
        adressen[28] = adresse29;

        Adresse adresse30 = new Adresse();
        adresse30.setStrasse("Lorenzkirche");
        adresse30.setHausnummer("29");
        adresse30.setPlz("90402");
        adresse30.setOrt("Nürnberg");
        adressen[29] = adresse30;

        Adresse adresse31 = new Adresse();
        adresse31.setStrasse("Clara-Zetkin-Straße");
        adresse31.setHausnummer("30");
        adresse31.setPlz("04109");
        adresse31.setOrt("Leipzig");
        adressen[30] = adresse31;

        Adresse adresse32 = new Adresse();
        adresse32.setStrasse("Prager Straße");
        adresse32.setHausnummer("31");
        adresse32.setPlz("01067");
        adresse32.setOrt("Dresden");
        adressen[31] = adresse32;

        Adresse adresse33 = new Adresse();
        adresse33.setStrasse("Hackescher Markt");
        adresse33.setHausnummer("32");
        adresse33.setPlz("10178");
        adresse33.setOrt("Berlin");
        adressen[32] = adresse33;

        Adresse adresse34 = new Adresse();
        adresse34.setStrasse("Neuhauser Straße");
        adresse34.setHausnummer("33");
        adresse34.setPlz("80331");
        adresse34.setOrt("München");
        adressen[33] = adresse34;

        Adresse adresse35 = new Adresse();
        adresse35.setStrasse("Lange Brücke");
        adresse35.setHausnummer("34");
        adresse35.setPlz("99084");
        adresse35.setOrt("Erfurt");
        adressen[34] = adresse35;

        Adresse adresse36 = new Adresse();
        adresse36.setStrasse("Georgstraße");
        adresse36.setHausnummer("35");
        adresse36.setPlz("30159");
        adresse36.setOrt("Hannover");
        adressen[35] = adresse36;

        Adresse adresse37 = new Adresse();
        adresse37.setStrasse("Schildergasse");
        adresse37.setHausnummer("36");
        adresse37.setPlz("50667");
        adresse37.setOrt("Köln");
        adressen[36] = adresse37;

        Adresse adresse38 = new Adresse();
        adresse38.setStrasse("Fressgass");
        adresse38.setHausnummer("37");
        adresse38.setPlz("60313");
        adresse38.setOrt("Frankfurt am Main");
        adressen[37] = adresse38;

        Adresse adresse39 = new Adresse();
        adresse39.setStrasse("Königstraße");
        adresse39.setHausnummer("38");
        adresse39.setPlz("70173");
        adresse39.setOrt("Stuttgart");
        adressen[38] = adresse39;

        Adresse adresse40 = new Adresse();
        adresse40.setStrasse("Hauptmarkt");
        adresse40.setHausnummer("39");
        adresse40.setPlz("90402");
        adresse40.setOrt("Nürnberg");
        adressen[39] = adresse40;

        Adresse adresse41 = new Adresse();
        adresse41.setStrasse("Windmühlenstraße");
        adresse41.setHausnummer("40");
        adresse41.setPlz("04109");
        adresse41.setOrt("Leipzig");
        adressen[40] = adresse41;

        Adresse adresse42 = new Adresse();
        adresse42.setStrasse("Altmarkt");
        adresse42.setHausnummer("41");
        adresse42.setPlz("01067");
        adresse42.setOrt("Dresden");
        adressen[41] = adresse42;

        Adresse adresse43 = new Adresse();
        adresse43.setStrasse("Gendarmenmarkt");
        adresse43.setHausnummer("42");
        adresse43.setPlz("10178");
        adresse43.setOrt("Berlin");
        adressen[42] = adresse43;

        Adresse adresse44 = new Adresse();
        adresse44.setStrasse("Sendlinger Straße");
        adresse44.setHausnummer("43");
        adresse44.setPlz("80331");
        adresse44.setOrt("München");
        adressen[43] = adresse44;

        Adresse adresse45 = new Adresse();
        adresse45.setStrasse("Marktstraße");
        adresse45.setHausnummer("44");
        adresse45.setPlz("99084");
        adresse45.setOrt("Erfurt");
        adressen[44] = adresse45;

        Adresse adresse46 = new Adresse();
        adresse46.setStrasse("Kröpcke");
        adresse46.setHausnummer("45");
        adresse46.setPlz("30159");
        adresse46.setOrt("Hannover");
        adressen[45] = adresse46;

        Adresse adresse47 = new Adresse();
        adresse47.setStrasse("Neumarkt");
        adresse47.setHausnummer("46");
        adresse47.setPlz("50667");
        adresse47.setOrt("Köln");
        adressen[46] = adresse47;

        Adresse adresse48 = new Adresse();
        adresse48.setStrasse("Große Eschenheimer Straße");
        adresse48.setHausnummer("47");
        adresse48.setPlz("60313");
        adresse48.setOrt("Frankfurt am Main");
        adressen[47] = adresse48;

        Adresse adresse49 = new Adresse();
        adresse49.setStrasse("Calwer Straße");
        adresse49.setHausnummer("48");
        adresse49.setPlz("70173");
        adresse49.setOrt("Stuttgart");
        adressen[48] = adresse49;

        Adresse adresse50 = new Adresse();
        adresse50.setStrasse("Karolinenstraße");
        adresse50.setHausnummer("49");
        adresse50.setPlz("90402");
        adresse50.setOrt("Nürnberg");
        adressen[49] = adresse50;

        Adresse adresse51 = new Adresse();
        adresse51.setStrasse("Gustav-Adolf-Straße");
        adresse51.setHausnummer("50");
        adresse51.setPlz("04109");
        adresse51.setOrt("Leipzig");
        adressen[50] = adresse51;

        Adresse adresse52 = new Adresse();
        adresse52.setStrasse("Hauptstraße");
        adresse52.setHausnummer("51");
        adresse52.setPlz("01067");
        adresse52.setOrt("Dresden");
        adressen[51] = adresse52;

        Adresse adresse53 = new Adresse();
        adresse53.setStrasse("Kurfürstendamm");
        adresse53.setHausnummer("52");
        adresse53.setPlz("10178");
        adresse53.setOrt("Berlin");
        adressen[52] = adresse53;

        Adresse adresse54 = new Adresse();
        adresse54.setStrasse("Tal");
        adresse54.setHausnummer("53");
        adresse54.setPlz("80331");
        adresse54.setOrt("München");
        adressen[53] = adresse54;

        Adresse adresse55 = new Adresse();
        adresse55.setStrasse("Barfüßerstraße");
        adresse55.setHausnummer("54");
        adresse55.setPlz("99084");
        adresse55.setOrt("Erfurt");
        adressen[54] = adresse55;

        Adresse adresse56 = new Adresse();
        adresse56.setStrasse("Vahrenwalder Straße");
        adresse56.setHausnummer("55");
        adresse56.setPlz("30159");
        adresse56.setOrt("Hannover");
        adressen[55] = adresse56;

        Adresse adresse57 = new Adresse();
        adresse57.setStrasse("Breitestraße");
        adresse57.setHausnummer("56");
        adresse57.setPlz("50667");
        adresse57.setOrt("Köln");
        adressen[56] = adresse57;

        Adresse adresse58 = new Adresse();
        adresse58.setStrasse("Hasengasse");
        adresse58.setHausnummer("57");
        adresse58.setPlz("60313");
        adresse58.setOrt("Frankfurt am Main");
        adressen[57] = adresse58;

        Adresse adresse59 = new Adresse();
        adresse59.setStrasse("Schlossplatz");
        adresse59.setHausnummer("58");
        adresse59.setPlz("70173");
        adresse59.setOrt("Stuttgart");
        adressen[58] = adresse59;

        Adresse adresse60 = new Adresse();
        adresse60.setStrasse("Burgstraße");
        adresse60.setHausnummer("59");
        adresse60.setPlz("90402");
        adresse60.setOrt("Nürnberg");
        adressen[59] = adresse60;
*/

    }

    private static void createDefaultBusse(Connection conn, Bus[] busse) throws SQLException {
        BusseTable.initBusGroup(conn, busse);
        for (Bus bus: busse) {
            BusseTable.updateBus(conn, bus);
        }
    }
}
