package iuinformatik.busreisen.busreisen_oop2_referat.objects;

import iuinformatik.busreisen.busreisen_oop2_referat.enums.Praeferenz;

import java.util.ArrayList;
import java.util.List;

public class Buchung {

    private int buchungsNr;
    private Passagier passagier;
    private int sitzplatz = -1;
    private Praeferenz praeferenz = Praeferenz.KEINE;
    private Busreise busreise;
    private boolean inklusiveHeizdecke;

    public int getBuchungsNr() {
        return buchungsNr;
    }

    // WICHTIG! Das Key Value nach der ersten Einstellung soll nicht mehr geändert werden.
    public void setBuchungsNr(int buchungsNr) {
        this.buchungsNr = this.buchungsNr == 0 ? buchungsNr : this.buchungsNr;
    }

    public Busreise getBusreise() {
        return busreise;
    }

    public void setBusreise(Busreise busreise) {
        this.busreise = busreise;
    }

    public Passagier getPassagier() {
        return passagier;
    }

    public void setPassagier(Passagier passagier) {
        this.passagier = passagier;
    }

    public int getSitzplatz() {
        return sitzplatz;
    }

    public void setSitzplatz(int sitzplatz) {
        this.sitzplatz = sitzplatz;
    }

    public Praeferenz getPraeferenz() {
        return praeferenz;
    }

    public void setPraeferenz(Praeferenz praeferenz) {
        this.praeferenz = praeferenz;
    }

    public boolean isInklusiveHeizdecke() {
        return inklusiveHeizdecke;
    }

    public void setInklusiveHeizdecke(boolean inklusive) {
        this.inklusiveHeizdecke = inklusive;
    }

    /**
     * Weist automatisch den Sitzplatz anhand der gewählten {@code Buchung.praeferenz} zu.
     */
    public void setSitzplatzByPraeferenz() {
        List<Boolean> sitzplaetze = this.getBusreise().getSitzplaetze();
        if (this.praeferenz.equals(Praeferenz.FENSTERPLATZ)) {
            for (int i = 0; i <= 52; i += 4) {
                if (!sitzplaetze.get(i)) {
                    this.setSitzplatz(i + 1);
                    return;
                }
                if (!sitzplaetze.get(i + 3) && (i + 3) != 27) {
                    this.setSitzplatz(i + 4);
                    return;
                }
            }
        } else if (this.praeferenz.equals(Praeferenz.GANGPLATZ)) {
            for (int i = 1; i <= 53; i += 4) {
                if (!sitzplaetze.get(i)) {
                    this.setSitzplatz(i + 1);
                    return;
                }
                if (!sitzplaetze.get(i + 1) && (i + 1) != 26) {
                    this.setSitzplatz(i + 2);
                    return;
                }
            }
        } else {
            this.setSitzplatz(sitzplaetze.indexOf(Boolean.FALSE) + 1);
        }
    }
}