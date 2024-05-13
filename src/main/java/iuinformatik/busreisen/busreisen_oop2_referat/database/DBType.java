package iuinformatik.busreisen.busreisen_oop2_referat.database;

public enum DBType {
    String("VARCHAR(50)"),
    LongString("VARCHAR(255)"),
    Bool("BOOL"),
    Int("INT"),
    Float("FLOAT"),
    Date("DATE"),
    Time("TIME"),
    TimeStamp("TIMESTAMP");

    private final String dbType;

    DBType(String type) {
        this.dbType = type;
    }
}
