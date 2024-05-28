package iuinformatik.busreisen.busreisen_oop2_referat.database.busreiseDB;

import iuinformatik.busreisen.busreisen_oop2_referat.database.DB;
import iuinformatik.busreisen.busreisen_oop2_referat.database.DBType;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Bus;
import iuinformatik.busreisen.busreisen_oop2_referat.tables.Table;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

public class DBTable {

    public static void BuildBase(){
        try (var conn =  DB.connect()){
            System.out.println("_________Connected to the PostgreSQL database__________");
            try {
                conn.setAutoCommit(false);
                //CreateTables(conn);

                //InitializeBus(conn, "testBus02",BusTyp.Kleinbus, Date.valueOf("2021-2-4"), 12.0);

                System.out.println(DB.selectString(conn, Table.BUSSE, Table.Busse.Kennzeichen, 1));
                System.out.println("_________Successfully create table in the database__________");
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
                conn.close();
            }
            System.out.println("_________Successfully operate in the database__________");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void CreateTables(Connection conn) throws SQLException {
        // create Busse table
        DB.create(conn, Table.BUSSE);
        DB.addColumn(conn, Table.BUSSE, Table.Busse.Kennzeichen, DBType.String);
        DB.addColumn(conn, Table.BUSSE, Table.Busse.Zulassung, DBType.Date);
        DB.addColumn(conn, Table.BUSSE, Table.Busse.TuevTermin, DBType.Date);
        DB.addColumn(conn, Table.BUSSE, Table.Busse.GefahreneKilo, DBType.Double);
        DB.addColumn(conn, Table.BUSSE, Table.Busse.KostenProKilo, DBType.Double);
        DB.addArrayColumn(conn, Table.BUSSE, Table.Busse.Sitzplaetze, DBType.Bool);

        // create Fahrer table
        DB.create(conn, Table.FAHRER);
        DB.addColumn(conn, Table.FAHRER, Table.Fahrer.FahrerNr, DBType.Int);
        DB.addColumn(conn, Table.FAHRER, Table.Fahrer.Name, DBType.String);
        DB.addColumn(conn, Table.FAHRER, Table.Fahrer.Vorname, DBType.String);
        DB.addColumn(conn, Table.FAHRER, Table.Fahrer.FuehrerscheinKlasse, DBType.Int);

        // create Adressen table
        DB.create(conn, Table.ADRESSEN);
        DB.addColumn(conn, Table.ADRESSEN, Table.Adressen.AdressId, DBType.Int);
        DB.addColumn(conn, Table.ADRESSEN, Table.Adressen.Strasse, DBType.LongString);
        DB.addColumn(conn, Table.ADRESSEN, Table.Adressen.Hausnummer, DBType.Int);
        DB.addColumn(conn, Table.ADRESSEN, Table.Adressen.PLZ, DBType.String);
        DB.addColumn(conn, Table.ADRESSEN, Table.Adressen.Ort, DBType.LongString);

        // create Passagiere table
        DB.create(conn, Table.PASSAGIERE);
        DB.addColumn(conn, Table.PASSAGIERE, Table.Passagiere.PassagierNr, DBType.Int);
        DB.addColumn(conn, Table.PASSAGIERE, Table.Passagiere.Name, DBType.String);
        DB.addColumn(conn, Table.PASSAGIERE, Table.Passagiere.Vorname, DBType.String);
        DB.addColumn(conn, Table.PASSAGIERE, Table.Passagiere.AdressId, DBType.Int);

        // create Busreisen table
        DB.create(conn, Table.BUSREISEN);
        DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.ReiseNr, DBType.Int);
        DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.Fahrtbeginn, DBType.TimeStamp);
        DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.Fahrtende, DBType.TimeStamp);
        DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.FahrerNr, DBType.Int);
        DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.BusKennzeichen, DBType.String);
        DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.AdressId, DBType.Int);
        DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.KostenProPerson, DBType.Double);

        // create Buchungen table
        DB.create(conn, Table.BUCHUNGEN);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.BuchungsNr, DBType.Int);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.PassagierNr, DBType.Int);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.PassagierPartnerNr, DBType.Int);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.Sitzplatz, DBType.Int);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.PartnerSitzplatz, DBType.Int);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.Praeferenz, DBType.Int);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.ReiseNr, DBType.Int);
    }

    private static void InitializeTables(Connection conn) throws SQLException {

    }

    public static int DBInitializeBus(Connection conn, String kennzeichen, BusTyp busTyp, Date zulassung ,Double kostenProKilo) throws SQLException {
        int id = DB.insertString(conn, Table.BUSSE, Table.Busse.Kennzeichen, kennzeichen);

        DB.updateDouble(conn, Table.BUSSE, Table.Busse.KostenProKilo, kostenProKilo,id);
        DB.updateDate(conn, Table.BUSSE, Table.Busse.Zulassung, zulassung, id);

        boolean[] sitzplaetze = new boolean[busTyp.getAnzahlSitzplaetze()];
        Arrays.fill(sitzplaetze,false);
        DB.updateBoolArray(conn, Table.BUSSE, Table.Busse.Sitzplaetze, sitzplaetze, id);

        return id;
    }

    public static int InitBus(Connection conn, Bus bus) throws SQLException {
        return DBInitializeBus(conn, bus.getKennzeichen(), bus.getBusTyp(), bus.getZulassung(), bus.getKostenProKilometer());
    }

}
