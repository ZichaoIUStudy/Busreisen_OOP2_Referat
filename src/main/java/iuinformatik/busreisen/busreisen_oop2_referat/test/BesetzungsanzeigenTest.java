package iuinformatik.busreisen.busreisen_oop2_referat.test;

import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Bus;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Busreise;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class BesetzungsanzeigenTest {

    public static void main(String[] args) {
        // Kleinbus
        Bus kleinbus = new Bus();
        kleinbus.setBusTyp(BusTyp.KLEINBUS);

        Busreise busreiseKleinbus = new Busreise();
        busreiseKleinbus.setBus(kleinbus);
        busreiseKleinbus.setSitzplatzBesetzt(3, Boolean.TRUE);
        busreiseKleinbus.setSitzplatzBesetzt(4, Boolean.TRUE);
        busreiseKleinbus.setSitzplatzBesetzt(9, Boolean.TRUE);
        busreiseKleinbus.showBusBesetzung();

        // Reisebus
        Bus reisebus = new Bus();
        reisebus.setBusTyp(BusTyp.REISEBUS);

        Busreise busreiseReisebus = new Busreise();
        busreiseReisebus.setBus(reisebus);
        busreiseReisebus.setSitzplatzBesetzt(5, Boolean.TRUE);
        busreiseReisebus.setSitzplatzBesetzt(19, Boolean.TRUE);
        busreiseReisebus.setSitzplatzBesetzt(27, Boolean.TRUE);
        busreiseReisebus.setSitzplatzBesetzt(35, Boolean.TRUE);
        busreiseReisebus.setSitzplatzBesetzt(55, Boolean.TRUE);
        busreiseReisebus.showBusBesetzung();
    }
}