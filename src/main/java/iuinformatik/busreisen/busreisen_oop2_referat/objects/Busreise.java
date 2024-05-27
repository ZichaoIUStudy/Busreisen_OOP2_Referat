package iuinformatik.busreisen.busreisen_oop2_referat.objects;

import java.sql.Timestamp;

public class Busreise {

    private int reiseNr;
    private Timestamp fahrtbeginn;
    private Timestamp fahrtende;
    private Fahrer fahrer;
    private Bus bus;
    private Adresse zielort;
    private Double kostenProPerson;

    public int getReiseNr() {
        return reiseNr;
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
}