package iuinformatik.busreisen.busreisen_oop2_referat.objects;

public class Adresse {

    private int adresseId;
    private String strasse;
    private String hausnummer;
    private String plz;
    private String ort;

    public int getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(int adressId) {
        this.adresseId = adressId;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

}