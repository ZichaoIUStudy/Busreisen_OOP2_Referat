package iuinformatik.busreisen.busreisen_oop2_referat.objects;

import java.security.Timestamp;

public class Busreise {
    private int reiseNr;
    private Timestamp fahrtbeginn;
    private Timestamp fahrtende;
    private Fahrer fahrer;
    private Bus bus;
    private Adresse zielort;
    private Double kostenProPerson;

    //reiseNr
    public int getReiseNr(){
        return reiseNr;
    }

    //fahrtbeginn
    public Timestamp getFahrtbeginn(){
        return fahrtbeginn;
    }

    public void setFahrtbeginn(Timestamp newFahrtbeginn) {
        this.fahrtbeginn = newFahrtbeginn;
    }
    //fahrtEnde
    public Timestamp getFahrtEnde(){
        return fahrtende;
    }

    public void setFahrtende(Timestamp newFahrtende) {
        this.fahrtende = newFahrtende;
    }
    //fahrer
    public Fahrer getFahrer(){
        return fahrer;
    }

    public void setFahrer(Fahrer newFahrer) {
        this.fahrer = newFahrer;
    }
    //Bus
    public Bus getBus(){
        return bus;
    }

    public void setBus(Bus newBus) {
        this.bus = newBus;
    }
    //Zielort
    public Adresse getZielort(){
        return zielort;
    }

    public void setZielort(Adresse newZielort) {
        this.zielort = newZielort;
    }
    //kostenProPerson
    public Double getKostenProPerson(){
        return kostenProPerson;
    }

    public void setKostenProPerson(Double newKostenProPerson) {
        this.kostenProPerson = newKostenProPerson;
    }
}
