package iuinformatik.busreisen.busreisen_oop2_referat.objects;

import iuinformatik.busreisen.busreisen_oop2_referat.enums.Fuehrerscheinklasse;

import java.util.Comparator;

public class Fahrer implements Comparable<Fahrer> {

    private int fahrerNr;
    private String name;
    private String vorname;
    private Fuehrerscheinklasse hoechsteFuehrerscheinklasse;

    public int getFahrerNr() {
        return fahrerNr;
    }

    // WICHTIG! Das Key Value nach der ersten Einstellung soll nicht mehr geändert werden.
    public void setFahrerNr(int fahrerNr) {
        this.fahrerNr = this.fahrerNr == 0 ? fahrerNr : this.fahrerNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Fuehrerscheinklasse getHoechsteFuehrerscheinklasse() {
        return hoechsteFuehrerscheinklasse;
    }

    public void setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse hoechsteFuehrerscheinklasse) {
        this.hoechsteFuehrerscheinklasse = hoechsteFuehrerscheinklasse;
    }

    @Override
    public int compareTo(Fahrer fahrer) {
        return Comparator.comparing(Fahrer::getHoechsteFuehrerscheinklasse)
                         .thenComparing(Fahrer::getName)
                         .thenComparing(Fahrer::getVorname)
                         .compare(this, fahrer);
    }
}