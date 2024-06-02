package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.objectMethods;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Adresse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Activities to operate Adresse Object in DB.
 */
public class AdresseDB {

    /**
     * Insert a single address.
     * check if the adresse with this adresseID already exists in DB, if not then initialize it.
     * @param conn
     * @param adresse
     * @return the Adresse id as int
     * @throws SQLException
     */
    public static int initAdresse(Connection conn, Adresse adresse) throws SQLException {
        List<Integer> adresseIds = BusreisenDB.getAdresseDBIds(conn, adresse.getPlz());
        if (adresseIds.isEmpty()) {
            adresse.setAdresseId(BusreisenDB.initAdresse(conn, adresse));
            return adresse.getAdresseId();
        }

        for (int i = 0; i < adresseIds.size(); i++) {
            if (adresseIds.get(i) != adresse.getAdresseId()) {
                adresse.setAdresseId(BusreisenDB.initAdresse(conn, adresse));
                return adresse.getAdresseId();            }
        }
        System.out.println("Die Adresse mit AdresseId " + adresse.getAdresseId() + " ist bereits bei der DB!");
        return -1;
    }

    /**
     * To insert multiple addresses at once.
     * @param conn
     * @param adressen
     * @throws SQLException
     */
    public static void initAdressenGroup(Connection conn, Adresse[] adressen) throws SQLException {
        for (Adresse adresse: adressen) {
            initAdresse(conn, adresse);
        }
    }

    /**
     * Update address, checked by the parameter plz, if it's not found, show warning.
     * @param conn
     * @param adresse
     * @throws SQLException
     */
    public static void updateAdresse(Connection conn, Adresse adresse) throws SQLException {
        if (BusreisenDB.getAdresseByDBId(conn, adresse.getAdresseId()).getPlz().equals("Error"))
            System.out.println("Die Daten wurden in der Datenbank nicht gefunden!");
        else BusreisenDB.updateAdresse(conn, adresse, adresse.getAdresseId());
    }

    /**
     * Get addresses by {@param plz} as a List.
     * @param conn
     * @param plz
     * @return return the list of Adresse.
     * @throws SQLException
     */
    public static List<Adresse> getAdresse(Connection conn, String plz) throws SQLException {
        return BusreisenDB.getAdresse(conn, plz);
    }
}