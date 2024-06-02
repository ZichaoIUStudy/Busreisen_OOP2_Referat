package iuinformatik.busreisen.busreisen_oop2_referat.enums;

public enum Praeferenz {
    KEINE(0),
    FENSTERPLATZ(1),
    GANGPLATZ(2);

    private final int id;

    Praeferenz(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    /**
     * @param id ID, der zu suchenden {@code Praeferenz}
     * @return {@code Praeferenz}, welche zur angegebenen {@code id} passt
     */
    public static Praeferenz getPraeferenzById(int id) {
        return switch (id) {
            case 1 -> FENSTERPLATZ;
            case 2 -> GANGPLATZ;
            default -> KEINE;
        };
    }
}