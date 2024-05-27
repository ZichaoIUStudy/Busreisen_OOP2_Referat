package iuinformatik.busreisen.busreisen_oop2_referat.objects;

import iuinformatik.busreisen.busreisen_oop2_referat.enums.Praeferenz;

public class Buchung {

    private int buchungsNr;
    private Busreise busreise;
    private Passagier passagier;
    private int sitzplatz = -1;
    private Praeferenz praeferenz = Praeferenz.KEINE;
    private boolean inklusiveHeizdecke;

    public int getBuchungsNr() {
        return buchungsNr;
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
}