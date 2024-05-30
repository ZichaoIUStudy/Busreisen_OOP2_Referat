package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Passagier;

import java.sql.Connection;
import java.sql.SQLException;

public class PassagiereTable {

    private static Passagier[] defaultPassagiereTable() {

        return new Passagier[] {};
    }

    // DB Operations
    public static int initPassagier(Connection conn, Passagier passagier) throws SQLException {
        // // check if it already exists in DB, if not then initialize it
        if (!(BusreisenDB.getPassagier(conn, passagier.getPassagierNr()).getPassagierNr()==(passagier.getPassagierNr())))
            return BusreisenDB.initPassagier(conn, passagier);
        else {
            System.out.println("Der Passagier mit PassagierNr " + passagier.getPassagierNr() + " ist bereits bei der DB!");
            return -1;
        }
    }

    public static void initPassagiereGroup(Connection conn, Passagier[] passagiere) throws SQLException {
        for (Passagier passagier: passagiere) {
            initPassagier(conn, passagier);
        }
    }

    public static void updatePassagier(Connection conn, Passagier passagier) throws SQLException {
        if (BusreisenDB.getPassagierDBId(conn, passagier.getPassagierNr()) == -1)
            System.out.println("Die Daten wurden in der Datenbank nicht gefunden!");
        else BusreisenDB.updatePassagier(conn, passagier, BusreisenDB.getPassagierDBId(conn, passagier.getPassagierNr()));
    }

    public static Passagier getPassagier(Connection conn, int passagierNr) throws SQLException {
        return BusreisenDB.getPassagier(conn, passagierNr);
    }
}
