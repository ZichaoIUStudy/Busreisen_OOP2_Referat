package iuinformatik.busreisen.busreisen_oop2_referat.database;

/**
 * Enum for exchanging the name of java data type to sql data type.
 */
public enum DBType {
    String("VARCHAR(50)"),
    LongString("VARCHAR(255)"),
    Bool("BOOL"),
    Int("INT"),
    Double("NUMERIC"),
    Date("DATE"),
    Time("TIME"),
    TimeStamp("TIMESTAMP");

    private final String dbType;

    DBType(String type) {
        this.dbType = type;
    }

    public String toString() {
        return this.dbType;
    }
}
