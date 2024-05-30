package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Adresse;

import java.sql.Connection;
import java.sql.SQLException;

public class AdressenTable {

    private static Adresse[] defaultAdressenTable() {

        Adresse[] adressen = new Adresse[60];

        Adresse Adresse1 = new Adresse(1);
        Adresse1.setStrasse("Hauptstraße");
        Adresse1.setHausnummer("1");
        Adresse1.setPlz("04109");
        Adresse1.setOrt("Leipzig");
        adressen[0] = Adresse1;

        Adresse Adresse2 = new Adresse(2);
        Adresse2.setStrasse("Bahnhofsstraße");
        Adresse2.setHausnummer("3");
        Adresse2.setPlz("01067");
        Adresse2.setOrt("Dresden");
        adressen[1] = Adresse2;

        Adresse Adresse3 = new Adresse(3);
        Adresse3.setStrasse("Alexanderplatz");
        Adresse3.setHausnummer("5");
        Adresse3.setPlz("10178");
        Adresse3.setOrt("Berlin");
        adressen[2] = Adresse3;

        Adresse Adresse4 = new Adresse(4);
        Adresse4.setStrasse("Marienplatz");
        Adresse4.setHausnummer("7");
        Adresse4.setPlz("80331");
        Adresse4.setOrt("München");
        adressen[3] = Adresse4;

        Adresse Adresse5 = new Adresse(5);
        Adresse5.setStrasse("Domplatz");
        Adresse5.setHausnummer("2");
        Adresse5.setPlz("99084");
        Adresse5.setOrt("Erfurt");
        adressen[4] = Adresse5;

        Adresse Adresse6 = new Adresse(6);
        Adresse6.setStrasse("Königstraße");
        Adresse6.setHausnummer("4");
        Adresse6.setPlz("30159");
        Adresse6.setOrt("Hannover");
        adressen[5] = Adresse6;

        Adresse Adresse7 = new Adresse(7);
        Adresse7.setStrasse("Domkloster");
        Adresse7.setHausnummer("1");
        Adresse7.setPlz("50667");
        Adresse7.setOrt("Köln");
        adressen[6] = Adresse7;

        Adresse Adresse8 = new Adresse(8);
        Adresse8.setStrasse("Zeil");
        Adresse8.setHausnummer("10");
        Adresse8.setPlz("60313");
        Adresse8.setOrt("Frankfurt am Main");
        adressen[7] = Adresse8;

        Adresse Adresse9 = new Adresse(9);
        Adresse9.setStrasse("Königstraße");
        Adresse9.setHausnummer("6");
        Adresse9.setPlz("70173");
        Adresse9.setOrt("Stuttgart");
        adressen[8] = Adresse9;

        Adresse Adresse10 = new Adresse(10);
        Adresse10.setStrasse("Königsstraße");
        Adresse10.setHausnummer("8");
        Adresse10.setPlz("90402");
        Adresse10.setOrt("Nürnberg");
        adressen[9] = Adresse10;

        Adresse Adresse11 = new Adresse(11);
        Adresse11.setStrasse("Leipziger Straße");
        Adresse11.setHausnummer("9");
        Adresse11.setPlz("04109");
        Adresse11.setOrt("Leipzig");
        adressen[10] = Adresse11;

        Adresse Adresse12 = new Adresse(12);
        Adresse12.setStrasse("Augustusplatz");
        Adresse12.setHausnummer("10");
        Adresse12.setPlz("01067");
        Adresse12.setOrt("Dresden");
        adressen[11] = Adresse12;

        Adresse Adresse13 = new Adresse(13);
        Adresse13.setStrasse("Friedrichstraße");
        Adresse13.setHausnummer("12");
        Adresse13.setPlz("10178");
        Adresse13.setOrt("Berlin");
        adressen[12] = Adresse13;

        Adresse Adresse14 = new Adresse(14);
        Adresse14.setStrasse("Karlsplatz");
        Adresse14.setHausnummer("13");
        Adresse14.setPlz("80331");
        Adresse14.setOrt("München");
        adressen[13] = Adresse14;

        Adresse Adresse15 = new Adresse(15);
        Adresse15.setStrasse("Anger");
        Adresse15.setHausnummer("14");
        Adresse15.setPlz("99084");
        Adresse15.setOrt("Erfurt");
        adressen[14] = Adresse15;

        Adresse Adresse16 = new Adresse(16);
        Adresse16.setStrasse("Ernst-August-Platz");
        Adresse16.setHausnummer("15");
        Adresse16.setPlz("30159");
        Adresse16.setOrt("Hannover");
        adressen[15] = Adresse16;

        Adresse Adresse17 = new Adresse(17);
        Adresse17.setStrasse("Hohe Straße");
        Adresse17.setHausnummer("16");
        Adresse17.setPlz("50667");
        Adresse17.setOrt("Köln");
        adressen[16] = Adresse17;

        Adresse Adresse18 = new Adresse(18);
        Adresse18.setStrasse("Goethestraße");
        Adresse18.setHausnummer("17");
        Adresse18.setPlz("60313");
        Adresse18.setOrt("Frankfurt am Main");
        adressen[17] = Adresse18;

        Adresse Adresse19 = new Adresse(19);
        Adresse19.setStrasse("Eberhardstraße");
        Adresse19.setHausnummer("18");
        Adresse19.setPlz("70173");
        Adresse19.setOrt("Stuttgart");
        adressen[18] = Adresse19;

        Adresse Adresse20 = new Adresse(20);
        Adresse20.setStrasse("Plärrer");
        Adresse20.setHausnummer("19");
        Adresse20.setPlz("90402");
        Adresse20.setOrt("Nürnberg");
        adressen[19] = Adresse20;

        Adresse Adresse21 = new Adresse(21);
        Adresse21.setStrasse("Nikolaistraße");
        Adresse21.setHausnummer("20");
        Adresse21.setPlz("04109");
        Adresse21.setOrt("Leipzig");
        adressen[20] = Adresse21;

        Adresse Adresse22 = new Adresse(22);
        Adresse22.setStrasse("Wilsdruffer Straße");
        Adresse22.setHausnummer("21");
        Adresse22.setPlz("01067");
        Adresse22.setOrt("Dresden");
        adressen[21] = Adresse22;

        Adresse Adresse23 = new Adresse(23);
        Adresse23.setStrasse("Unter den Linden");
        Adresse23.setHausnummer("22");
        Adresse23.setPlz("10178");
        Adresse23.setOrt("Berlin");
        adressen[22] = Adresse23;

        Adresse Adresse24 = new Adresse(24);
        Adresse24.setStrasse("Kaufingerstraße");
        Adresse24.setHausnummer("23");
        Adresse24.setPlz("80331");
        Adresse24.setOrt("München");
        adressen[23] = Adresse24;

        Adresse Adresse25 = new Adresse(25);
        Adresse25.setStrasse("Domstraße");
        Adresse25.setHausnummer("24");
        Adresse25.setPlz("99084");
        Adresse25.setOrt("Erfurt");
        adressen[24] = Adresse25;

        Adresse Adresse26 = new Adresse(26);
        Adresse26.setStrasse("Lister Meile");
        Adresse26.setHausnummer("25");
        Adresse26.setPlz("30159");
        Adresse26.setOrt("Hannover");
        adressen[25] = Adresse26;

        Adresse Adresse27 = new Adresse(27);
        Adresse27.setStrasse("Breite Straße");
        Adresse27.setHausnummer("26");
        Adresse27.setPlz("50667");
        Adresse27.setOrt("Köln");
        adressen[26] = Adresse27;

        Adresse Adresse28 = new Adresse(28);
        Adresse28.setStrasse("Biebergasse");
        Adresse28.setHausnummer("27");
        Adresse28.setPlz("60313");
        Adresse28.setOrt("Frankfurt am Main");
        adressen[27] = Adresse28;

        Adresse Adresse29 = new Adresse(29);
        Adresse29.setStrasse("Rotebühlplatz");
        Adresse29.setHausnummer("28");
        Adresse29.setPlz("70173");
        Adresse29.setOrt("Stuttgart");
        adressen[28] = Adresse29;

        Adresse Adresse30 = new Adresse(30);
        Adresse30.setStrasse("Lorenzkirche");
        Adresse30.setHausnummer("29");
        Adresse30.setPlz("90402");
        Adresse30.setOrt("Nürnberg");
        adressen[29] = Adresse30;

        Adresse Adresse31 = new Adresse(31);
        Adresse31.setStrasse("Clara-Zetkin-Straße");
        Adresse31.setHausnummer("30");
        Adresse31.setPlz("04109");
        Adresse31.setOrt("Leipzig");
        adressen[30] = Adresse31;

        Adresse Adresse32 = new Adresse(32);
        Adresse32.setStrasse("Prager Straße");
        Adresse32.setHausnummer("31");
        Adresse32.setPlz("01067");
        Adresse32.setOrt("Dresden");
        adressen[31] = Adresse32;

        Adresse Adresse33 = new Adresse(33);
        Adresse33.setStrasse("Hackescher Markt");
        Adresse33.setHausnummer("32");
        Adresse33.setPlz("10178");
        Adresse33.setOrt("Berlin");
        adressen[32] = Adresse33;

        Adresse Adresse34 = new Adresse(34);
        Adresse34.setStrasse("Neuhauser Straße");
        Adresse34.setHausnummer("33");
        Adresse34.setPlz("80331");
        Adresse34.setOrt("München");
        adressen[33] = Adresse34;

        Adresse Adresse35 = new Adresse(35);
        Adresse35.setStrasse("Lange Brücke");
        Adresse35.setHausnummer("34");
        Adresse35.setPlz("99084");
        Adresse35.setOrt("Erfurt");
        adressen[34] = Adresse35;

        Adresse Adresse36 = new Adresse(36);
        Adresse36.setStrasse("Georgstraße");
        Adresse36.setHausnummer("35");
        Adresse36.setPlz("30159");
        Adresse36.setOrt("Hannover");
        adressen[35] = Adresse36;

        Adresse Adresse37 = new Adresse(37);
        Adresse37.setStrasse("Schildergasse");
        Adresse37.setHausnummer("36");
        Adresse37.setPlz("50667");
        Adresse37.setOrt("Köln");
        adressen[36] = Adresse37;

        Adresse Adresse38 = new Adresse(38);
        Adresse38.setStrasse("Fressgass");
        Adresse38.setHausnummer("37");
        Adresse38.setPlz("60313");
        Adresse38.setOrt("Frankfurt am Main");
        adressen[37] = Adresse38;

        Adresse Adresse39 = new Adresse(39);
        Adresse39.setStrasse("Königstraße");
        Adresse39.setHausnummer("38");
        Adresse39.setPlz("70173");
        Adresse39.setOrt("Stuttgart");
        adressen[38] = Adresse39;

        Adresse Adresse40 = new Adresse(40);
        Adresse40.setStrasse("Hauptmarkt");
        Adresse40.setHausnummer("39");
        Adresse40.setPlz("90402");
        Adresse40.setOrt("Nürnberg");
        adressen[39] = Adresse40;

        Adresse Adresse41 = new Adresse(41);
        Adresse41.setStrasse("Windmühlenstraße");
        Adresse41.setHausnummer("40");
        Adresse41.setPlz("04109");
        Adresse41.setOrt("Leipzig");
        adressen[40] = Adresse41;

        Adresse Adresse42 = new Adresse(42);
        Adresse42.setStrasse("Altmarkt");
        Adresse42.setHausnummer("41");
        Adresse42.setPlz("01067");
        Adresse42.setOrt("Dresden");
        adressen[41] = Adresse42;

        Adresse Adresse43 = new Adresse(43);
        Adresse43.setStrasse("Gendarmenmarkt");
        Adresse43.setHausnummer("42");
        Adresse43.setPlz("10178");
        Adresse43.setOrt("Berlin");
        adressen[42] = Adresse43;

        Adresse Adresse44 = new Adresse(44);
        Adresse44.setStrasse("Sendlinger Straße");
        Adresse44.setHausnummer("43");
        Adresse44.setPlz("80331");
        Adresse44.setOrt("München");
        adressen[43] = Adresse44;

        Adresse Adresse45 = new Adresse(45);
        Adresse45.setStrasse("Marktstraße");
        Adresse45.setHausnummer("44");
        Adresse45.setPlz("99084");
        Adresse45.setOrt("Erfurt");
        adressen[44] = Adresse45;

        Adresse Adresse46 = new Adresse(46);
        Adresse46.setStrasse("Kröpcke");
        Adresse46.setHausnummer("45");
        Adresse46.setPlz("30159");
        Adresse46.setOrt("Hannover");
        adressen[45] = Adresse46;

        Adresse Adresse47 = new Adresse(47);
        Adresse47.setStrasse("Neumarkt");
        Adresse47.setHausnummer("46");
        Adresse47.setPlz("50667");
        Adresse47.setOrt("Köln");
        adressen[46] = Adresse47;

        Adresse Adresse48 = new Adresse(48);
        Adresse48.setStrasse("Große Eschenheimer Straße");
        Adresse48.setHausnummer("47");
        Adresse48.setPlz("60313");
        Adresse48.setOrt("Frankfurt am Main");
        adressen[47] = Adresse48;

        Adresse Adresse49 = new Adresse(49);
        Adresse49.setStrasse("Calwer Straße");
        Adresse49.setHausnummer("48");
        Adresse49.setPlz("70173");
        Adresse49.setOrt("Stuttgart");
        adressen[48] = Adresse49;

        Adresse Adresse50 = new Adresse(50);
        Adresse50.setStrasse("Karolinenstraße");
        Adresse50.setHausnummer("49");
        Adresse50.setPlz("90402");
        Adresse50.setOrt("Nürnberg");
        adressen[49] = Adresse50;

        Adresse Adresse51 = new Adresse(51);
        Adresse51.setStrasse("Gustav-Adolf-Straße");
        Adresse51.setHausnummer("50");
        Adresse51.setPlz("04109");
        Adresse51.setOrt("Leipzig");
        adressen[50] = Adresse51;

        Adresse Adresse52 = new Adresse(52);
        Adresse52.setStrasse("Hauptstraße");
        Adresse52.setHausnummer("51");
        Adresse52.setPlz("01067");
        Adresse52.setOrt("Dresden");
        adressen[51] = Adresse52;

        Adresse Adresse53 = new Adresse(53);
        Adresse53.setStrasse("Kurfürstendamm");
        Adresse53.setHausnummer("52");
        Adresse53.setPlz("10178");
        Adresse53.setOrt("Berlin");
        adressen[52] = Adresse53;

        Adresse Adresse54 = new Adresse(54);
        Adresse54.setStrasse("Tal");
        Adresse54.setHausnummer("53");
        Adresse54.setPlz("80331");
        Adresse54.setOrt("München");
        adressen[53] = Adresse54;

        Adresse Adresse55 = new Adresse(55);
        Adresse55.setStrasse("Barfüßerstraße");
        Adresse55.setHausnummer("54");
        Adresse55.setPlz("99084");
        Adresse55.setOrt("Erfurt");
        adressen[54] = Adresse55;

        Adresse Adresse56 = new Adresse(56);
        Adresse56.setStrasse("Vahrenwalder Straße");
        Adresse56.setHausnummer("55");
        Adresse56.setPlz("30159");
        Adresse56.setOrt("Hannover");
        adressen[55] = Adresse56;

        Adresse Adresse57 = new Adresse(57);
        Adresse57.setStrasse("Breitestraße");
        Adresse57.setHausnummer("56");
        Adresse57.setPlz("50667");
        Adresse57.setOrt("Köln");
        adressen[56] = Adresse57;

        Adresse Adresse58 = new Adresse(58);
        Adresse58.setStrasse("Hasengasse");
        Adresse58.setHausnummer("57");
        Adresse58.setPlz("60313");
        Adresse58.setOrt("Frankfurt am Main");
        adressen[57] = Adresse58;

        Adresse Adresse59 = new Adresse(59);
        Adresse59.setStrasse("Schlossplatz");
        Adresse59.setHausnummer("58");
        Adresse59.setPlz("70173");
        Adresse59.setOrt("Stuttgart");
        adressen[58] = Adresse59;

        Adresse Adresse60 = new Adresse(60);
        Adresse60.setStrasse("Burgstraße");
        Adresse60.setHausnummer("59");
        Adresse60.setPlz("90402");
        Adresse60.setOrt("Nürnberg");
        adressen[59] = Adresse60;

        return adressen;
    }

