package iuinformatik.busreisen.busreisen_oop2_referat.objects;

public class Adresse {

    private int adresseId;
    private String strasse;
    private String hausnummer;
    private String plz;
    private String ort;

    // AdresseID must be assigned.
    public Adresse(int adresseId) {
        setAdresseId(adresseId);
    }

    public int getAdresseId() {
        return adresseId;
    }

    // WICHTIG! Das Key Value nach der ersten Einstellung soll nicht mehr geändert werden.
    public void setAdresseId(int adressId) {
        this.adresseId = this.adresseId == 0 ? adressId : this.adresseId;
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