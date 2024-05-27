package iuinformatik.busreisen.busreisen_oop2_referat.enums;

public enum BusTyp {
    Kleinbus(1, 9),
    Reisebus(2, 57);

    private final int id;
    private final int anzahlSitzplaetze;

    BusTyp(int id, int anzahlSitzplaetze) {
        this.id = id;
        this.anzahlSitzplaetze = anzahlSitzplaetze;
    }

    public int getId() {
        return id;
    }

    public int getAnzahlSitzplaetze() {
        return anzahlSitzplaetze;
    }

    public static BusTyp getBusTypById(int id) {
        return (id == 2) ? Reisebus : Kleinbus;
    }
}