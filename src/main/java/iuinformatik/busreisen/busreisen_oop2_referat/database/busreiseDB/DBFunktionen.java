package iuinformatik.busreisen.busreisen_oop2_referat.database.busreiseDB;

import iuinformatik.busreisen.busreisen_oop2_referat.database.DB;
import iuinformatik.busreisen.busreisen_oop2_referat.database.DBType;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreiseDB.tables.BusseTable;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.Fuehrerscheinklasse;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.Praeferenz;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.*;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreiseDB.tables.Table;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class DBFunktionen {

    public static void BuildTableBase(){
        try (var conn =  DB.connect()){
            System.out.println("_________Connected to the PostgreSQL database__________");
            try {
                conn.setAutoCommit(false);

                CreateTables(conn);

                System.out.println("_________Successfully create table in the database__________");
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
                conn.close();
            }
            System.out.println("_________Finished operation in the database__________");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void InsertDefaultData(){

        try (var conn =  DB.connect()){
            System.out.println("_________Connected to the PostgreSQL database__________");
            try {
                conn.setAutoCommit(false);

                BusseTable.createDefaultBusse(conn);

                System.out.println("_________Successfully initialize data in the database__________");
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
                conn.close();
            }
            System.out.println("_________Finished operation in the database__________");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void SelectData(){
        try (var conn =  DB.connect()){
            System.out.println("_________Connected to the PostgreSQL database__________");
            try {
                conn.setAutoCommit(false);

                //System.out.println(DB.selectString(conn, Table.BUSSE, Table.Busse.Kennzeichen, 1));

                System.out.println("_________Successfully print data from the database__________");
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
                conn.close();
            }
            System.out.println("_________Finished operation in the database__________");
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
        DB.addColumn(conn, Table.BUSSE, Table.Busse.BusTypNr, DBType.Int);

        // create Fahrer table
        DB.create(conn, Table.FAHRER);
        DB.addColumn(conn, Table.FAHRER, Table.Fahrer.FahrerNr, DBType.Int);
        DB.addColumn(conn, Table.FAHRER, Table.Fahrer.Name, DBType.String);
        DB.addColumn(conn, Table.FAHRER, Table.Fahrer.Vorname, DBType.String);
        DB.addColumn(conn, Table.FAHRER, Table.Fahrer.FuehrerscheinKlasse, DBType.Int);

        // create Adressen table
        DB.create(conn, Table.ADRESSEN);
        DB.addColumn(conn, Table.ADRESSEN, Table.Adressen.AdresseId, DBType.Int);
        DB.addColumn(conn, Table.ADRESSEN, Table.Adressen.Strasse, DBType.LongString);
        DB.addColumn(conn, Table.ADRESSEN, Table.Adressen.Hausnummer, DBType.String);
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
        DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.AdresseId, DBType.Int);
        DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.KostenProPerson, DBType.Double);
        DB.addArrayColumn(conn, Table.BUSREISEN, Table.Busreisen.Sitzplaetze, DBType.Bool);

        // create Buchungen table
        DB.create(conn, Table.BUCHUNGEN);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.BuchungsNr, DBType.Int);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.PassagierNr, DBType.Int);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.Sitzplatz, DBType.Int);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.Praeferenz, DBType.Int);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.ReiseNr, DBType.Int);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.Heizdecke, DBType.Bool);
    }

    // Initialize objects
    // Bus
    private static int DBInitializeBus(Connection conn, String kennzeichen, int busTypNr, Date zulassung ,Double kostenProKilo) throws SQLException {

        int id = DB.insertString(conn, Table.BUSSE, Table.Busse.Kennzeichen, kennzeichen);
        DB.updateDate(conn, Table.BUSSE, Table.Busse.Zulassung, zulassung, id);
        DB.updateDouble(conn, Table.BUSSE, Table.Busse.GefahreneKilo,0.0 ,id);
        DB.updateDouble(conn, Table.BUSSE, Table.Busse.KostenProKilo, kostenProKilo,id);
        DB.updateInt(conn, Table.BUSSE, Table.Busse.BusTypNr, busTypNr, id);

        return id;
    }
    public static int InitBus(Connection conn, Bus bus) throws SQLException {
        return DBInitializeBus(conn, bus.getKennzeichen(), bus.getBusTyp().getId(), bus.getZulassung(), bus.getKostenProKilometer());
    }

    // Fahrer
    private static int DBInitializeFahrer(Connection conn, int fahrerNr, String name, String vorname ,int fuehrerscheinKlasse) throws SQLException {

        int id = DB.insertInt(conn, Table.FAHRER, Table.Fahrer.FahrerNr, fahrerNr);
        DB.updateString(conn, Table.FAHRER, Table.Fahrer.Name, name, id);
        DB.updateString(conn, Table.FAHRER, Table.Fahrer.Vorname,vorname ,id);
        DB.updateInt(conn, Table.FAHRER, Table.Fahrer.FuehrerscheinKlasse, fuehrerscheinKlasse,id);

        return id;
    }
    public static int InitFahrer(Connection conn, Fahrer fahrer) throws SQLException {
        return DBInitializeFahrer(conn, fahrer.getFahrerNr(), fahrer.getName(), fahrer.getVorname(), fahrer.getHoechsteFuehrerscheinklasse().getId());
    }

    // Adresse
    private static int DBInitializeAdresse(Connection conn, int adresseId, String strasse, String hausnummer ,String plz, String ort) throws SQLException {

        int id = DB.insertInt(conn, Table.ADRESSEN, Table.Adressen.AdresseId, adresseId);
        DB.updateString(conn, Table.ADRESSEN, Table.Adressen.Strasse, strasse, id);
        DB.updateString(conn, Table.ADRESSEN, Table.Adressen.Hausnummer,hausnummer ,id);
        DB.updateString(conn, Table.ADRESSEN, Table.Adressen.PLZ, plz,id);
        DB.updateString(conn, Table.ADRESSEN, Table.Adressen.Ort, ort,id);

        return id;
    }
    public static int InitAdresse(Connection conn, Adresse adresse) throws SQLException {
        return DBInitializeAdresse(conn, adresse.getAdresseId(), adresse.getStrasse(), adresse.getHausnummer(), adresse.getPlz(), adresse.getOrt());
    }

    // Passagier
    private static int DBInitializePassagier(Connection conn, int passagierNr, String name, String vorname ,int adresseId) throws SQLException {

        int id = DB.insertInt(conn, Table.PASSAGIERE, Table.Passagiere.PassagierNr, passagierNr);
        DB.updateString(conn, Table.PASSAGIERE, Table.Passagiere.Name, name, id);
        DB.updateString(conn, Table.PASSAGIERE, Table.Passagiere.Vorname,vorname ,id);
        DB.updateInt(conn, Table.PASSAGIERE, Table.Passagiere.AdressId, adresseId,id);

        return id;
    }
    public static int InitPassagier(Connection conn, Passagier passagier) throws SQLException {
        return DBInitializePassagier(conn, passagier.getPassagierNr(), passagier.getName(), passagier.getVorname(), passagier.getAdresse().getAdresseId());
    }

    // Busreise
    private static int DBInitializeBusreise(Connection conn, int reiseNr, Timestamp fahrtbeginn, Timestamp fahrtende , int fahrerNr,
                                           Bus bus, int adresseId, Double kostenProPerson) throws SQLException {

        int id = DB.insertInt(conn, Table.BUSREISEN, Table.Busreisen.ReiseNr, reiseNr);
        DB.updateTimStamp(conn, Table.BUSREISEN, Table.Busreisen.Fahrtbeginn, fahrtbeginn, id);
        DB.updateTimStamp(conn, Table.BUSREISEN, Table.Busreisen.Fahrtende, fahrtende, id);
        DB.updateInt(conn, Table.BUSREISEN, Table.Busreisen.FahrerNr, fahrerNr, id);
        DB.updateString(conn, Table.BUSREISEN, Table.Busreisen.BusKennzeichen, bus.getKennzeichen(), id);
        DB.updateInt(conn, Table.BUSREISEN, Table.Busreisen.AdresseId, adresseId, id);
        DB.updateDouble(conn, Table.BUSREISEN, Table.Busreisen.KostenProPerson,kostenProPerson ,id);

        boolean[] sitzplaetze = new boolean[bus.getBusTyp().getAnzahlSitzplaetze()];
        Arrays.fill(sitzplaetze,false);
        DB.updateBoolArray(conn, Table.BUSREISEN, Table.Busreisen.Sitzplaetze, sitzplaetze, id);

        return id;
    }
    public static int InitBusreise(Connection conn, Busreise busreise) throws SQLException {
        return DBInitializeBusreise(conn, busreise.getReiseNr(), busreise.getFahrtbeginn(), busreise.getFahrtEnde(),
                busreise.getFahrer().getFahrerNr(), busreise.getBus(), busreise.getZielort().getAdresseId(), busreise.getKostenProPerson());
    }

    // Buchung
    private static int DBInitializeBuchung(Connection conn, int buchungsNr, int passagierNr, int sitzplatz , int praeferenz, int reiseNr, boolean heizdecke) throws SQLException {

        int id = DB.insertInt(conn, Table.BUCHUNGEN, Table.Buchungen.BuchungsNr, buchungsNr);
        DB.updateInt(conn, Table.BUCHUNGEN, Table.Buchungen.PassagierNr, passagierNr, id);
        DB.updateInt(conn, Table.BUCHUNGEN, Table.Buchungen.Sitzplatz, sitzplatz, id);
        DB.updateInt(conn, Table.BUCHUNGEN, Table.Buchungen.Praeferenz, praeferenz, id);
        DB.updateInt(conn, Table.BUCHUNGEN, Table.Buchungen.ReiseNr, reiseNr, id);
        DB.updateBool(conn, Table.BUCHUNGEN, Table.Buchungen.Heizdecke, heizdecke, id);;

        return id;
    }
    public static int InitBuchung(Connection conn, Buchung buchung) throws SQLException {
        return DBInitializeBuchung(conn, buchung.getBuchungsNr(), buchung.getPassagier().getPassagierNr(),
                buchung.getSitzplatz(), buchung.getPraeferenz().getId(), buchung.getBusreise().getReiseNr(), buchung.isInklusiveHeizdecke());
    }

    // Update objects
    // Bus
    private static void DBUpdateBus(Connection conn, String kennzeichen, int busTypNr, Date zulassung, Date tuevTermin,
                                   Double gefahreneKilo, Double kostenProKilo, int id) throws SQLException {

        DB.updateString(conn, Table.BUSSE, Table.Busse.Kennzeichen, kennzeichen, id);
        DB.updateInt(conn, Table.BUSSE, Table.Busse.BusTypNr, busTypNr, id);
        DB.updateDate(conn, Table.BUSSE, Table.Busse.Zulassung, zulassung, id);
        DB.updateDate(conn, Table.BUSSE, Table.Busse.TuevTermin, tuevTermin, id);
        DB.updateDouble(conn, Table.BUSSE, Table.Busse.GefahreneKilo,gefahreneKilo ,id);
        DB.updateDouble(conn, Table.BUSSE, Table.Busse.KostenProKilo, kostenProKilo,id);
    }
    public static void UpdateBus(Connection conn, Bus bus, int id) throws SQLException {
        DBUpdateBus(conn, bus.getKennzeichen(), bus.getBusTyp().getId(), bus.getZulassung(), bus.getTuevTermin(), bus.getGefahreneKilometer(), bus.getKostenProKilometer(), id);
    }

    // Fahrer
    private static void DBUpdateFahrer(Connection conn, int fahrerNr, String name, String vorname, int fuehrerscheinKlasse, int id) throws SQLException {

        DB.updateInt(conn, Table.FAHRER, Table.Fahrer.FahrerNr, fahrerNr, id);
        DB.updateString(conn, Table.FAHRER, Table.Fahrer.Name, name, id);
        DB.updateString(conn, Table.FAHRER, Table.Fahrer.Vorname,vorname ,id);
        DB.updateInt(conn, Table.FAHRER, Table.Fahrer.FuehrerscheinKlasse, fuehrerscheinKlasse, id);
    }
    public static void UpdateFahrer(Connection conn, Fahrer fahrer, int id) throws SQLException {
        DBUpdateFahrer(conn, fahrer.getFahrerNr(), fahrer.getName(), fahrer.getVorname(), fahrer.getHoechsteFuehrerscheinklasse().getId(), id);
    }

    // Adresse
    private static void DBUpdateAdresse(Connection conn, int adresseId, String strasse, String hausnummer ,String plz, String ort, int id) throws SQLException {

        DB.updateInt(conn, Table.ADRESSEN, Table.Adressen.AdresseId, adresseId, id);
        DB.updateString(conn, Table.ADRESSEN, Table.Adressen.Strasse, strasse, id);
        DB.updateString(conn, Table.ADRESSEN, Table.Adressen.Hausnummer,hausnummer ,id);
        DB.updateString(conn, Table.ADRESSEN, Table.Adressen.PLZ, plz,id);
        DB.updateString(conn, Table.ADRESSEN, Table.Adressen.Ort, ort,id);
    }
    public static void UpdateAdresse(Connection conn, Adresse adresse, int id) throws SQLException {
        DBUpdateAdresse(conn, adresse.getAdresseId(), adresse.getStrasse(), adresse.getHausnummer(), adresse.getPlz(), adresse.getOrt(), id);
    }

    // Passagier
    private static void DBUpdatePassagier(Connection conn, int passagierNr, String name, String vorname, int adresseId, int id) throws SQLException {

        DB.updateInt(conn, Table.PASSAGIERE, Table.Passagiere.PassagierNr, passagierNr, id);
        DB.updateString(conn, Table.PASSAGIERE, Table.Passagiere.Name, name, id);
        DB.updateString(conn, Table.PASSAGIERE, Table.Passagiere.Vorname,vorname ,id);
        DB.updateInt(conn, Table.PASSAGIERE, Table.Passagiere.AdressId, adresseId,id);
    }
    public static void UpdatePassagier(Connection conn, Passagier passagier, int id) throws SQLException {
        DBUpdatePassagier(conn, passagier.getPassagierNr(), passagier.getName(), passagier.getVorname(), passagier.getAdresse().getAdresseId(), id);
    }

    // Busreise
    private static void DBUpdateBusreise(Connection conn, int reiseNr, Timestamp fahrtbeginn, Timestamp fahrtende , int fahrerNr,
                                        Bus bus, int adresseId, Double kostenProPerson, boolean[] sitzplaetze, int id) throws SQLException {

        DB.updateInt(conn, Table.BUSREISEN, Table.Busreisen.ReiseNr, reiseNr, id);
        DB.updateTimStamp(conn, Table.BUSREISEN, Table.Busreisen.Fahrtbeginn, fahrtbeginn, id);
        DB.updateTimStamp(conn, Table.BUSREISEN, Table.Busreisen.Fahrtende, fahrtende, id);
        DB.updateInt(conn, Table.BUSREISEN, Table.Busreisen.FahrerNr, fahrerNr, id);
        DB.updateString(conn, Table.BUSREISEN, Table.Busreisen.BusKennzeichen, bus.getKennzeichen(), id);
        DB.updateInt(conn, Table.BUSREISEN, Table.Busreisen.AdresseId, adresseId, id);
        DB.updateDouble(conn, Table.BUSREISEN, Table.Busreisen.KostenProPerson,kostenProPerson ,id);
        DB.updateBoolArray(conn, Table.BUSREISEN, Table.Busreisen.Sitzplaetze, sitzplaetze, id);
    }
    public static void UpdateBusreise(Connection conn, Busreise busreise, int id) throws SQLException {
        List<Boolean> list = busreise.getSitzplaetze();
        boolean[] boolArray = new boolean[list.size()];
        for(int i = 0; i < list.size(); i++) boolArray[i] = list.get(i);
        DBUpdateBusreise(conn, busreise.getReiseNr(), busreise.getFahrtbeginn(), busreise.getFahrtEnde(), busreise.getFahrer().getFahrerNr(),
                busreise.getBus(), busreise.getZielort().getAdresseId(), busreise.getKostenProPerson(), boolArray, id);
    }

    // Buchung
    private static void DBUpdateBuchung(Connection conn, int buchungsNr, int passagierNr, int sitzplatz , int praeferenz, int reiseNr, boolean heizdecke, int id) throws SQLException {

        DB.updateInt(conn, Table.BUCHUNGEN, Table.Buchungen.BuchungsNr, buchungsNr,id);
        DB.updateInt(conn, Table.BUCHUNGEN, Table.Buchungen.PassagierNr, passagierNr, id);
        DB.updateInt(conn, Table.BUCHUNGEN, Table.Buchungen.Sitzplatz, sitzplatz, id);
        DB.updateInt(conn, Table.BUCHUNGEN, Table.Buchungen.Praeferenz, praeferenz, id);
        DB.updateInt(conn, Table.BUCHUNGEN, Table.Buchungen.ReiseNr, reiseNr, id);
        DB.updateBool(conn, Table.BUCHUNGEN, Table.Buchungen.Heizdecke, heizdecke, id);
    }
    public static void UpdateBuchung(Connection conn, Buchung buchung, int id) throws SQLException {
        DBUpdateBuchung(conn, buchung.getBuchungsNr(), buchung.getPassagier().getPassagierNr(),
                buchung.getSitzplatz(), buchung.getPraeferenz().getId(), buchung.getBusreise().getReiseNr(), buchung.isInklusiveHeizdecke(), id);
    }

    // Delete object

    public static void Delete(Connection conn, Table table, int id) throws SQLException {
        DB.delete(conn, table, id);
    }

    // Select objects
    // Bus
    public Bus getBusByDBId(Connection conn, int id) throws SQLException {
        Bus bus = new Bus();

        bus.setKennzeichen(DB.selectString(conn, Table.BUSSE, Table.Busse.Kennzeichen, id));
        bus.setBusTyp(BusTyp.getBusTypById(DB.selectInt(conn, Table.BUSSE, Table.Busse.BusTypNr, id)));
        bus.setZulassung(DB.selectDate(conn, Table.BUSSE, Table.Busse.Zulassung, id));
        bus.setTuevTermin(DB.selectDate(conn, Table.BUSSE, Table.Busse.TuevTermin, id));
        bus.setGefahreneKilometer(DB.selectDouble(conn, Table.BUSSE, Table.Busse.GefahreneKilo, id));
        bus.setKostenProKilometer(DB.selectDouble(conn, Table.BUSSE, Table.Busse.KostenProKilo, id));
        return bus;
    }
    public int getBusDBId(Connection conn, String kennzeichen) throws SQLException {
        return DB.selectIdPerString(conn, Table.BUSSE, Table.Busse.Kennzeichen, kennzeichen);
    }
    public Bus getBus(Connection conn, String kennzeichen) throws SQLException {
        return getBusByDBId(conn, getBusDBId(conn, kennzeichen));
    }

    // Fahrer
    public Fahrer getFahrerByDBId(Connection conn, int id) throws SQLException {
        Fahrer fahrer = new Fahrer();

        fahrer.setFahrerNr(DB.selectInt(conn, Table.FAHRER, Table.Fahrer.FahrerNr, id));
        fahrer.setName(DB.selectString(conn, Table.FAHRER, Table.Fahrer.Name, id));
        fahrer.setVorname(DB.selectString(conn, Table.FAHRER, Table.Fahrer.Vorname, id));
        fahrer.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.getFuehrerscheinklasseById
                (DB.selectInt(conn, Table.FAHRER, Table.Fahrer.FuehrerscheinKlasse, id)));
        return fahrer;
    }
    public int getFahrerDBId(Connection conn, int fahrerNr) throws SQLException {
        return DB.selectIdPerInt(conn, Table.FAHRER, Table.Fahrer.FahrerNr, fahrerNr);
    }
    public Fahrer getFahrer(Connection conn, int fahrerNr) throws SQLException {
        return getFahrerByDBId(conn, getFahrerDBId(conn, fahrerNr));
    }

    // Adresse
    public Adresse getAdresseByDBId(Connection conn, int id) throws SQLException {
        Adresse adresse = new Adresse();

        adresse.setAdresseId(DB.selectInt(conn, Table.ADRESSEN, Table.Adressen.AdresseId, id));
        adresse.setStrasse(DB.selectString(conn, Table.ADRESSEN, Table.Adressen.Strasse, id));
        adresse.setHausnummer(DB.selectString(conn, Table.ADRESSEN, Table.Adressen.Hausnummer, id));
        adresse.setPlz(DB.selectString(conn, Table.ADRESSEN, Table.Adressen.PLZ, id));
        adresse.setOrt(DB.selectString(conn, Table.ADRESSEN, Table.Adressen.Ort, id));
        return adresse;
    }
    public int getAdresseDBId(Connection conn, int adresseId) throws SQLException {
        return DB.selectIdPerInt(conn, Table.ADRESSEN, Table.Adressen.AdresseId, adresseId);
    }
    public Adresse getAdresse(Connection conn, int adresseId) throws SQLException {
        return getAdresseByDBId(conn, getAdresseDBId(conn, adresseId));
    }

    // Passagier
    public Passagier getPassagierByDBId(Connection conn, int id) throws SQLException {
        Passagier passagier = new Passagier();

        passagier.setPassagierNr(DB.selectInt(conn, Table.PASSAGIERE, Table.Passagiere.PassagierNr, id));
        passagier.setName(DB.selectString(conn, Table.PASSAGIERE, Table.Passagiere.Name, id));
        passagier.setVorname(DB.selectString(conn, Table.PASSAGIERE, Table.Passagiere.Vorname, id));
        passagier.setAdresse(getAdresse(conn, DB.selectInt(conn, Table.PASSAGIERE, Table.Passagiere.AdressId, id)));
        return passagier;
    }
    public int getPassagierDBId(Connection conn, int passagierNr) throws SQLException {
        return DB.selectIdPerInt(conn, Table.PASSAGIERE, Table.Passagiere.PassagierNr, passagierNr);
    }
    public Passagier getPassagier(Connection conn, int fahrerNr) throws SQLException {
        return getPassagierByDBId(conn, getPassagierDBId(conn, fahrerNr));
    }

    // Busreise
    public Busreise getBusreiseByDBId(Connection conn, int id) throws SQLException {
        Busreise busreise = new Busreise();

        busreise.setReiseNr(DB.selectInt(conn, Table.BUSREISEN, Table.Busreisen.ReiseNr, id));
        busreise.setFahrtbeginn(DB.selectTimStamp(conn, Table.BUSREISEN, Table.Busreisen.Fahrtbeginn, id));
        busreise.setFahrtende(DB.selectTimStamp(conn, Table.BUSREISEN, Table.Busreisen.Fahrtende, id));
        busreise.setFahrer(getFahrer(conn, DB.selectInt(conn, Table.BUSREISEN, Table.Busreisen.FahrerNr,id)));
        busreise.setBus(getBus(conn, DB.selectString(conn, Table.BUSREISEN, Table.Busreisen.BusKennzeichen, id)));
        busreise.setZielort(getAdresse(conn, DB.selectInt(conn, Table.BUSREISEN, Table.Busreisen.AdresseId, id)));
        busreise.setKostenProPerson(DB.selectDouble(conn, Table.BUSREISEN, Table.Busreisen.KostenProPerson, id));
        busreise.setSitzplaetze(DB.selectBoolArray(conn, Table.BUSREISEN, Table.Busreisen.Sitzplaetze, id));
        return busreise;
    }
    public int getBusreiseDBId(Connection conn, int reiseNr) throws SQLException {
        return DB.selectIdPerInt(conn, Table.BUSREISEN, Table.Busreisen.ReiseNr, reiseNr);
    }
    public Busreise getBusreise(Connection conn, int reiseNr) throws SQLException {
        return getBusreiseByDBId(conn, getBusreiseDBId(conn, reiseNr));
    }

    // Buchung
    public Buchung getBuchungByDBId(Connection conn, int id) throws SQLException {
        Buchung buchung = new Buchung();

        buchung.setBuchungsNr(DB.selectInt(conn, Table.BUCHUNGEN, Table.Buchungen.BuchungsNr, id));
        buchung.setPassagier(getPassagier(conn, DB.selectInt(conn, Table.BUCHUNGEN, Table.Buchungen.PassagierNr, id)));
        buchung.setSitzplatz(DB.selectInt(conn, Table.BUCHUNGEN, Table.Buchungen.Sitzplatz, id));
        buchung.setPraeferenz(Praeferenz.getPraeferenzById(DB.selectInt(conn, Table.BUCHUNGEN, Table.Buchungen.Praeferenz, id)));
        buchung.setBusreise(getBusreise(conn, DB.selectInt(conn, Table.BUCHUNGEN, Table.Buchungen.ReiseNr, id)));
        buchung.setInklusiveHeizdecke(DB.selectBool(conn, Table.BUCHUNGEN, Table.Buchungen.Heizdecke, id));
        return buchung;
    }
    public int getBuchungDBId(Connection conn, int buchungsNr) throws SQLException {
        return DB.selectIdPerInt(conn, Table.BUCHUNGEN, Table.Buchungen.BuchungsNr, buchungsNr);
    }
    public Buchung getBuchung(Connection conn, int buchungsNr) throws SQLException {
        return getBuchungByDBId(conn, getBuchungDBId(conn, buchungsNr));
    }

}
