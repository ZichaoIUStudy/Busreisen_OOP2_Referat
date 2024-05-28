package iuinformatik.busreisen.busreisen_oop2_referat.tables;

import iuinformatik.busreisen.busreisen_oop2_referat.database.DB;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Bus;

import java.util.Date;

public class BusseTable {

    // VW 9-Sitzer-Busse
    public void setBusTypeEins (Bus bus) {
        bus.setKostenProKilometer(0.5);
    }

    // Cityliner
    public void setBusTypeZwei (Bus bus) {
        bus.setKostenProKilometer(1.2);
    }

    public void createDefaultBus() {
        Bus VW_T6_01 = new Bus();
        setBusTypeEins(VW_T6_01);
        VW_T6_01.setKennzeichen("L-EK 6789");
        VW_T6_01.setZulassung("01.03.2021");
        VW_T6_01.setTuevTermin("03/2025");
        VW_T6_01.setGefahreneKilometer(124.446);
        //DB.insert(conn, "Busse", "Kennzeichen", bus01.getKennzeichen());

        Bus VW_T6_02 = new Bus();
        setBusTypeEins(VW_T6_02);
        VW_T6_02.setKennzeichen("L-FR 1011");
        VW_T6_02.setZulassung("01.03.2021");
        VW_T6_02.setTuevTermin("03/2025");
        VW_T6_02.setGefahreneKilometer(102.759);

        Bus cityliner01 = new Bus();
        setBusTypeZwei(cityliner01);
        cityliner01.setKennzeichen("L-AF 1234");
        cityliner01.setZulassung("01.05.2015");
        cityliner01.setTuevTermin("05/2025");
        cityliner01.setGefahreneKilometer(862.345);

        Bus cityliner02 = new Bus();
        setBusTypeZwei(cityliner02);
        cityliner02.setKennzeichen("L-BX 5678");
        cityliner02.setZulassung("01.05.2015");
        cityliner02.setTuevTermin("05/2025");
        cityliner02.setGefahreneKilometer(789.456);

        Bus cityliner03 = new Bus();
        setBusTypeZwei(cityliner03);
        cityliner03.setKennzeichen("L-CG 9101");
        cityliner03.setZulassung("01.07.2016");
        cityliner03.setTuevTermin("07/2024");
        cityliner03.setGefahreneKilometer(678.901);

        Bus cityliner04 = new Bus();
        setBusTypeZwei(cityliner04);
        cityliner04.setKennzeichen("L-DH 2345");
        cityliner04.setZulassung("01.07.2016");
        cityliner04.setTuevTermin("07/2024");
        cityliner04.setGefahreneKilometer(696.332);
    }

}
