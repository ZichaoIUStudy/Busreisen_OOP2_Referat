package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Adresse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdressenTable {

    // DB Operations

    public static int initAdresse(Connection conn, Adresse adresse) throws SQLException {
        // check if the adresse with this adresseID already exists in DB, if not then initialize it
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

    public static void initAdressenGroup(Connection conn, Adresse[] adressen) throws SQLException {
        for (Adresse adresse: adressen) {
            initAdresse(conn, adresse);
        }
    }

    public static void updateAdresse(Connection conn, Adresse adresse) throws SQLException {
        if (BusreisenDB.getAdresseByDBId(conn, adresse.getAdresseId()).getPlz().equals("Error"))
            System.out.println("Die Daten wurden in der Datenbank nicht gefunden!");
        else BusreisenDB.updateAdresse(conn, adresse, adresse.getAdresseId());
    }

    public static List<Adresse> getAdresse(Connection conn, String plz) throws SQLException {
        return BusreisenDB.getAdresse(conn, plz);
    }
}