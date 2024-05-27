package iuinformatik.busreisen.busreisen_oop2_referat.enums;

public enum BusTyp {
    Kleinbus(1),
    Reisebus(2);

    private final int id;

    BusTyp(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static BusTyp getBusTypById(int id) {
        return (id == 2) ? Reisebus : Kleinbus;
    }
}