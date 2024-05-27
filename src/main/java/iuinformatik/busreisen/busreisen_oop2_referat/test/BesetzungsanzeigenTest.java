package iuinformatik.busreisen.busreisen_oop2_referat.test;

import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Bus;

import java.sql.Date;
import java.time.LocalDate;

public class BesetzungsanzeigenTest {

    public static void main(String[] args) {
        // Kleinbus
        Bus kleinbus = new Bus();
        kleinbus.setKennzeichen("L AB 1234");
        kleinbus.setBusTyp(BusTyp.Kleinbus);
        kleinbus.setZulassung(Date.valueOf(LocalDate.now()));
        kleinbus.setTuevTermin(Date.valueOf(LocalDate.now()));
        kleinbus.setGefahreneKilometer(123.4);
        kleinbus.setKostenProKilometer(12.34);
        kleinbus.setSitzplatzBesetzt(3, Boolean.TRUE);
        kleinbus.setSitzplatzBesetzt(4, Boolean.TRUE);
        kleinbus.setSitzplatzBesetzt(9, Boolean.TRUE);

        kleinbus.showBusBesetzung();

        // Reisebus
        Bus reisebus = new Bus();
        reisebus.setKennzeichen("L AB 1234");
        reisebus.setBusTyp(BusTyp.Reisebus);
        reisebus.setZulassung(Date.valueOf(LocalDate.now()));
        reisebus.setTuevTermin(Date.valueOf(LocalDate.now()));
        reisebus.setGefahreneKilometer(123.4);
        reisebus.setKostenProKilometer(12.34);
        reisebus.setSitzplatzBesetzt(5, Boolean.TRUE);
        reisebus.setSitzplatzBesetzt(19, Boolean.TRUE);
        reisebus.setSitzplatzBesetzt(27, Boolean.TRUE);
        reisebus.setSitzplatzBesetzt(35, Boolean.TRUE);
        reisebus.setSitzplatzBesetzt(55, Boolean.TRUE);

        reisebus.showBusBesetzung();
    }
}
