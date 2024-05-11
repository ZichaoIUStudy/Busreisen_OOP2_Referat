package iuinformatik.busreisen.busreisen_oop2_referat;

import iuinformatik.busreisen.busreisen_oop2_referat.objects.*;

import java.util.TreeMap;

public class Reiseverwaltung {

    public static TreeMap<Integer, Busreise> getBusreisen() {
        TreeMap<Integer, Busreise> busreisen = new TreeMap<>();

        // TODO: DB - SELECT

        return busreisen;
    }

    public static TreeMap<Integer, Bus> getBusse() {
        TreeMap<Integer, Bus> busse = new TreeMap<>();

        // TODO: DB - SELECT

        return busse;
    }

    public static TreeMap<Integer, Fahrer> getFahrer() {
        TreeMap<Integer, Fahrer> fahrer = new TreeMap<>();

        // TODO: DB - SELECT

        return fahrer;
    }

    public static TreeMap<Integer, Buchung> getBuchungen() {
        TreeMap<Integer, Buchung> buchungen = new TreeMap<>();

        // TODO: DB - SELECT

        return buchungen;
    }

    public static void reiseHinzufuegen(Busreise busreise) {
        // TODO: VALIDATE + DB - INSERT
    }

    public static void busHinzufuegen(Bus bus) {
        // TODO: VALIDATE + DB - INSERT
    }

    public static void fahrerHinzufuegen(Fahrer fahrer) {
        // TODO: VALIDATE + DB - INSERT
    }

    public static void reiseBuchen(Busreise busreise, Buchung buchung) {
        // TODO: VALIDATE + DB - INSERT
    }
}