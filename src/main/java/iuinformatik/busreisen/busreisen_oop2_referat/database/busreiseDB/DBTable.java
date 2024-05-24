package iuinformatik.busreisen.busreisen_oop2_referat.database.busreiseDB;

import iuinformatik.busreisen.busreisen_oop2_referat.database.DB;
import iuinformatik.busreisen.busreisen_oop2_referat.database.DBType;
import iuinformatik.busreisen.busreisen_oop2_referat.tables.Table;

import java.sql.SQLException;

public class DBTable {

    public static void BuildBase(){
        try (var conn =  DB.connect()){
            System.out.println("_________Connected to the PostgreSQL database__________");
            try {
                conn.setAutoCommit(false);
                // create Busse table
                DB.create(conn, Table.BUSSE);
                DB.addColumn(conn, Table.BUSSE, Table.Busse.Kennzeichen, DBType.String);
                DB.addColumn(conn, Table.BUSSE, Table.Busse.Zulassung, DBType.Date);
                DB.addColumn(conn, Table.BUSSE, Table.Busse.TuevTermin, DBType.Date);
                DB.addColumn(conn, Table.BUSSE, Table.Busse.GefahreneKilo, DBType.Float);
                DB.addColumn(conn, Table.BUSSE, Table.Busse.KostenProKilo, DBType.Float);
                DB.addArrayColumn(conn, Table.BUSSE, Table.Busse.Sitzplatz, DBType.Bool);

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
                DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.KostenProPerson, DBType.Float);

                // create Buchungen table
                DB.create(conn, Table.BUCHUNGEN);
                //DB.addColumn(conn, "Buchungen", "BuchungsNr", DBType.Int);
                // Wait to be discussed

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
                conn.close();
            }

            System.out.println("_________Successfully create table in the database__________");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
