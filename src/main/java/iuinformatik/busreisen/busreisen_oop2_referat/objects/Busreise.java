package iuinformatik.busreisen.busreisen_oop2_referat.objects;

import iuinformatik.busreisen.busreisen_oop2_referat.Util;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;

import java.sql.Timestamp;
import java.util.*;

public class Busreise implements Comparable<Busreise> {

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

    // WICHTIG! Das Key Value nach der ersten Einstellung soll nicht mehr geändert werden.
    public void setReiseNr(int reiseNr) {
        this.reiseNr = this.reiseNr == 0 ? reiseNr : this.reiseNr;
    }

    public Timestamp getFahrtbeginn() {
        return fahrtbeginn;
    }

    public void setFahrtbeginn(Timestamp fahrtbeginn) {
        this.fahrtbeginn = fahrtbeginn;
    }

    public Timestamp getFahrtende() {
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
        this.setSitzplatzBesetzt(1, Boolean.TRUE);
        if (this.getBus().getBusTyp().equals(BusTyp.KLEINBUS)) {
            this.setSitzplatzBesetzt(2, Boolean.TRUE);
        } else {
            this.setSitzplatzBesetzt(3, Boolean.TRUE);
            this.setSitzplatzBesetzt(4, Boolean.TRUE);
        }
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
        Boolean[] array = Util.convertToBoolean(sitzplaetze);
        this.sitzplaetze = Arrays.asList(array);
    }

    public void setSitzplatzBesetzt(int sitzplatzNr, Boolean besetzt) {
        this.sitzplaetze.set((sitzplatzNr - 1), besetzt);
    }

    @Override
    public int compareTo(Busreise busreise) {
        return Comparator.comparing(Busreise::getZielort).thenComparing(Busreise::getFahrtbeginn).compare(this, busreise);
    }

    /**
     * @return aktuelle Bus-Besetzungsübersicht (gebuchte und freie Sitzplätze)
     */
    public String getBusBesetzung() {
        String busBesetzung = this.getBusBesetzungsBeschreibung();
        if (this.getBus().getBusTyp() == BusTyp.KLEINBUS) {
            busBesetzung += this.getKleinbusBesetzung();
        } else {
            busBesetzung += this.getReisebusBesetzung();
        }
        return busBesetzung;
    }

    /**
     * @return Legende der Bus-Besetzungsübersicht
     */
    private String getBusBesetzungsBeschreibung() {
        return Util.Colors.BLUE + "F - Fahrer" + Util.Colors.RESET + "\n" +
               Util.Colors.BLUE + "R - Reiseleiter" + Util.Colors.RESET + "\n" +
               Util.Colors.RED + "X - Gebucht" + Util.Colors.RESET + "\n";
    }

    /**
     * @return aktuelle Bus-Besetzungsübersicht für Kleinbusse.
     */
    private String getKleinbusBesetzung() {
        StringBuilder outBuilder = new StringBuilder("|=========|\n");

        int index = 1;
        for (int i = 0; i < 3; i++) {
            StringBuilder lineBuilder = new StringBuilder(Util.Colors.RESET + "|");

            for (int j = 0; j < 3; j++) {
                if (index == 1) {
                    lineBuilder.append(Util.Colors.BLUE + " F ");
                } else if (index == 2) {
                    lineBuilder.append(Util.Colors.BLUE + " R ");
                } else if (this.sitzplaetze.get(index - 1)) {
                    lineBuilder.append(Util.Colors.RED + " X ");
                } else {
                    lineBuilder.append(" " + Util.Colors.RESET).append(index).append(" ");
                }
                index++;
            }
            lineBuilder.append(Util.Colors.RESET + "|\n");
            outBuilder.append(lineBuilder);
        }
        outBuilder.append(Util.Colors.RESET + "|=========|");
        return outBuilder.toString();
    }

    /**
     * @return aktuelle Bus-Besetzungsübersicht für Reisebusse.
     */
    private String getReisebusBesetzung() {
        StringBuilder outBuilder = new StringBuilder("|====================|\n");

        int index = 1;
        for (int i = 0; i < 15; i++) {
            StringBuilder lineBuilder = new StringBuilder(Util.Colors.RESET + "|");

            for (int j = 0; j < 5; j++) {
                if ((j == 2 && index != 55) || ((j == 3 || j == 4) && (index == 27 || index == 29))) {
                    lineBuilder.append(Util.Colors.RESET + "    ");
                    continue;
                } else if (index == 1) {
                    lineBuilder.append(Util.Colors.BLUE + "  F ");
                } else if (index == 3 || index == 4) {
                    lineBuilder.append(Util.Colors.BLUE + "  R ");
                } else {
                    if (String.valueOf(index).length() == 2 && !this.sitzplaetze.get(index - 1)) {
                        lineBuilder.append(" ");
                    } else {
                        lineBuilder.append("  ");
                    }
                    if (this.sitzplaetze.get(index - 1)) {
                        lineBuilder.append(Util.Colors.RED + "X ");
                    } else {
                        lineBuilder.append(Util.Colors.RESET).append(index).append(" ");
                    }
                }
                index++;
            }
            lineBuilder.append(Util.Colors.RESET + "|\n");
            outBuilder.append(lineBuilder);
        }
        outBuilder.append(Util.Colors.RESET + "|====================|");
        return outBuilder.toString();
    }
}