package iuinformatik.busreisen.busreisen_oop2_referat.tables;

public enum Table {
    BUSSE("Busse"),
    ADRESSEN("Adressen"),
    PASSAGIERE("Passagiere"),
    BUSREISEN("Busreisen"),
    BUCHUNGEN("Buchungen"),
    FAHRER("Fahrer");

    private final String tableName;

    public static final class Busse {
        public static final String Kennzeichen = "Kennzeichen";
        public static final String Zulassung = "Zulassung";
        public static final String TuevTermin = "TuevTermin";
        public static final String GefahreneKilo = "GefahreneKilo";
        public static final String KostenProKilo = "KostenProKilo";
        public static final String Sitzplatz = "Sitzplatz";
    }

    public static final class Fahrer {
        public static final String FahrerNr = "FahrerNr";
        public static final String Name = "Name";
        public static final String Vorname = "Vorname";
        public static final String FuehrerscheinKlasse = "FuehrerscheinKlasse";
    }

    public static final class Adressen {
        public static final String AdressId = "AdressId";
        public static final String Strasse = "Strasse";
        public static final String Hausnummer = "Hausnummer";
        public static final String PLZ = "PLZ";
        public static final String Ort = "Ort";
    }

    public static final class Passagiere {
        public static final String PassagierNr = "PassagierNr";
        public static final String Name = "Name";
        public static final String Vorname = "Vorname";
        public static final String AdressId = "AdressId";
    }

    public static final class Busreisen {
        public static final String ReiseNr = "ReiseNr";
        public static final String Fahrtbeginn = "Fahrtbeginn";
        public static final String Fahrtende = "Fahrtende";
        public static final String FahrerNr = "FahrerNr";
        public static final String BusKennzeichen = "BusKennzeichen";
        public static final String AdressId = "AdressId";
        public static final String KostenProPerson = "KostenProPerson";
    }

    public static final class Buchungen {
        public static final String BuchungsNr = "BuchungsNr";
        public static final String PassagierNr = "PassagierNr";
        public static final String PassagierPartnerNr = "PassagierPartnerNr";
        public static final String Sitzplatz = "Sitzplatz";
        public static final String PartnerSitzplatz = "PartnerSitzplatz";
        public static final String Praeferenz = "Praeferenz";
        //public static final String Sitzplatz = "BuchungsNr";
    }

    Table(String name) {
        this.tableName = name;
    }
}
