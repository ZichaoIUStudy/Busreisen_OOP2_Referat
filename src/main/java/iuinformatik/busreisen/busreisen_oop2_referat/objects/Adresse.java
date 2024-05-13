package iuinformatik.busreisen.busreisen_oop2_referat.objects;

public class Adresse {
    private String strasse;
    private int hausnummer;
    private String plz;
    private String ort;

    //Strasse
    public String getStrasse () {
        return strasse;
    }

    public void setStrasse (String newStrasse) {
        this.strasse = newStrasse;
    }
    //Hausnummer
    public int getHausnummer () {
        return hausnummer;
    }

    public void setHausnummer (int newHausnummer) {
        this.hausnummer= newHausnummer;
    }
    //plz
    public String getPlz () {
        return plz;
    }

    public void setPlz (String newPlz) {
        this.plz= newPlz;
    }
    //ort
    public String getOrt () {
        return ort;
    }

    public void setOrt (String newOrt) {
        this.ort= newOrt;
    }
}
