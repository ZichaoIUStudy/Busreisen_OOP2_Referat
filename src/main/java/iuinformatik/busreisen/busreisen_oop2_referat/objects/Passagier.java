package iuinformatik.busreisen.busreisen_oop2_referat.objects;

public class Passagier {

    private int passagierNr;
    private String name;
    private String vorname;
    private Adresse adresse;

    public int getPassagierNr() {
        return passagierNr;
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