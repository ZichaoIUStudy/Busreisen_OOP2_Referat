package iuinformatik.busreisen.busreisen_oop2_referat.enums;

public enum Fuehrerscheinklasse {
    B(1),
    D(2);

    private final int id;

    Fuehrerscheinklasse(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * @param id ID, der zu suchenden {@code Fuehrerscheinklasse}
     * @return {@code Fuehrerscheinklasse}, welche zur angegebenen {@code id} passt
     */
    public static Fuehrerscheinklasse getFuehrerscheinklasseById(int id) {
        return (id == 2) ? D : B;
    }

    /**
     * @return Auflistung der Führerscheinklassen
     */
    public static String getListe() {
        StringBuilder liste = new StringBuilder();
        for (Fuehrerscheinklasse fsk : Fuehrerscheinklasse.values()) {
            liste.append(fsk.toString()).append(", ");
        }
        return liste.substring(0, liste.length() - 2);
    }
}