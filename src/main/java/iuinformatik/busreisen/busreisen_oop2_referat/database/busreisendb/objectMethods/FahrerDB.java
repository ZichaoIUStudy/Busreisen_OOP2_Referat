package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.objectMethods;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Fahrer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Activities to operate Fahrer Object in DB.
 */
public class FahrerDB {

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
