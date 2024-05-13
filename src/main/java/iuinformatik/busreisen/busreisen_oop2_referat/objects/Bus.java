package iuinformatik.busreisen.busreisen_oop2_referat.objects;

import java.util.Date;

public class Bus {
    private String kennzeichen;
    private Date zulassung;
    private Date tuevTermin;
    private Double gefahreneKilometer;
    private Double kostenProKilometer;

    //Kennzeichen
    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setKennzeichen(String newKennzeichen) {
        this.kennzeichen = newKennzeichen;
    }
    //Zulassung
    public Date getZulassung() {
        return zulassung;
    }

    public void setZulassung(Date newZulassung) {
        this.zulassung = newZulassung;
    }

    //tuevTermin
    public Date getTuevTermin() {
        return tuevTermin;
    }

    public void setTuevTermin(Date newTuevTermin) {
        this.tuevTermin = newTuevTermin;
    }
    //gefahreneKilometer
    public Double getGefahreneKilometer() {
        return gefahreneKilometer;
    }

    public void setGefahreneKilometer(Double newGefahreneKilometer) {
        this.gefahreneKilometer = newGefahreneKilometer;
    }
    //kostenProKilometer
    public Double getKostenProKilometer() {
        return kostenProKilometer;
    }

    public void setKostenProKilometer(Double newKostenProKilometer) {
        this.kostenProKilometer = newKostenProKilometer;
    }
}
