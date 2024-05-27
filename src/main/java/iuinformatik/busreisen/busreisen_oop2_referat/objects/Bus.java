package iuinformatik.busreisen.busreisen_oop2_referat.objects;

import iuinformatik.busreisen.busreisen_oop2_referat.Colors;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;

import java.util.*;

public class Bus {

    private String kennzeichen;
    private BusTyp busTyp;
    private Date zulassung;
    private Date tuevTermin;
    private Double gefahreneKilometer;
    private Double kostenProKilometer;
    private List<Boolean> sitzplaetze;

    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    public BusTyp getBusTyp() {
        return busTyp;
    }

    public void setBusTyp(BusTyp busTyp) {
        this.busTyp = busTyp;
        this.sitzplaetze = new ArrayList<>(Collections.nCopies(busTyp.getAnzahlSitzplaetze(), false));
    }

    public Date getZulassung() {
        return zulassung;
    }

    public void setZulassung(Date zulassung) {
        this.zulassung = zulassung;
    }

    public Date getTuevTermin() {
        return tuevTermin;
    }

    public void setTuevTermin(Date tuevTermin) {
        this.tuevTermin = tuevTermin;
    }

    public Double getGefahreneKilometer() {
        return gefahreneKilometer;
    }

    public void setGefahreneKilometer(Double gefahreneKilometer) {
        this.gefahreneKilometer = gefahreneKilometer;
    }

    public Double getKostenProKilometer() {
        return kostenProKilometer;
    }

    public void setKostenProKilometer(Double kostenProKilometer) {
        this.kostenProKilometer = kostenProKilometer;
    }

    public List<Boolean> getSitzplaetze() {
        return sitzplaetze;
    }

    public void setSitzplatzBesetzt(int sitzplatzNr, Boolean besetzt) {
        this.sitzplaetze.set((sitzplatzNr - 1), besetzt);
    }

    /**
     * Ausgabe der aktuellen Besetzung des Busses (gebuchte und freie Sitzplätze)
     */
    public void showBusBesetzung() {
        if (this.busTyp == BusTyp.Kleinbus) {
            this.showKleinbusBesetzung();
        } else if (this.busTyp == BusTyp.Reisebus) {
            this.showReisebusBesetzung();
        }
    }

    /**
     * Generator für die Ausgabe der aktuellen Besetzung für Kleinbusse
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
     * Generator für die Ausgabe der aktuellen Besetzung für Reisebusse
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