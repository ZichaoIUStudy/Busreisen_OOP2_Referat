package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.objectMethods;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Passagier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Activities to operate Passagier Object in DB.
 */
public class PassagierDB {

    public static int initPassagier(Connection conn, Passagier passagier) throws SQLException {
        // // check if it already exists in DB, if not then initialize it
        List<Integer> passagierNrs = BusreisenDB.getPassagierDBIds(conn, passagier.getVorname(), passagier.getName());
        if (passagierNrs.isEmpty()) {
            passagier.setPassagierNr(BusreisenDB.initPassagier(conn, passagier));
            return passagier.getPassagierNr();
        }

        for (int i = 0; i < passagierNrs.size(); i++) {
            if (passagierNrs.get(i) != passagier.getPassagierNr()) {
                passagier.setPassagierNr(BusreisenDB.initPassagier(conn, passagier));
                return passagier.getPassagierNr();
            }
        }

        System.out.println("Der Passagier mit PassagierNr " + passagier.getPassagierNr() + " ist bereits bei der DB!");
        return -1;
    }

    public static void initPassagiereGroup(Connection conn, Passagier[] passagiere) throws SQLException {
        for (Passagier passagier: passagiere) {
            initPassagier(conn, passagier);
        }
    }

    public static void updatePassagier(Connection conn, Passagier passagier) throws SQLException {
        if (BusreisenDB.getPassagierByDBId(conn, passagier.getPassagierNr()).getName().equals("Error"))
            System.out.println("Die Daten wurden in der Datenbank nicht gefunden!");
        else BusreisenDB.updatePassagier(conn, passagier, passagier.getPassagierNr());
    }

    public static List<Passagier> getPassagier(Connection conn, String vorname, String name) throws SQLException {
        return BusreisenDB.getPassagier(conn, vorname, name);
    }
}