    // DB Operations
    public static void createDefaultAdressen(Connection conn) throws SQLException {
        initAdressenGroup(conn, defaultAdressenTable());
    }

    public static int initAdresse(Connection conn, Adresse adresse) throws SQLException {
        // check if the adresse with this adresseID already exists in DB, if not then initialize it
        if (!(BusreisenDB.getAdresse(conn, adresse.getAdresseId()).getAdresseId()==(adresse.getAdresseId())))
            return BusreisenDB.initAdresse(conn, adresse);
        else {
            System.out.println("Die Adresse mit AdresseId " + adresse.getAdresseId() + " ist bereits bei der DB!");
            return -1;
        }
    }

    public static void initAdressenGroup(Connection conn, Adresse[] adressen) throws SQLException {
        for (Adresse adresse: adressen) {
            initAdresse(conn, adresse);
        }
    }

    public static void updateAdresse(Connection conn, Adresse adresse) throws SQLException {
        if (BusreisenDB.getAdresseDBId(conn, adresse.getAdresseId()) == -1)
            System.out.println("Die Daten wurden in der Datenbank nicht gefunden!");
        else BusreisenDB.updateAdresse(conn, adresse, BusreisenDB.getAdresseDBId(conn, adresse.getAdresseId()));
    }

    public static Adresse getAdresse(Connection conn, int adresseId) throws SQLException {
        return BusreisenDB.getAdresse(conn, adresseId);
    }
}