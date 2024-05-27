package iuinformatik.busreisen.busreisen_oop2_referat.test;

import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.Fuehrerscheinklasse;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.Praeferenz;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class PaarbuchungsTest {

    public static void main(String[] args) {
        Fahrer fahrer = new Fahrer();
        fahrer.setName("Müller");
        fahrer.setVorname("Klaus");
        fahrer.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.B);

        Adresse zielort = new Adresse();
        zielort.setStrasse("");
        zielort.setHausnummer(1);
        zielort.setPlz("");
        zielort.setOrt("Berlin");

        Adresse adresse = new Adresse();
        adresse.setStrasse("Musterstraße");
        adresse.setHausnummer(1);
        adresse.setPlz("12345");
        adresse.setOrt("Musterstadt");

        Passagier p1 = new Passagier();
        p1.setName("Mustermann");
        p1.setVorname("Max");
        p1.setAdresse(adresse);

        Passagier p2 = new Passagier();
        p2.setName("Musterfrau");
        p2.setVorname("Maxime");
        p2.setAdresse(adresse);

        // KLEINBUS
        Bus kleinbus = new Bus();
        kleinbus.setKennzeichen("L AB 1234");
        kleinbus.setBusTyp(BusTyp.Kleinbus);
        kleinbus.setZulassung(Date.valueOf(LocalDate.now()));
        kleinbus.setTuevTermin(Date.valueOf(LocalDate.now()));
        kleinbus.setGefahreneKilometer(123.4);
        kleinbus.setKostenProKilometer(12.34);
        //kleinbus.setSitzplatzBesetzt(4, Boolean.TRUE); // wenn gesetzt dann Ausgabe 6, sonst 4

        Busreise busreiseKleinbus = new Busreise();
        busreiseKleinbus.setFahrtbeginn(new Timestamp(Date.valueOf(LocalDate.now()).getTime()));
        busreiseKleinbus.setFahrtende(new Timestamp(Date.valueOf(LocalDate.now()).getTime()));
        busreiseKleinbus.setFahrer(fahrer);
        busreiseKleinbus.setBus(kleinbus);
        busreiseKleinbus.setZielort(zielort);
        busreiseKleinbus.setKostenProPerson(129.99);

        Paarbuchung pbKleinbus = new Paarbuchung();
        pbKleinbus.setBusreise(busreiseKleinbus);
        pbKleinbus.setPassagier(p1);
        pbKleinbus.setSitzplatz(5);
        pbKleinbus.setPraeferenz(Praeferenz.KEINE);

        System.out.println("Kleinbus: " + pbKleinbus.getPartnerSitzplatz());

        // REISEBUS
        Bus reisebus = new Bus();
        reisebus.setKennzeichen("L AB 1234");
        reisebus.setBusTyp(BusTyp.Reisebus);
        reisebus.setZulassung(Date.valueOf(LocalDate.now()));
        reisebus.setTuevTermin(Date.valueOf(LocalDate.now()));
        reisebus.setGefahreneKilometer(123.4);
        reisebus.setKostenProKilometer(12.34);
        //reisebus.setSitzplatzBesetzt(55, Boolean.TRUE); // wenn gesetzt dann Ausgabe 57, sonst 55

        Busreise busreiseReisebus = new Busreise();
        busreiseReisebus.setFahrtbeginn(new Timestamp(Date.valueOf(LocalDate.now()).getTime()));
        busreiseReisebus.setFahrtende(new Timestamp(Date.valueOf(LocalDate.now()).getTime()));
        busreiseReisebus.setFahrer(fahrer);
        busreiseReisebus.setBus(reisebus);
        busreiseReisebus.setZielort(zielort);
        busreiseReisebus.setKostenProPerson(129.99);

        Paarbuchung pbReisebus = new Paarbuchung();
        pbReisebus.setBusreise(busreiseReisebus);
        pbReisebus.setPassagier(p1);
        pbReisebus.setSitzplatz(56);
        pbReisebus.setPraeferenz(Praeferenz.KEINE);

        System.out.println("Reisebus: " + pbReisebus.getPartnerSitzplatz());
    }
}
