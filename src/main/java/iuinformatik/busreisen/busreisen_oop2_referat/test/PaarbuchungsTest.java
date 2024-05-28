package iuinformatik.busreisen.busreisen_oop2_referat.test;

import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.*;

public class PaarbuchungsTest {

    public static void main(String[] args) {
        // KLEINBUS
        Bus kleinbus = new Bus();
        kleinbus.setBusTyp(BusTyp.KLEINBUS);
        //kleinbus.setSitzplatzBesetzt(4, Boolean.TRUE); // wenn gesetzt dann Ausgabe 6, sonst 4

        Busreise busreiseKleinbus = new Busreise();
        busreiseKleinbus.setBus(kleinbus);

        Paarbuchung pbKleinbus = new Paarbuchung();
        pbKleinbus.setBusreise(busreiseKleinbus);
        pbKleinbus.setSitzplatz(5);

        System.out.println("Kleinbus: " + pbKleinbus.getPartnerSitzplatz());

        // REISEBUS
        Bus reisebus = new Bus();
        reisebus.setBusTyp(BusTyp.REISEBUS);
        //reisebus.setSitzplatzBesetzt(55, Boolean.TRUE); // wenn gesetzt dann Ausgabe 57, sonst 55

        Busreise busreiseReisebus = new Busreise();
        busreiseReisebus.setBus(reisebus);

        Paarbuchung pbReisebus = new Paarbuchung();
        pbReisebus.setBusreise(busreiseReisebus);
        pbReisebus.setSitzplatz(56);

        System.out.println("Reisebus: " + pbReisebus.getPartnerSitzplatz());
    }
}
