package iuinformatik.busreisen.busreisen_oop2_referat.objects;

import java.util.Comparator;

public class Adresse implements Comparable<Adresse> {

    private int adresseId;
    private String strasse;
    private String hausnummer;
    private String plz;
    private String ort;
    private int adressTyp;

    public int getAdresseId() {
        return adresseId;
    }

    // WICHTIG! Das Key Value nach der ersten Einstellung soll nicht mehr geändert werden.
    public void setAdresseId(int adresseId) {
        this.adresseId = this.adresseId == 0 ? adresseId : this.adresseId;
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

    public int getAdressTyp() {
        return adressTyp;
    }

    public void setAdressTyp(int adressTyp) {
        this.adressTyp = adressTyp;
    }

    @Override
    public int compareTo(Adresse adresse) {
        return Comparator.comparing(Adresse::getOrt)
                         .thenComparing(Adresse::getStrasse)
                         .thenComparing(Adresse::getHausnummer)
                         .compare(this, adresse);
    }
}