package iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb;

import iuinformatik.busreisen.busreisen_oop2_referat.database.DB;
import iuinformatik.busreisen.busreisen_oop2_referat.database.DBType;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.Fuehrerscheinklasse;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.Praeferenz;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.*;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables.Table;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// for every object in DB, this class offers init/update/select methods, delete method is not suggested to use.
public class BusreisenDB extends DB {

    protected static void createTables(Connection conn) throws SQLException {
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
        //DB.addColumn(conn, Table.FAHRER, Table.Fahrer.FahrerNr, DBType.Int);
        DB.addColumn(conn, Table.FAHRER, Table.Fahrer.Name, DBType.String);
        DB.addColumn(conn, Table.FAHRER, Table.Fahrer.Vorname, DBType.String);
        DB.addColumn(conn, Table.FAHRER, Table.Fahrer.FuehrerscheinKlasse, DBType.Int);

        // create Adressen table
        DB.create(conn, Table.ADRESSEN);
        //DB.addColumn(conn, Table.ADRESSEN, Table.Adressen.AdresseId, DBType.Int);
        DB.addColumn(conn, Table.ADRESSEN, Table.Adressen.Strasse, DBType.LongString);
        DB.addColumn(conn, Table.ADRESSEN, Table.Adressen.Hausnummer, DBType.String);
        DB.addColumn(conn, Table.ADRESSEN, Table.Adressen.PLZ, DBType.String);
        DB.addColumn(conn, Table.ADRESSEN, Table.Adressen.Ort, DBType.LongString);

        // create Passagiere table
        DB.create(conn, Table.PASSAGIERE);
        //DB.addColumn(conn, Table.PASSAGIERE, Table.Passagiere.PassagierNr, DBType.Int);
        DB.addColumn(conn, Table.PASSAGIERE, Table.Passagiere.Name, DBType.String);
        DB.addColumn(conn, Table.PASSAGIERE, Table.Passagiere.Vorname, DBType.String);
        DB.addColumn(conn, Table.PASSAGIERE, Table.Passagiere.AdressId, DBType.Int);

        // create Busreisen table
        DB.create(conn, Table.BUSREISEN);
        //DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.ReiseNr, DBType.Int);
        DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.Fahrtbeginn, DBType.TimeStamp);
        DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.Fahrtende, DBType.TimeStamp);
        DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.FahrerNr, DBType.Int);
        DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.BusKennzeichen, DBType.String);
        DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.AdresseId, DBType.Int);
        DB.addColumn(conn, Table.BUSREISEN, Table.Busreisen.KostenProPerson, DBType.Double);
        DB.addArrayColumn(conn, Table.BUSREISEN, Table.Busreisen.Sitzplaetze, DBType.Bool);

        // create Buchungen table
        DB.create(conn, Table.BUCHUNGEN);
        //DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.BuchungsNr, DBType.Int);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.PassagierNr, DBType.Int);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.Sitzplatz, DBType.Int);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.Praeferenz, DBType.Int);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.ReiseNr, DBType.Int);
        DB.addColumn(conn, Table.BUCHUNGEN, Table.Buchungen.Heizdecke, DBType.Bool);
    }

    // Initialize objects
    // Bus
    private static int initializeBusDB(Connection conn, String kennzeichen, int busTypNr, Date zulassung ,Double kostenProKilo) throws SQLException {

        int id = DB.insertString(conn, Table.BUSSE, Table.Busse.Kennzeichen, kennzeichen);
        DB.updateDate(conn, Table.BUSSE, Table.Busse.Zulassung, zulassung, id);
        DB.updateDouble(conn, Table.BUSSE, Table.Busse.GefahreneKilo,0.0 ,id);
        DB.updateDouble(conn, Table.BUSSE, Table.Busse.KostenProKilo, kostenProKilo,id);
        DB.updateInt(conn, Table.BUSSE, Table.Busse.BusTypNr, busTypNr, id);

        return id;
    }
    public static int initBus(Connection conn, Bus bus) throws SQLException {
        return initializeBusDB(conn, bus.getKennzeichen(), bus.getBusTyp().getId(), bus.getZulassung(), bus.getKostenProKilometer());
    }

    // Fahrer
    private static int initializeFahrerDB(Connection conn, String name, String vorname ,int fuehrerscheinKlasse) throws SQLException {

        int id = DB.insertString(conn, Table.FAHRER, Table.Fahrer.Name, name);
        DB.updateString(conn, Table.FAHRER, Table.Fahrer.Vorname,vorname ,id);
        DB.updateInt(conn, Table.FAHRER, Table.Fahrer.FuehrerscheinKlasse, fuehrerscheinKlasse,id);

        return id;
    }
    public static int initFahrer(Connection conn, Fahrer fahrer) throws SQLException {
        return initializeFahrerDB(conn, fahrer.getName(), fahrer.getVorname(), fahrer.getHoechsteFuehrerscheinklasse().getId());
    }

    // Adresse
    private static int initializeAdresseDB(Connection conn, String strasse, String hausnummer ,String plz, String ort) throws SQLException {

        int id = DB.insertString(conn, Table.ADRESSEN, Table.Adressen.Strasse, strasse);
        DB.updateString(conn, Table.ADRESSEN, Table.Adressen.Hausnummer,hausnummer ,id);
        DB.updateString(conn, Table.ADRESSEN, Table.Adressen.PLZ, plz,id);
        DB.updateString(conn, Table.ADRESSEN, Table.Adressen.Ort, ort,id);

        return id;
    }
    public static int initAdresse(Connection conn, Adresse adresse) throws SQLException {
        return initializeAdresseDB(conn, adresse.getStrasse(), adresse.getHausnummer(), adresse.getPlz(), adresse.getOrt());
    }

    // Passagier
    private static int initializePassagierDB(Connection conn, String name, String vorname ,int adresseId) throws SQLException {

        int id = DB.insertString(conn, Table.PASSAGIERE, Table.Passagiere.Name, name);
        DB.updateString(conn, Table.PASSAGIERE, Table.Passagiere.Vorname, vorname, id);
        DB.updateInt(conn, Table.PASSAGIERE, Table.Passagiere.AdressId, adresseId, id);

        return id;
    }
    public static int initPassagier(Connection conn, Passagier passagier) throws SQLException {
        return initializePassagierDB(conn, passagier.getName(), passagier.getVorname(), passagier.getAdresse().getAdresseId());
    }

    // Busreise
    private static int initializeBusreiseDB(Connection conn, Timestamp fahrtbeginn, Timestamp fahrtende , int fahrerNr,
                                           Bus bus, int adresseId, Double kostenProPerson) throws SQLException {

        int id = DB.insertString(conn, Table.BUSREISEN, Table.Busreisen.BusKennzeichen, bus.getKennzeichen());
        DB.updateTimStamp(conn, Table.BUSREISEN, Table.Busreisen.Fahrtbeginn, fahrtbeginn, id);
        DB.updateTimStamp(conn, Table.BUSREISEN, Table.Busreisen.Fahrtende, fahrtende, id);
        DB.updateInt(conn, Table.BUSREISEN, Table.Busreisen.FahrerNr, fahrerNr, id);
        DB.updateInt(conn, Table.BUSREISEN, Table.Busreisen.AdresseId, adresseId, id);
        DB.updateDouble(conn, Table.BUSREISEN, Table.Busreisen.KostenProPerson,kostenProPerson ,id);

        boolean[] sitzplaetze = new boolean[bus.getBusTyp().getAnzahlSitzplaetze()];
        Arrays.fill(sitzplaetze,false);
        DB.updateBoolArray(conn, Table.BUSREISEN, Table.Busreisen.Sitzplaetze, sitzplaetze, id);

        return id;
    }
    public static int initBusreise(Connection conn, Busreise busreise) throws SQLException {
        return initializeBusreiseDB(conn, busreise.getFahrtbeginn(), busreise.getFahrtEnde(),
                busreise.getFahrer().getFahrerNr(), busreise.getBus(), busreise.getZielort().getAdresseId(), busreise.getKostenProPerson());
    }

    // Buchunu
    private static int initializeBuchungDB(Connection conn, int passagierNr, int sitzplatz , int praeferenz, int reiseNr, boolean heizdecke) throws SQLException {

        int id = DB.insertInt(conn, Table.BUCHUNGEN, Table.Buchungen.Sitzplatz, sitzplatz);
        DB.updateInt(conn, Table.BUCHUNGEN, Table.Buchungen.PassagierNr, passagierNr, id);
        DB.updateInt(conn, Table.BUCHUNGEN, Table.Buchungen.Praeferenz, praeferenz, id);
        DB.updateInt(conn, Table.BUCHUNGEN, Table.Buchungen.ReiseNr, reiseNr, id);
        DB.updateBool(conn, Table.BUCHUNGEN, Table.Buchungen.Heizdecke, heizdecke, id);;

        return id;
    }
    public static int initBuchung(Connection conn, Buchung buchung) throws SQLException {
        return initializeBuchungDB(conn, buchung.getPassagier().getPassagierNr(),
                buchung.getSitzplatz(), buchung.getPraeferenz().getId(), buchung.getBusreise().getReiseNr(), buchung.isInklusiveHeizdecke());
    }

    // Update objects
    // Bus
    private static void updateBusDB(Connection conn, int busTypNr, Date zulassung, Date tuevTermin,
                                   Double gefahreneKilo, Double kostenProKilo, int id) throws SQLException {

        DB.updateInt(conn, Table.BUSSE, Table.Busse.BusTypNr, busTypNr, id);
        DB.updateDate(conn, Table.BUSSE, Table.Busse.Zulassung, zulassung, id);
        DB.updateDate(conn, Table.BUSSE, Table.Busse.TuevTermin, tuevTermin, id);
        DB.updateDouble(conn, Table.BUSSE, Table.Busse.GefahreneKilo,gefahreneKilo ,id);
        DB.updateDouble(conn, Table.BUSSE, Table.Busse.KostenProKilo, kostenProKilo,id);
    }
    public static void updateBus(Connection conn, Bus bus, int id) throws SQLException {
        updateBusDB(conn, bus.getBusTyp().getId(), bus.getZulassung(), bus.getTuevTermin(), bus.getGefahreneKilometer(), bus.getKostenProKilometer(), id);
    }

    // Fahrer
    private static void updateFahrerDB(Connection conn, String name, String vorname, int fuehrerscheinKlasse, int id) throws SQLException {

        DB.updateString(conn, Table.FAHRER, Table.Fahrer.Name, name, id);
        DB.updateString(conn, Table.FAHRER, Table.Fahrer.Vorname,vorname ,id);
        DB.updateInt(conn, Table.FAHRER, Table.Fahrer.FuehrerscheinKlasse, fuehrerscheinKlasse, id);
    }
    public static void updateFahrer(Connection conn, Fahrer fahrer, int fahrerNr) throws SQLException {
        updateFahrerDB(conn, fahrer.getName(), fahrer.getVorname(), fahrer.getHoechsteFuehrerscheinklasse().getId(), fahrerNr);
    }

    // Adresse
    private static void updateAdresseDB(Connection conn, String strasse, String hausnummer ,String plz, String ort, int id) throws SQLException {

        DB.updateString(conn, Table.ADRESSEN, Table.Adressen.Strasse, strasse, id);
        DB.updateString(conn, Table.ADRESSEN, Table.Adressen.Hausnummer,hausnummer ,id);
        DB.updateString(conn, Table.ADRESSEN, Table.Adressen.PLZ, plz,id);
        DB.updateString(conn, Table.ADRESSEN, Table.Adressen.Ort, ort,id);
    }
    public static void updateAdresse(Connection conn, Adresse adresse, int adresseId) throws SQLException {
        updateAdresseDB(conn, adresse.getStrasse(), adresse.getHausnummer(), adresse.getPlz(), adresse.getOrt(), adresseId);
    }

    // Passagier
    private static void updatePassagierDB(Connection conn, String name, String vorname, int adresseId, int id) throws SQLException {

        DB.updateString(conn, Table.PASSAGIERE, Table.Passagiere.Name, name, id);
        DB.updateString(conn, Table.PASSAGIERE, Table.Passagiere.Vorname,vorname ,id);
        DB.updateInt(conn, Table.PASSAGIERE, Table.Passagiere.AdressId, adresseId,id);
    }
    public static void updatePassagier(Connection conn, Passagier passagier, int passagierNr) throws SQLException {
        updatePassagierDB(conn, passagier.getName(), passagier.getVorname(), passagier.getAdresse().getAdresseId(), passagierNr);
    }

    // Busreise
    private static void updateBusreiseDB(Connection conn, Timestamp fahrtbeginn, Timestamp fahrtende , int fahrerNr,
                                        Bus bus, int adresseId, Double kostenProPerson, boolean[] sitzplaetze, int id) throws SQLException {

        DB.updateTimStamp(conn, Table.BUSREISEN, Table.Busreisen.Fahrtbeginn, fahrtbeginn, id);
        DB.updateTimStamp(conn, Table.BUSREISEN, Table.Busreisen.Fahrtende, fahrtende, id);
        DB.updateInt(conn, Table.BUSREISEN, Table.Busreisen.FahrerNr, fahrerNr, id);
        DB.updateString(conn, Table.BUSREISEN, Table.Busreisen.BusKennzeichen, bus.getKennzeichen(), id);
        DB.updateInt(conn, Table.BUSREISEN, Table.Busreisen.AdresseId, adresseId, id);
        DB.updateDouble(conn, Table.BUSREISEN, Table.Busreisen.KostenProPerson,kostenProPerson ,id);
        DB.updateBoolArray(conn, Table.BUSREISEN, Table.Busreisen.Sitzplaetze, sitzplaetze, id);
    }
    public static void updateBusreise(Connection conn, Busreise busreise, int reiseNr) throws SQLException {
        List<Boolean> list = busreise.getSitzplaetze();
        boolean[] boolArray = new boolean[list.size()];
        for(int i = 0; i < list.size(); i++) boolArray[i] = list.get(i);
        updateBusreiseDB(conn, busreise.getFahrtbeginn(), busreise.getFahrtEnde(), busreise.getFahrer().getFahrerNr(),
                busreise.getBus(), busreise.getZielort().getAdresseId(), busreise.getKostenProPerson(), boolArray, reiseNr);
    }

    // Buchung
    private static void updateBuchungDB(Connection conn, int passagierNr, int sitzplatz , int praeferenz, int reiseNr, boolean heizdecke, int id) throws SQLException {

        DB.updateInt(conn, Table.BUCHUNGEN, Table.Buchungen.PassagierNr, passagierNr, id);
        DB.updateInt(conn, Table.BUCHUNGEN, Table.Buchungen.Sitzplatz, sitzplatz, id);
        DB.updateInt(conn, Table.BUCHUNGEN, Table.Buchungen.Praeferenz, praeferenz, id);
        DB.updateInt(conn, Table.BUCHUNGEN, Table.Buchungen.ReiseNr, reiseNr, id);
        DB.updateBool(conn, Table.BUCHUNGEN, Table.Buchungen.Heizdecke, heizdecke, id);
    }
    public static void updateBuchung(Connection conn, Buchung buchung, int buchungsNr) throws SQLException {
        updateBuchungDB(conn, buchung.getPassagier().getPassagierNr(),
                buchung.getSitzplatz(), buchung.getPraeferenz().getId(), buchung.getBusreise().getReiseNr(), buchung.isInklusiveHeizdecke(), buchungsNr);
    }

    // Delete object

    public static void delete(Connection conn, Table table, int id) throws SQLException {
        DB.delete(conn, table, id);
    }

    // Select objects
    // Bus
    public static Bus getBusByDBId(Connection conn, int id) throws SQLException {
        Bus bus = new Bus(DB.selectString(conn, Table.BUSSE, Table.Busse.Kennzeichen, id));
        bus.setBusTyp(BusTyp.getBusTypById(DB.selectInt(conn, Table.BUSSE, Table.Busse.BusTypNr, id)));
        bus.setZulassung(DB.selectDate(conn, Table.BUSSE, Table.Busse.Zulassung, id));
        bus.setTuevTermin(DB.selectDate(conn, Table.BUSSE, Table.Busse.TuevTermin, id));
        bus.setGefahreneKilometer(DB.selectDouble(conn, Table.BUSSE, Table.Busse.GefahreneKilo, id));
        bus.setKostenProKilometer(DB.selectDouble(conn, Table.BUSSE, Table.Busse.KostenProKilo, id));
        return bus;
    }
    public static int getBusDBId(Connection conn, String kennzeichen) throws SQLException {
        if (!DB.selectIdsPerString(conn, Table.BUSSE, Table.Busse.Kennzeichen, kennzeichen).isEmpty())
            return DB.selectIdsPerString(conn, Table.BUSSE, Table.Busse.Kennzeichen, kennzeichen).get(0);
        return -1;
    }
    public static Bus getBus(Connection conn, String kennzeichen) throws SQLException {
        return getBusByDBId(conn, getBusDBId(conn, kennzeichen));
    }

    // Fahrer
    public static Fahrer getFahrerByDBId(Connection conn, int id) throws SQLException {
        Fahrer fahrer = new Fahrer();
        fahrer.setFahrerNr(id);
        fahrer.setName(DB.selectString(conn, Table.FAHRER, Table.Fahrer.Name, id));
        fahrer.setVorname(DB.selectString(conn, Table.FAHRER, Table.Fahrer.Vorname, id));
        fahrer.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.getFuehrerscheinklasseById
                (DB.selectInt(conn, Table.FAHRER, Table.Fahrer.FuehrerscheinKlasse, id)));
        return fahrer;
    }
    public static List<Integer> getFahrerDBIds(Connection conn, String vorame, String name) throws SQLException {
        return DB.selectIdsPerString(conn, Table.FAHRER, Table.Fahrer.Vorname, vorame, Table.Fahrer.Name, name);
    }
    public static List<Fahrer> getFahrer(Connection conn, String vorname, String name) throws SQLException {
        List<Fahrer> fahrerList = new ArrayList<>();
        List<Integer> fahrerIdList = getFahrerDBIds(conn, vorname, name);
        for (Integer integer : fahrerIdList) {
            fahrerList.add(getFahrerByDBId(conn, integer));
        }
        return fahrerList;
    }

    // Adresse
    public static Adresse getAdresseByDBId(Connection conn, int id) throws SQLException {
        Adresse adresse = new Adresse();
        adresse.setAdresseId(id);
        adresse.setStrasse(DB.selectString(conn, Table.ADRESSEN, Table.Adressen.Strasse, id));
        adresse.setHausnummer(DB.selectString(conn, Table.ADRESSEN, Table.Adressen.Hausnummer, id));
        adresse.setPlz(DB.selectString(conn, Table.ADRESSEN, Table.Adressen.PLZ, id));
        adresse.setOrt(DB.selectString(conn, Table.ADRESSEN, Table.Adressen.Ort, id));
        return adresse;
    }
    public static List<Integer> getAdresseDBIds(Connection conn, String plz) throws SQLException {
        return DB.selectIdsPerString(conn, Table.ADRESSEN, Table.Adressen.PLZ, plz);
    }
    public static List<Adresse> getAdresse(Connection conn, String plz) throws SQLException {
        List<Adresse> adresseList = new ArrayList<>();
        List<Integer> adresseIdList = getAdresseDBIds(conn, plz);
        for (Integer integer : adresseIdList) {
            adresseList.add(getAdresseByDBId(conn, integer));
        }
        return adresseList;
    }

    // Passagier
    public static Passagier getPassagierByDBId(Connection conn, int id) throws SQLException {
        Passagier passagier = new Passagier();
        passagier.setPassagierNr(id);
        passagier.setName(DB.selectString(conn, Table.PASSAGIERE, Table.Passagiere.Name, id));
        passagier.setVorname(DB.selectString(conn, Table.PASSAGIERE, Table.Passagiere.Vorname, id));
        passagier.setAdresse(getAdresseByDBId(conn, DB.selectInt(conn, Table.PASSAGIERE, Table.Passagiere.AdressId, id)));
        return passagier;
    }
    public static List<Integer> getPassagierDBIds(Connection conn, String vorame, String name) throws SQLException {
        return DB.selectIdsPerString(conn, Table.PASSAGIERE, Table.Passagiere.Vorname, vorame, Table.Passagiere.Name, name);
    }
    public static List<Passagier> getPassagier(Connection conn, String vorname, String name) throws SQLException {
        List<Passagier> passagierList = new ArrayList<>();
        List<Integer> passagierIdList = getPassagierDBIds(conn, vorname, name);
        for (Integer integer : passagierIdList) {
            passagierList.add(getPassagierByDBId(conn, integer));
        }
        return passagierList;
    }

    // Busreise
    public static Busreise getBusreiseByDBId(Connection conn, int id) throws SQLException {
        Busreise busreise = new Busreise();
        busreise.setReiseNr(id);
        busreise.setFahrtbeginn(DB.selectTimStamp(conn, Table.BUSREISEN, Table.Busreisen.Fahrtbeginn, id));
        busreise.setFahrtende(DB.selectTimStamp(conn, Table.BUSREISEN, Table.Busreisen.Fahrtende, id));
        busreise.setFahrer(getFahrerByDBId(conn, DB.selectInt(conn, Table.BUSREISEN, Table.Busreisen.FahrerNr,id)));
        busreise.setBus(getBus(conn, DB.selectString(conn, Table.BUSREISEN, Table.Busreisen.BusKennzeichen, id)));
        busreise.setZielort(getAdresseByDBId(conn, DB.selectInt(conn, Table.BUSREISEN, Table.Busreisen.AdresseId, id)));
        busreise.setKostenProPerson(DB.selectDouble(conn, Table.BUSREISEN, Table.Busreisen.KostenProPerson, id));
        busreise.setSitzplaetze(DB.selectBoolArray(conn, Table.BUSREISEN, Table.Busreisen.Sitzplaetze, id));
        return busreise;
    }
    public static List<Integer> getBusreiseDBIds(Connection conn, Adresse adresse) throws SQLException {
        return DB.selectIdsPerInt(conn, Table.BUSREISEN, Table.Busreisen.AdresseId, adresse.getAdresseId());
    }
    public static List<Busreise> getBusreise(Connection conn, Adresse adresse) throws SQLException {
        List<Busreise> busreiseList = new ArrayList<>();
        List<Integer> busreiseIdList = getBusreiseDBIds(conn, adresse);
        for (Integer integer : busreiseIdList) {
            busreiseList.add(getBusreiseByDBId(conn, integer));
        }
        return busreiseList;
    }

    // Buchung
    public static Buchung getBuchungByDBId(Connection conn, int id) throws SQLException {
        Buchung buchung = new Buchung();
        buchung.setBuchungsNr(id);
        buchung.setPassagier(getPassagierByDBId(conn, DB.selectInt(conn, Table.BUCHUNGEN, Table.Buchungen.PassagierNr, id)));
        buchung.setSitzplatz(DB.selectInt(conn, Table.BUCHUNGEN, Table.Buchungen.Sitzplatz, id));
        buchung.setPraeferenz(Praeferenz.getPraeferenzById(DB.selectInt(conn, Table.BUCHUNGEN, Table.Buchungen.Praeferenz, id)));
        buchung.setBusreise(getBusreiseByDBId(conn, DB.selectInt(conn, Table.BUCHUNGEN, Table.Buchungen.ReiseNr, id)));
        buchung.setInklusiveHeizdecke(DB.selectBool(conn, Table.BUCHUNGEN, Table.Buchungen.Heizdecke, id));
        return buchung;
    }
    public static List<Integer> getBuchungDBIds(Connection conn, Busreise busreise) throws SQLException {
        return DB.selectIdsPerInt(conn, Table.BUCHUNGEN, Table.Buchungen.ReiseNr, busreise.getReiseNr());
    }
    public static List<Buchung> getBuchung(Connection conn, Busreise busreise) throws SQLException {
        List<Buchung> buchungList = new ArrayList<>();
        List<Integer> buchungIdList = getBuchungDBIds(conn, busreise);
        for (Integer integer : buchungIdList) {
            buchungList.add(getBuchungByDBId(conn, integer));
        }
        return buchungList;
    }

}
