package iuinformatik.busreisen.busreisen_oop2_referat.objects;

import iuinformatik.busreisen.busreisen_oop2_referat.enums.Praeferenz;

public class Buchung {
    private int buchungsNr;
    private Passagier passagier;
    private int sitzplatz = -1;
    private Praeferenz praeferenz = Praeferenz.KEINE;

    //BuchungsNr
    public int getBuchungsNr() {
        return buchungsNr;
    }

    //Passagier
    public Passagier getPassagier() {
        return passagier;
    }

    public void setPassagier (Passagier newPassagier) {
        this.passagier = newPassagier;
    }
    //Sitzplatz
    public int getSitzplatz() {
        return sitzplatz;
    }

    public void setSitzplatz (int newSitzplatz) {
        this.sitzplatz = newSitzplatz;
    }
    //Praeferenz
    public Praeferenz getPraeferenz() {
        return praeferenz;
    }
    public void setPraeferenz (Praeferenz newPraeferenz) {
        this.praeferenz = newPraeferenz;
    }

}