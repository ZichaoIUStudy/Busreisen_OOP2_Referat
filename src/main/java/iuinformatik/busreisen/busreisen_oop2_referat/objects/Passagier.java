package iuinformatik.busreisen.busreisen_oop2_referat.objects;

public class Passagier {
    private int passagierNr;
    private String name;
    private String vorname;
    private Adresse adresse;

    //PassagierNr
    public int getPassagierNr() {
        return passagierNr;
    }

    //name
    public String getName() {
        return name;
    }

    public void setName (String newName) {
        this.name = newName;
    }
    //vorname
    public String getVorname() {
        return vorname;
    }

    public void setVorname (String newVorname) {
        this.vorname = newVorname;
    }
    //adresse
    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse (Adresse newAdresse) {
        this.adresse = newAdresse;
    }

}
