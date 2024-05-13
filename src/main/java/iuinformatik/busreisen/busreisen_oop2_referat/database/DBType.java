package iuinformatik.busreisen.busreisen_oop2_referat.database;

public enum DBType {
    VARCHAR("VARCHAR(50)");

    private final String dbType;

    DBType(String type) {
        this.dbType = type;
    }
}
