package iuinformatik.busreisen.busreisen_oop2_referat;

import iuinformatik.busreisen.busreisen_oop2_referat.database.DB;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables.Table;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.*;

import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;

/**
 * Schnittstelle zwischen User-Interface und Datenbank
 */
public class Reiseverwaltung {

    /**
     * @return alle gespeicherten Busreisen, abrufbar über ihre {@code Busreise.reiseNr}
     */
    public static TreeMap<Integer, Busreise> getBusreisen() {
        TreeMap<Integer, Busreise> busreisen = new TreeMap<>();
        try (var con =  DB.connect()) {
            try {
                con.setAutoCommit(false);

                List<Busreise> busreisenListe = BusreisenDB.getBusreisenListe(con);
                for (Busreise br : busreisenListe) {
                    busreisen.put(br.getReiseNr(), br);
                }

                con.commit();
            } catch (SQLException e) {
                con.rollback();
            } finally {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return busreisen;
    }

    /**
     * @return alle gespeicherten Busse, abrufbar über ihr {@code Bus.kennzeichen}
     */
    public static TreeMap<String, Bus> getBusse() {
        TreeMap<String, Bus> busse = new TreeMap<>();
        try (var con =  DB.connect()) {
            try {
                con.setAutoCommit(false);

                List<Bus> busListe = BusreisenDB.getBusListe(con);
                for (Bus b : busListe) {
                    busse.put(b.getKennzeichen(), b);
                }

                con.commit();
            } catch (SQLException e) {
                con.rollback();
            } finally {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return busse;
    }

    /**
     * @return alle gespeicherten Fahrer, abrufbar über ihre {@code Fahrer.fahrerNr}
     */
    public static TreeMap<Integer, Fahrer> getFahrer() {
        TreeMap<Integer, Fahrer> fahrer = new TreeMap<>();
        try (var con =  DB.connect()) {
            try {
                con.setAutoCommit(false);

                List<Fahrer> fahrerListe = BusreisenDB.getFahrerListe(con);
                for (Fahrer f : fahrerListe) {
                    fahrer.put(f.getFahrerNr(), f);
                }

                con.commit();
            } catch (SQLException e) {
                con.rollback();
            } finally {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return fahrer;
    }

    /**
     * @return alle gespeicherten Adressen, welche als Reisezielorte gespeichert wurden ( {@code Adresse.adressTyp} = 1 ), abrufbar über ihre {@code Adresse.adresseId}
     */
    public static TreeMap<Integer, Adresse> getReiseAdressen() {
        TreeMap<Integer, Adresse> adressen = new TreeMap<>();
        try (var con =  DB.connect()) {
            try {
                con.setAutoCommit(false);

                List<Adresse> adressenListe = BusreisenDB.getReiseAdressenListe(con);
                for (Adresse a : adressenListe) {
                    adressen.put(a.getAdresseId(), a);
                }

                con.commit();
            } catch (SQLException e) {
                con.rollback();
            } finally {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return adressen;
    }

    /**
     * Fügt eine neue Adresse in der Datenbank ein
     * @param adresse in Datenbank einzufügende Adresse
     * @return die von der Datenbank generierte {@code Adresse.adresseId}
     */
    public static int adresseHinzufuegen(Adresse adresse) {
        int id = 0;
        try (var con = DB.connect()) {
            try {
                con.setAutoCommit(false);
                id = BusreisenDB.initAdresse(con, adresse);
                con.commit();
            } catch (SQLException e) {
                con.rollback();
            } finally {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return id;
    }

    /**
     * Fügt eine neue Busreise in der Datenbank ein
     * @param busreise in Datenbank einzufügende Busreise
     */
    public static void reiseHinzufuegen(Busreise busreise) {
        try (var con = DB.connect()) {
            try {
                con.setAutoCommit(false);

                BusreisenDB.initBusreise(con, busreise);

                con.commit();
            } catch (SQLException e) {
                con.rollback();
            } finally {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Fügt einen neuen Bus in der Datenbank ein
     * @param bus in Datenbank einzufügender Bus
     */
    public static void busHinzufuegen(Bus bus) {
        try (var con = DB.connect()) {
            try {
                con.setAutoCommit(false);
                BusreisenDB.initBus(con, bus);
                con.commit();
            } catch (SQLException e) {
                con.rollback();
            } finally {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Entfernent den übergebenen {@code Bus} aus der Datenbank
     * @param bus zu löschender Bus
     */
    public static void busEntfernen(Bus bus) {
        try (var con = DB.connect()) {
            try {
                con.setAutoCommit(false);
                BusreisenDB.delete(con, Table.BUSSE, BusreisenDB.getBusDBId(con, bus.getKennzeichen()));
                con.commit();
            } catch (SQLException e) {
                con.rollback();
            } finally {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Fügt einen neuen Fahrer in der Datenbank ein
     * @param fahrer in Datenbank einzufügender Fahrer
     */
    public static void fahrerHinzufuegen(Fahrer fahrer) {
        try (var con = DB.connect()) {
            try {
                con.setAutoCommit(false);
                BusreisenDB.initFahrer(con, fahrer);
                con.commit();
            } catch (SQLException e) {
                con.rollback();
            } finally {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Entfernent den übergebenen {@code Fahrer} aus der Datenbank
     * @param fahrer zu löschender Fahrer
     */
    public static void fahrerEntfernen(Fahrer fahrer) {
        try (var con = DB.connect()) {
            try {
                con.setAutoCommit(false);
                BusreisenDB.delete(con, Table.FAHRER, fahrer.getFahrerNr());
                con.commit();
            } catch (SQLException e) {
                con.rollback();
            } finally {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Fügt eine neue Buchung, den Passagier und seine Adresse in der Datenbank ein und updated {@code Busreise.sitzplaetze} der zugehörigen Busreise
     * @param busreise Busreise, welche in der Datenbank geupdatet werden muss
     * @param buchung in der Datenbank einzufügende Buchung
     */
    public static void reiseBuchen(Busreise busreise, Buchung buchung) {
        try (var con = DB.connect()) {
            try {
                con.setAutoCommit(false);

                int adressId = BusreisenDB.initAdresse(con, buchung.getPassagier().getAdresse());
                buchung.getPassagier().getAdresse().setAdresseId(adressId);
                int passagierNr = BusreisenDB.initPassagier(con, buchung.getPassagier());
                buchung.getPassagier().setPassagierNr(passagierNr);

                BusreisenDB.initBuchung(con, buchung);
                BusreisenDB.updateBusreise(con, busreise, busreise.getReiseNr());

                con.commit();
            } catch (SQLException e) {
                con.rollback();
            } finally {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Fügt eine neue Paarbuchung, die Passagiere und ihre Adressen in der Datenbank ein und updated {@code Busreise.sitzplaetze} der zugehörigen Busreise
     * @param busreise Busreise, welche in der Datenbank geupdatet werden muss
     * @param paarbuchung in der Datenbank einzufügende Paarbuchung
     */
    public static void reiseBuchen(Busreise busreise, Paarbuchung paarbuchung) {
        try (var con = DB.connect()) {
            try {
                con.setAutoCommit(false);

                int adressId = BusreisenDB.initAdresse(con, paarbuchung.getPassagier().getAdresse());
                paarbuchung.getPassagier().getAdresse().setAdresseId(adressId);
                int passagierNr = BusreisenDB.initPassagier(con, paarbuchung.getPassagier());
                paarbuchung.getPassagier().setPassagierNr(passagierNr);

                int partnerAdressId = BusreisenDB.initAdresse(con, paarbuchung.getPassagierPartner().getAdresse());
                paarbuchung.getPassagier().getAdresse().setAdresseId(partnerAdressId);
                int passagierPartnerNr = BusreisenDB.initPassagier(con, paarbuchung.getPassagierPartner());
                paarbuchung.getPassagierPartner().setPassagierNr(passagierPartnerNr);

                BusreisenDB.initBuchung(con, paarbuchung);
                BusreisenDB.updateBusreise(con, busreise, busreise.getReiseNr());

                con.commit();
            } catch (SQLException e) {
                con.rollback();
            } finally {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}