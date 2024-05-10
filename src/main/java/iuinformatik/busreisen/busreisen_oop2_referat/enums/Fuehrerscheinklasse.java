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

    public static Fuehrerscheinklasse getFuehrerscheinklasseById(int id) {
        return (id == 2) ? D : B;
    }
}