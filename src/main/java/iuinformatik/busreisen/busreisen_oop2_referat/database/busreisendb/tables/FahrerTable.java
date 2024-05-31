package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.Fuehrerscheinklasse;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Fahrer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FahrerTable {

    private static Fahrer[] defaultFahrerTable() {
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

        return new Fahrer[] {fahrer1, fahrer2, fahrer3, fahrer4, fahrer5, fahrer6, fahrer7, fahrer8 };
    }

    // DB Operations
    public static void createDefaultFahrer(Connection conn) throws SQLException {
        initFahrerGroup(conn, defaultFahrerTable());
    }

    public static int initFahrer(Connection conn, Fahrer fahrer) throws SQLException {
        // check if it already exists in DB, if not then initialize it
        List<Integer> fahrerNrs = BusreisenDB.getFahrerDBIds(conn, fahrer.getVorname(), fahrer.getName());
        if (fahrerNrs.isEmpty()) {
            fahrer.setFahrerNr(BusreisenDB.initFahrer(conn, fahrer));
            return fahrer.getFahrerNr();
        }

        for (int i = 0; i < fahrerNrs.size(); i++) {
            if (fahrerNrs.get(i) != fahrer.getFahrerNr()) {
                fahrer.setFahrerNr(BusreisenDB.initFahrer(conn, fahrer));
                return fahrer.getFahrerNr();
            }
        }
        System.out.println("Der Fahrer mit FahrerNr " + fahrer.getFahrerNr() + " ist bereits bei der DB!");
        return -1;
    }

    public static void initFahrerGroup(Connection conn, Fahrer[] fahrers) throws SQLException {
        for (Fahrer fahrer: fahrers) {
            initFahrer(conn, fahrer);
        }
    }

    public static void updateFahrer(Connection conn, Fahrer fahrer) throws SQLException {
        if (BusreisenDB.getFahrerByDBId(conn, fahrer.getFahrerNr()).getName().equals("Error"))
            System.out.println("Die Daten wurden in der Datenbank nicht gefunden!");
        else BusreisenDB.updateFahrer(conn, fahrer, fahrer.getFahrerNr());
    }

    public static List<Fahrer> getFahrer(Connection conn, String vorname, String name) throws SQLException {
        return BusreisenDB.getFahrer(conn, vorname, name);
    }
}
