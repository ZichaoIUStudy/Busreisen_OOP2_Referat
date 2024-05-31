package iuinformatik.busreisen.busreisen_oop2_referat.objects;

public class Passagier {

    private int passagierNr;
    private String name;
    private String vorname;
    private Adresse adresse;

    public int getPassagierNr() {
        return passagierNr;
    }

    // WICHTIG! Das Key Value nach der ersten Einstellung soll nicht mehr geändert werden.
    public void setPassagierNr(int passagierNr) {
        this.passagierNr = this.passagierNr == 0 ? passagierNr : this.passagierNr;
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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

}