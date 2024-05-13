package iuinformatik.busreisen.busreisen_oop2_referat.objects;

public class Fahrer {
    private int fahrerNr;
    private String name;
    private String vorname;
    private Fuehrerscheinklasse hoechsteFuehrerscheinklasse;

    //fahrerNr
    public int getFahrerNr() {
        return fahrerNr;
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
    //hoechsteFuehrerscheinklasse
    public Fuehrerscheinklasse getHoechsteFuehrerscheinklasse() {
        return hoechsteFuehrerscheinklasse;
    }

    public void setHoechsteFuehrerscheinklasse (Fuehrerscheinklasse newHoechsteFuehrerscheinklasse) {
        this.hoechsteFuehrerscheinklasse = newHoechsteFuehrerscheinklasse;
    }
}
