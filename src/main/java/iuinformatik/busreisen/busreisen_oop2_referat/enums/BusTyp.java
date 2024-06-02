package iuinformatik.busreisen.busreisen_oop2_referat.enums;

/**
 * Typ eines Busses, speichert Anzahl der Sitzplätze
 */
public enum BusTyp {
    KLEINBUS(1, 9),
    REISEBUS(2, 57);

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

    /**
     * @param id ID, des zu suchenden {@code BusTyp}
     * @return {@code BusTyp}, welcher zur angegebenen {@code id} passt
     */
    public static BusTyp getBusTypById(int id) {
        return (id == 2) ? REISEBUS : KLEINBUS;
    }

    /**
     * @return Auflistung der BusTypen
     */
    public static String getListe() {
        StringBuilder liste = new StringBuilder();
        for (BusTyp bt : BusTyp.values()) {
            liste.append(bt.getId()).append(" - ").append(bt).append(", ");
        }
        return liste.substring(0, liste.length() - 2);
    }
}