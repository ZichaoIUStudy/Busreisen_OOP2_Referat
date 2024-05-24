package iuinformatik.busreisen.busreisen_oop2_referat.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bus {

    private String kennzeichen;
    private Date zulassung;
    private Date tuevTermin;
    private Double gefahreneKilometer;
    private Double kostenProKilometer;
    private int platz;
    public List<Boolean> sitzplatz = new ArrayList<>(platz);

    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
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

    public int getPlatz() {return platz;}

    public void setPlatz(int platz) {this.platz = platz;}
}