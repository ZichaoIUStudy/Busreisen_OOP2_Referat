package iuinformatik.busreisen.busreisen_oop2_referat.objects;

import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;

import java.util.*;
import java.sql.Date;

public class Bus {

    private String kennzeichen;
    private BusTyp busTyp;
    private Date zulassung;
    private Date tuevTermin;
    private Double gefahreneKilometer;
    private Double kostenProKilometer;

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

    public void addGefahreneKilometer(Double gefahreneKilometer) {
        this.gefahreneKilometer += gefahreneKilometer;
    }

    public Double getKostenProKilometer() {
        return kostenProKilometer;
    }

    public void setKostenProKilometer(Double kostenProKilometer) {
        this.kostenProKilometer = kostenProKilometer;
    }
}