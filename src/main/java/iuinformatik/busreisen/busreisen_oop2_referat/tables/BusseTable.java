package iuinformatik.busreisen.busreisen_oop2_referat.tables;

import iuinformatik.busreisen.busreisen_oop2_referat.database.DB;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Bus;

public class BusseTable {

    // VW 9-Sitzer-Busse
    public void setBusTypeEins (Bus bus) {
        bus.setKostenProKilometer(12.0);
    }

    // Cityliner
    public void setBusTypeZwei (Bus bus) {
        bus.setKostenProKilometer(20.0);
    }

    public void createDefaultBus() {
        Bus bus01 = new Bus();
        setBusTypeEins(bus01);
        bus01.setKennzeichen("VW_9-Sitzer-Busse_01");
        //DB.insert(conn, "Busse", "Kennzeichen", bus01.getKennzeichen());

        Bus bus02 = new Bus();
        setBusTypeEins(bus02);
        bus02.setKennzeichen("VW_9-Sitzer-Busse_02");

        Bus bus03 = new Bus();
        setBusTypeZwei(bus03);
        bus03.setKennzeichen("Cityliner_01");

        Bus bus04 = new Bus();
        setBusTypeZwei(bus04);
        bus04.setKennzeichen("Cityliner_02");

        Bus bus05 = new Bus();
        setBusTypeZwei(bus05);
        bus05.setKennzeichen("Cityliner_03");

        Bus bus06 = new Bus();
        setBusTypeZwei(bus06);
        bus06.setKennzeichen("Cityliner_04");
    }

}
