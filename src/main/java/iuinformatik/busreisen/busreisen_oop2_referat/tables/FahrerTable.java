package iuinformatik.busreisen.busreisen_oop2_referat.tables;

import iuinformatik.busreisen.busreisen_oop2_referat.enums.Fuehrerscheinklasse;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.Fahrer;

public class FahrerTable {

    public void createDefaultFahrer() {
        Fahrer Fahrer1 = new Fahrer();
        Fahrer1.setName("Müller");
        Fahrer1.setVorname("Petra");
        Fahrer1.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.B);

        Fahrer Fahrer2 = new Fahrer();
        Fahrer1.setName("Schmidt");
        Fahrer1.setVorname("Thomas");
        Fahrer1.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.B);

        Fahrer Fahrer3 = new Fahrer();
        Fahrer1.setName("Fischer");
        Fahrer1.setVorname("Gabriele");
        Fahrer1.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        Fahrer Fahrer4 = new Fahrer();
        Fahrer1.setName("Weber");
        Fahrer1.setVorname("Wolfgang");
        Fahrer1.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        Fahrer Fahrer5 = new Fahrer();
        Fahrer1.setName("Schulz");
        Fahrer1.setVorname("Karin");
        Fahrer1.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        Fahrer Fahrer6 = new Fahrer();
        Fahrer1.setName("Meyer");
        Fahrer1.setVorname("Jürgen");
        Fahrer1.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        Fahrer Fahrer7 = new Fahrer();
        Fahrer1.setName("Hoffmann");
        Fahrer1.setVorname("Monika");
        Fahrer1.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);

        Fahrer Fahrer8 = new Fahrer();
        Fahrer1.setName("Becker");
        Fahrer1.setVorname("Hans");
        Fahrer1.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.D);


    }
}
