package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.Fuehrerscheinklasse;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Fahrer;

import java.sql.Connection;
import java.sql.SQLException;

public class FahrerTable {

    private static Fahrer[] defaultFahrerTable() {
        Fahrer Fahrer1 = new Fahrer(1);
        Fahrer1.setName("Müller");
        Fahrer1.setVorname("Petra");
        Fahrer1.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.B);

        Fahrer Fahrer2 = new Fahrer(2);
        Fahrer2.setName("Schmidt");
        Fahrer2.setVorname("Thomas");
        Fahrer2.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.B);

        Fahrer Fahrer3 = new Fahrer(3);
        Fahrer3.setName("Fischer");
        Fahrer3.setVorname("Gabriele");
        Fahrer3.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        Fahrer Fahrer4 = new Fahrer(4);
        Fahrer4.setName("Weber");
        Fahrer4.setVorname("Wolfgang");
        Fahrer4.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        Fahrer Fahrer5 = new Fahrer(5);
        Fahrer5.setName("Schulz");
        Fahrer5.setVorname("Karin");
        Fahrer5.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        Fahrer Fahrer6 = new Fahrer(6);
        Fahrer6.setName("Meyer");
        Fahrer6.setVorname("Jürgen");
        Fahrer6.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        Fahrer Fahrer7 = new Fahrer(7);
        Fahrer7.setName("Hoffmann");
        Fahrer7.setVorname("Monika");
        Fahrer7.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        Fahrer Fahrer8 = new Fahrer(8);
        Fahrer8.setName("Becker");
        Fahrer8.setVorname("Hans");
        Fahrer8.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        return new Fahrer[] {Fahrer1, Fahrer2, Fahrer3, Fahrer4, Fahrer5, Fahrer6, Fahrer7, Fahrer8 };
    }

    // DB Operations
    public static void createDefaultFahrer(Connection conn) throws SQLException {
        initFahrerGroup(conn, defaultFahrerTable());
    }

    public static int initFahrer(Connection conn, Fahrer fahrer) throws SQLException {
        // check if it already exists in DB, if not then initialize it
        if (!(BusreisenDB.getFahrer(conn, fahrer.getFahrerNr()).getFahrerNr()==(fahrer.getFahrerNr())))
            return BusreisenDB.initFahrer(conn, fahrer);
        else {
            System.out.println("Der Fahrer mit FahrerNr " + fahrer.getFahrerNr() + " ist bereits bei der DB!");
            return -1;
        }
    }

    public static void initFahrerGroup(Connection conn, Fahrer[] fahrere) throws SQLException {
        for (Fahrer fahrer: fahrere) {
            initFahrer(conn, fahrer);
        }
    }

    public static void updateFahrer(Connection conn, Fahrer fahrer) throws SQLException {
        if (BusreisenDB.getFahrerDBId(conn, fahrer.getFahrerNr()) == -1)
            System.out.println("Die Daten wurden in der Datenbank nicht gefunden!");
        else BusreisenDB.updateFahrer(conn, fahrer, BusreisenDB.getFahrerDBId(conn, fahrer.getFahrerNr()));
    }

    public static Fahrer getFahrer(Connection conn, int fahrerNr) throws SQLException {
        return BusreisenDB.getFahrer(conn, fahrerNr);
    }
}
