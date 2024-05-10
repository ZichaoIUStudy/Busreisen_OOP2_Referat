package iuinformatik.busreisen.busreisen_oop2_referat;

import iuinformatik.busreisen.busreisen_oop2_referat.objects.*;

import java.util.TreeMap;

public class Reiseverwaltung {

    public TreeMap<Integer, Busreise> getBusreisen() {
        TreeMap<Integer, Busreise> busreisen = new TreeMap<>();

        // TODO: DB - SELECT

        return busreisen;
    }

    public TreeMap<Integer, Bus> getBusse() {
        TreeMap<Integer, Bus> busse = new TreeMap<>();

        // TODO: DB - SELECT

        return busse;
    }

    public TreeMap<Integer, Fahrer> getFahrer() {
        TreeMap<Integer, Fahrer> fahrer = new TreeMap<>();

        // TODO: DB - SELECT

        return fahrer;
    }

    public TreeMap<Integer, Buchung> getBuchungen() {
        TreeMap<Integer, Buchung> buchungen = new TreeMap<>();

        // TODO: DB - SELECT

        return buchungen;
    }

    public void reiseHinzufuegen(Busreise busreise) {
        // TODO: VALIDATE + DB - INSERT
    }

    public void busHinzufuegen(Bus bus) {
        // TODO: VALIDATE + DB - INSERT
    }

    public void fahrerHinzufuegen(Fahrer fahrer) {
        // TODO: VALIDATE + DB - INSERT
    }

    public void reiseBuchen(Busreise busreise, Buchung buchung) {
        // TODO: VALIDATE + DB - INSERT
    }
}