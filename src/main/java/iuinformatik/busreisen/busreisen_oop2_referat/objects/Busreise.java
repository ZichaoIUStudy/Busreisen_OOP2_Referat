package iuinformatik.busreisen.busreisen_oop2_referat.objects;

import iuinformatik.busreisen.busreisen_oop2_referat.Colors;
import iuinformatik.busreisen.busreisen_oop2_referat.database.DB;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Busreise {

    private int reiseNr;
    private Timestamp fahrtbeginn;
    private Timestamp fahrtende;
    private Fahrer fahrer;
    private Bus bus;
    private Adresse zielort;
    private Double kostenProPerson;
    private List<Boolean> sitzplaetze;

    public int getReiseNr() {
        return reiseNr;
    }

    public void setReiseNr(int reiseNr) {
        this.reiseNr = reiseNr;
    }

    public Timestamp getFahrtbeginn() {
        return fahrtbeginn;
    }

    public void setFahrtbeginn(Timestamp fahrtbeginn) {
        this.fahrtbeginn = fahrtbeginn;
    }

    public Timestamp getFahrtEnde() {
        return fahrtende;
    }

    public void setFahrtende(Timestamp fahrtende) {
        this.fahrtende = fahrtende;
    }

    public Fahrer getFahrer() {
        return fahrer;
    }

    public void setFahrer(Fahrer fahrer) {
        this.fahrer = fahrer;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
        this.sitzplaetze = new ArrayList<>(Collections.nCopies(bus.getBusTyp().getAnzahlSitzplaetze(), false));
    }

    public Adresse getZielort() {
        return zielort;
    }

    public void setZielort(Adresse zielort) {
        this.zielort = zielort;
    }

    public Double getKostenProPerson() {
        return kostenProPerson;
    }

    public void setKostenProPerson(Double kostenProPerson) {
        this.kostenProPerson = kostenProPerson;
    }

    public List<Boolean> getSitzplaetze() {
        return sitzplaetze;
    }

    public void setSitzplaetze(boolean[] sitzplaetze) {
        Boolean[] array = DB.convertToBoolean(sitzplaetze);
        this.sitzplaetze = Arrays.asList(array);
    }

    public void setSitzplatzBesetzt(int sitzplatzNr, Boolean besetzt) {
        this.sitzplaetze.set((sitzplatzNr - 1), besetzt);
    }

    /**
     * Ausgabe der aktuellen Besetzung des Busses (gebuchte und freie Sitzplätze).
     */
    public void showBusBesetzung() {
        if (this.getBus().getBusTyp() == BusTyp.KLEINBUS) {
            this.showKleinbusBesetzung();
        } else if (this.getBus().getBusTyp() == BusTyp.REISEBUS) {
            this.showReisebusBesetzung();
        }
    }

    /**
     * Generiert die Ausgabe der aktuellen Besetzung für Kleinbusse.
     */
    private void showKleinbusBesetzung() {
        System.out.println(Colors.RESET + "|=========|");

        int index = 1;
        for (int i = 0; i < 3; i++) {
            StringBuilder lineBuilder = new StringBuilder(Colors.RESET + "|");

            for (int j = 0; j < 3; j++) {
                if (index == 1) {
                    lineBuilder.append(Colors.BLUE + " F ");
                } else if (index == 2) {
                    lineBuilder.append(Colors.BLUE + " R ");
                } else if (this.sitzplaetze.get(index - 1)) {
                    lineBuilder.append(Colors.RED + " X ");
                } else {
                    lineBuilder.append(" " + Colors.RESET).append(index).append(" ");
                }
                index++;
            }
            lineBuilder.append(Colors.RESET + "|");
            System.out.println(lineBuilder);
        }
        System.out.println(Colors.RESET + "|=========|");
    }

    /**
     * Generiert die Ausgabe der aktuellen Besetzung für Reisebusse.
     */
    private void showReisebusBesetzung() {
        System.out.println(Colors.RESET + "|====================|");

        int index = 1;
        for (int i = 0; i < 15; i++) {
            StringBuilder lineBuilder = new StringBuilder(Colors.RESET + "|");

            for (int j = 0; j < 5; j++) {
                if ((j == 2 && index != 55) || ((j == 3 || j == 4) && (index == 27 || index == 29))) {
                    lineBuilder.append(Colors.RESET + "    ");
                    continue;
                } else if (index == 1) {
                    lineBuilder.append(Colors.BLUE + "  F ");
                } else if (index == 3 || index == 4) {
                    lineBuilder.append(Colors.BLUE + "  R ");
                } else {
                    if (String.valueOf(index).length() == 2 && !this.sitzplaetze.get(index - 1)) {
                        lineBuilder.append(" ");
                    } else {
                        lineBuilder.append("  ");
                    }
                    if (this.sitzplaetze.get(index - 1)) {
                        lineBuilder.append(Colors.RED + "X ");
                    } else {
                        lineBuilder.append(Colors.RESET).append(index).append(" ");
                    }
                }
                index++;
            }
            lineBuilder.append(Colors.RESET + "|");
            System.out.println(lineBuilder);
        }
        System.out.println(Colors.RESET + "|====================|");
    }
}