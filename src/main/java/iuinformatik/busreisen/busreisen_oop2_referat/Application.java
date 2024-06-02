package iuinformatik.busreisen.busreisen_oop2_referat;

import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.Fuehrerscheinklasse;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.Praeferenz;
import iuinformatik.busreisen.busreisen_oop2_referat.objects.*;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDBVerwaltung;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Einstiegspunkt des Programms
 */
public class Application extends javafx.application.Application {

    private static final Scanner SCAN = new Scanner(System.in);
    private static final NumberFormat NF = NumberFormat.getNumberInstance(Locale.GERMAN);
    private static final DecimalFormat DF = new DecimalFormat("#.00");
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");
    private static final int LEN = 100;

    private static int login = 0;

    @Override
    public void start(Stage stage) {
        VBox menu = new VBox();

        Button create_default_table = new Button("create default table");
        create_default_table.setOnMouseClicked(mouseEvent -> {
            BusreisenDBVerwaltung.createDefaultData();

        });

        TextField vorname = new TextField();
        vorname.setPromptText("Vorname");
        vorname.setMaxWidth(80);

        TextField name = new TextField();
        name.setPromptText("Name");
        name.setMaxWidth(80);


        menu.getChildren().addAll(create_default_table, vorname, name);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menu);

        Scene scene = new Scene(borderPane, 640, 320);
        stage.setTitle("Busreisen System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        launch();
        startConsole();
    }

    /**
     * Startpunkt des User-Interfaces
     */
    private static void startConsole() {
        String auswahl = select("BUSREISEN VERWALTUNG", "Sind Sie (1) ein Kunde oder (2) ein Mitarbeiter?", new String[]{"1", "2"});
        login = Integer.parseInt(auswahl);
        if (auswahl.equals("1")) {
            kundenVerwaltung();
        } else {
            mitarbeiterVerwaltung();
        }
    }

    /**
     * Startpunkt, wenn 'eingeloggt' als Kunde ( {@code Application.login} = 1 )
     */
    private static void kundenVerwaltung() {
        buchungTaetigen(Integer.parseInt(select(
            "WIE KÖNNEN WIR BEHILFLICH SEIN?",
            "(1) Einfache Buchung\n(2) Paarbuchung",
            new String[]{"1", "2"}
        )));
    }

    /**
     * Startpunkt, um eine Buchung zu tätigen
     * @param buchungsTyp 1 - einfache Buchung | 2 - Paarbuchung
     */
    private static void buchungTaetigen(int buchungsTyp) {
        String auswahl = select(
            (buchungsTyp == 1 ? "EINFACHE BUCHUNG" : "PAARBUCHUNG"),
            "Wählen Sie eine Reise aus:\n" + getBusreisenListe(),
            Reiseverwaltung.getBusreisen().keySet().stream().map(String::valueOf).toArray(String[]::new)
        );

        Busreise busreise = Reiseverwaltung.getBusreisen().get(Integer.parseInt(auswahl));
        if (buchungsTyp == 1) {
            Buchung buchung = buildEinzelbuchung(busreise);
            busreise.setSitzplatzBesetzt(buchung.getSitzplatz(), Boolean.TRUE);

            Reiseverwaltung.reiseBuchen(busreise, buchung);
        } else {
            Paarbuchung paarbuchung = buildPaarbuchung(busreise);
            busreise.setSitzplatzBesetzt(paarbuchung.getSitzplatz(), Boolean.TRUE);
            busreise.setSitzplatzBesetzt(paarbuchung.getPartnerSitzplatz(), Boolean.TRUE);

            Reiseverwaltung.reiseBuchen(busreise, paarbuchung);
        }

        print(Util.Colors.GREEN, "BUCHUNG GETÄTIGT", "Die Buchung wurde erfolgreich getätigt.");
        System.out.println();
        kundenVerwaltung();
    }

    /**
     * Erfragung der Eingabe der persönlichen Daten eines Passagiers
     * @param title auszugebende Überschrift
     * @return aus den eingegebenen Daten zusammengestellte {@code Passagier}
     */
    private static Passagier buildPassagier(String title) {
        String vorname = in(title, "Bitte geben Sie den Vornamen ein");
        String name = in(title, "Bitte geben Sie den Nachnamen ein");

        String strasse = in(title, "Bitte geben Sie die Straße ein");
        String hausnummer = numberIn(title, "Bitte geben Sie die Hausnummer ein");
        String plz = numberIn(title, "Bitte geben Sie die Postleitzahl ein");
        String ort = in(title, "Bitte geben Sie den Ort ein");

        Adresse adresse = new Adresse();
        adresse.setStrasse(strasse);
        adresse.setHausnummer(hausnummer);
        adresse.setPlz(plz);
        adresse.setOrt(ort);
        adresse.setAdressTyp(0);

        Passagier passagier = new Passagier();
        passagier.setName(name);
        passagier.setVorname(vorname);
        passagier.setAdresse(adresse);
        return passagier;
    }

    /**
     * Erfragung der Eingabe aller benötigten Daten, um eine Buchung zu tätigen
     * @param busreise Busreise, zu welcher eine Buchung vorgenommen wird
     * @return aus eingegebenen Daten zusammengestellte {@code Buchung}
     */
    private static Buchung buildEinzelbuchung(Busreise busreise) {
        Buchung buchung = new Buchung();
        buchung.setBusreise(busreise);
        buchung.setPassagier(buildPassagier("PASSAGIER"));

        String sitzPraef = busreise.getBus().getBusTyp().equals(BusTyp.KLEINBUS) ? "1" : select(
            "SITZPLATZ",
            "Möchten Sie (1) einen Sitzplatz auswählen oder (2) eine Präferenz äußern?\n\n" + busreise.getBusBesetzung(),
            new String[]{"1", "2"}
        );

        if (sitzPraef.equals("1")) {
            List<String> freieSitzplaetze = new ArrayList<>();
            for (int i = 0; i < busreise.getSitzplaetze().size(); i++) {
                if (!busreise.getSitzplaetze().get(i)) {
                    freieSitzplaetze.add(String.valueOf(i + 1));
                }
            }

            buchung.setSitzplatz(Integer.parseInt(select(
                "SITZPLATZ AUSWAHL",
                busreise.getBusBesetzung(),
                freieSitzplaetze.toArray(String[]::new)
            )));
        } else {
            buchung.setPraeferenz(Praeferenz.getPraeferenzById(Integer.parseInt(select(
                "SITZPLATZ PRÄFERENZ",
                "Wählen Sie eine Sitzplatz-Präferenz (" + getPraeferenzenListe(getVerfuegbarePraeferenzen(buchung)) + ")\n\n" + busreise.getBusBesetzung(),
                getVerfuegbarePraeferenzen(buchung).stream().map(praef -> String.valueOf(praef.getId())).toArray(String[]::new)
            ))));
            buchung.setSitzplatzByPraeferenz();
        }

        buchung.setInklusiveHeizdecke(select(
            "HEIZDECKE",
            "Möchten Sie eine Heizdecke dazubuchen (0 - Nein, 1 - Ja)?",
            new String[]{"0", "1"}
        ).equals("1"));

        return buchung;
    }

    /**
     *
     * Erfragung der Eingabe aller benötigten Daten, um eine Paarbuchung zu tätigen
     * @param busreise Busreise, zu welcher eine Paarbuchung vorgenommen wird
     * @return aus eingegebenen Daten zusammengestellte {@code Paarbuchung}
     */
    private static Paarbuchung buildPaarbuchung(Busreise busreise) {
        Paarbuchung paarbuchung = new Paarbuchung();
        paarbuchung.setBusreise(busreise);
        paarbuchung.setPassagier(buildPassagier("PASSAGIER"));
        paarbuchung.setPassagierPartner(buildPassagier("PASSAGIER PARTNER"));

        String sitzPraef = busreise.getBus().getBusTyp().equals(BusTyp.KLEINBUS) ? "1" : select(
            "SITZPLATZ",
            "Möchten Sie (1) einen Sitzplatz auswählen oder (2) eine Präferenz äußern?\n\n" + busreise.getBusBesetzung(),
            new String[]{"1", "2"}
        );

        if (sitzPraef.equals("1")) {
            List<String> freieSitzplaetze = new ArrayList<>();
            for (int i = 0; i < busreise.getSitzplaetze().size(); i++) {
                if (!busreise.getSitzplaetze().get(i) && paarbuchung.generatePartnerSitzplatz(i + 1) != -1) {
                    freieSitzplaetze.add(String.valueOf(i));
                }
            }

            paarbuchung.setSitzplatz(Integer.parseInt(select(
                "SITZPLATZ AUSWAHL",
                "Beachten Sie, dass bei der Paarbuchung die Plätze nebeneinander liegen müssen!\n\n" + busreise.getBusBesetzung(),
                freieSitzplaetze.toArray(String[]::new)
            )));
        } else {
            paarbuchung.setPraeferenz(Praeferenz.getPraeferenzById(Integer.parseInt(select(
                "SITZPLATZ PRÄFERENZ",
                "Wählen Sie eine Sitzplatz-Präferenz (" + getPraeferenzenListe(getVerfuegbarePraeferenzen(paarbuchung)) + ")\n\n" + busreise.getBusBesetzung(),
                getVerfuegbarePraeferenzen(paarbuchung).stream().map(praef -> String.valueOf(praef.getId())).toArray(String[]::new)
            ))));
            paarbuchung.setSitzplatzByPraeferenz();
        }

        paarbuchung.setInklusiveHeizdecke(select(
                "HEIZDECKE",
                "Möchten Sie eine Heizdecke dazubuchen (0 - Nein, 1 - Ja)?",
                new String[]{"0", "1"}
        ).equals("1"));

        return paarbuchung;
    }

    /**
     * Startpunkt, wenn 'eingeloggt' als Mitarbeiter ( {@code Application.login} = 2 )
     */
    private static void mitarbeiterVerwaltung() {
        String auswahl = select(
            "UNTERNEHMEN VERWALTEN",
            "(1) Fahrer hinzufügen\n(2) Fahrer entfernen\n(3) Bus hinzufügen\n(4) Bus entfernen\n(5) Reise hinzufügen",
            new String[]{"1", "2", "3", "4", "5"}
        );

        switch (auswahl) {
            case "1" -> fahrerHinzufuegen();
            case "2" -> fahrerEntfernen();
            case "3" -> busHinzufuegen();
            case "4" -> busEntfernen();
            case "5" -> reiseHinzufuegen();
        }
    }

    /**
     * Erfragung der Eingabe von Fahrer-Daten, um einen neuen Fahrer hinzuzufügen
     */
    private static void fahrerHinzufuegen() {
        String name = in("FAHRER HINZUFÜGEN", "Bitte geben Sie den Nachnamen des Fahrers ein");
        String vorname = in("FAHRER HINZUFÜGEN", "Bitte geben Sie den Vornamen des Fahrers ein");

        String fuehrerscheinKlasse = select(
            "FAHRER HINZUFÜGEN",
            "Bitte geben Sie die höchste Führerscheinklasse (" + Fuehrerscheinklasse.getListe() + ") des Fahrers an",
            Arrays.stream(Fuehrerscheinklasse.values()).map(Fuehrerscheinklasse::name).toArray(String[]::new)
        );

        Fahrer fahrer = new Fahrer();
        fahrer.setName(name);
        fahrer.setVorname(vorname);
        fahrer.setHoechsteFuehrerscheinklasse(Fuehrerscheinklasse.valueOf(fuehrerscheinKlasse.toUpperCase()));

        Reiseverwaltung.fahrerHinzufuegen(fahrer);

        print(Util.Colors.GREEN, "FAHRER HINZUGEFÜGT", "Der Fahrer wurde erfolgreich hinzugefügt.");
        System.out.println();
        mitarbeiterVerwaltung();
    }

    /**
     * Erfragung der {@code Fahrer.fahrerNr} einer der ausgelisteten Fahrer, um diesen Fahrer zu entfernen
     */
    private static void fahrerEntfernen() {
        TreeMap<Integer, Fahrer> fahrerMap = Reiseverwaltung.getFahrer();
        String auswahl = select(
            "FAHRER ENTFERNEN",
            "Wählen Sie den Fahrer aus, der entfernt werden soll:\n" + getFahrerListe(fahrerMap.values().stream().toList()),
            Arrays.stream(fahrerMap.keySet().toArray(Integer[]::new))
                  .map(String::valueOf)
                  .toArray(String[]::new)
        );
        Reiseverwaltung.fahrerEntfernen(Reiseverwaltung.getFahrer().get(Integer.parseInt(auswahl)));

        print(Util.Colors.GREEN, "FAHRER ENTFERNT", "Der Fahrer wurde erfolgreich entfernt.");
        System.out.println();
        mitarbeiterVerwaltung();
    }

    /**
     * Erfragung der Eingabe von Bus-Daten, um einen neuen Bus hinzuzufügen
     */
    private static void busHinzufuegen() {
        String title = "BUS HINZUFÜGEN";

        String kennzeichen = in(title, "Bitte geben Sie das Kennzeichen des Busses ein");
        while (Reiseverwaltung.getBusse().containsKey(kennzeichen)) {
            kennzeichen = errorIn(title + " - KENNZEICHEN VERGEBEN", "Das Kennzeichen ist schon vergeben! Bitte geben Sie ein anderes Kennzeichen ein");
        }

        String busTyp = select(
            title,
            "Bitte geben Sie den BusTyp (" + BusTyp.getListe() + ") an",
            Arrays.stream(BusTyp.values()).map(bt -> String.valueOf(bt.getId())).toArray(String[]::new)
        );

        String zulassung = dateIn(title, "Bitte geben Sie das Datum der Zulassung des Busses im Format YYYY-MM-DD ein");
        String tuevTermin = dateIn(title, "Bitte geben Sie das Datum des nächsten TÜV-Termins des Busses im Format YYYY-MM-DD ein");
        String gefahreneKilometer = decimalIn(title, "Bitte geben Sie den momentanen Kilometerstand des Busses an");
        String kostenProKilometer = decimalIn(title, "Bitte geben Sie die Kosten pro Kilometer des Busses an");

        Bus bus = new Bus();
        bus.setKennzeichen(kennzeichen);
        bus.setBusTyp(BusTyp.getBusTypById(Integer.parseInt(busTyp)));
        bus.setZulassung(Date.valueOf(zulassung));
        bus.setTuevTermin(Date.valueOf(tuevTermin));
        bus.setGefahreneKilometer(Double.parseDouble(gefahreneKilometer.replaceAll(",", ".")));
        bus.setKostenProKilometer(Double.parseDouble(kostenProKilometer.replaceAll(",", ".")));

        Reiseverwaltung.busHinzufuegen(bus);

        print(Util.Colors.GREEN, "BUS HINZUGEFÜGT", "Der Bus wurde erfolgreich hinzugefügt.");
        System.out.println();
        mitarbeiterVerwaltung();
    }

    /**
     * Erfragung des {@code Bus.kennzeichen} eines der ausgelisteten Busse, um diesen Bus zu entfernen
     */
    private static void busEntfernen() {
        String auswahl = select(
                "BUS ENTFERNEN",
                "Wählen Sie den Bus aus, der entfernt werden soll:\n" + getBusListe(),
                Reiseverwaltung.getBusse().keySet().toArray(String[]::new)
        );
        Reiseverwaltung.busEntfernen(Reiseverwaltung.getBusse().get(auswahl));

        print(Util.Colors.GREEN, "BUS ENTFERNT", "Der Bus wurde erfolgreich entfernt.");
        System.out.println();
        mitarbeiterVerwaltung();
    }

    /**
     * Erfragung der Eingabe von Busreise-Daten, um eine neue Busreise hinzuzufügen
     */
    private static void reiseHinzufuegen() {
        String title = "REISE HINZUFÜGEN";

        Set<Integer> set = new HashSet<>(Reiseverwaltung.getReiseAdressen().keySet());
        set.add(0);

        String auswahlAdresse = select(
            title,
            "Wollen Sie (0) eine neue Adresse hinzufügen oder eine der folgenden wählen:\n" + getReiseAdressenListe(),
            set.stream().map(String::valueOf).toArray(String[]::new)
        );

        Adresse adresse = new Adresse();
        if (auswahlAdresse.equals("0")) {
            adresse.setStrasse(in(title, "Bitte geben Sie die Straße des Zielortes ein"));
            adresse.setHausnummer(numberIn(title, "Bitte geben Sie die Hausnummer des Zielortes ein"));
            adresse.setPlz(numberIn(title, "Bitte geben Sie die Postleitzahl des Zielortes ein"));
            adresse.setOrt(in(title, "Bitte geben Sie den Ort des Zielortes ein"));
            adresse.setAdressTyp(1);
            adresse.setAdresseId(Reiseverwaltung.adresseHinzufuegen(adresse));
        } else {
            adresse = Reiseverwaltung.getReiseAdressen().get(Integer.parseInt(auswahlAdresse));
        }

        String fahrtbeginn = timestampIn(title, "Bitte geben Sie das Datum und die Uhrzeit des Fahrtbeginn im Format YYYY-MM-DD HH:MM ein");
        String fahrtende = timestampIn(title, "Bitte geben Sie das Datum und die Uhrzeit des Fahrtende im Format YYYY-MM-DD HH:MM ein");
        String kostenProPerson = decimalIn(title, "Bitte geben Sie die Kosten pro Person an");

        String auswahlBus = select(
            title,
            "Wählen Sie einen Bus für die Reise aus:\n" + getBusListe(),
            Reiseverwaltung.getBusse().keySet().toArray(String[]::new)
        );
        Bus bus = Reiseverwaltung.getBusse().get(auswahlBus);

        TreeMap<Integer, Fahrer> fahrerMap = Reiseverwaltung.getFahrer();
        if (bus.getBusTyp().equals(BusTyp.REISEBUS)) {
            fahrerMap.entrySet().removeIf(entry -> entry.getValue().getHoechsteFuehrerscheinklasse().equals(Fuehrerscheinklasse.B));
        }

        String auswahlFahrer = select(
                title,
                "Wählen Sie einen Fahrer für die Reise aus:\n" + getFahrerListe(fahrerMap.values().stream().toList()),
                fahrerMap.keySet().stream().map(String::valueOf).toArray(String[]::new)
        );
        Fahrer fahrer = Reiseverwaltung.getFahrer().get(Integer.parseInt(auswahlFahrer));

        Busreise busreise = new Busreise();
        busreise.setFahrtbeginn(Timestamp.valueOf(fahrtbeginn));
        busreise.setFahrtende(Timestamp.valueOf(fahrtende));
        busreise.setFahrer(fahrer);
        busreise.setBus(bus);
        busreise.setZielort(adresse);
        busreise.setKostenProPerson(Double.valueOf(kostenProPerson.replaceAll(",", ".")));

        boolean[] sitzplaetze = new boolean[bus.getBusTyp().getAnzahlSitzplaetze()];
        if (bus.getBusTyp().equals(BusTyp.KLEINBUS)) {
            sitzplaetze[0] = true;
            sitzplaetze[1] = true;
        } else {
            sitzplaetze[0] = true;
            sitzplaetze[2] = true;
            sitzplaetze[3] = true;
        }
        busreise.setSitzplaetze(sitzplaetze);

        Reiseverwaltung.reiseHinzufuegen(busreise);

        print(Util.Colors.GREEN, "REISE HINZUGEFÜGT", "Die Busreise wurde erfolgreich hinzugefügt.");
        System.out.println();
        mitarbeiterVerwaltung();
    }

    private static void print(String title, String description) {
        print(Util.Colors.RESET, title, description);
    }

    /**
     * Geniert eine Eingabe-Erfragung oder Informations-Ausgabe im Programm-Stil und gibt diese aus
     * @param color ANSI-Color-Code, welcher für die Überschrift verwendet wird
     * @param title auszugebende Überschrift
     * @param description auszugebender Text
     */
    private static void print(String color, String title, String description) {
        String header = "||" + "=".repeat(LEN - 30) + "||======||========||======||\n";
        header += "|| " + color + title + " ".repeat(LEN - 31 - title.length()) + Util.Colors.RESET + "|| HOME || LOGOUT || EXIT ||\n";
        header += "||" + "-".repeat(LEN - 30) + "||======||========||======||\n";

        StringBuilder box = new StringBuilder(header);
        for (String d : description.split("\n")) {
            int colorCodesLength = Util.Colors.getColorCodesCount(d) * 7;
            int spaceLength = LEN - 5 - d.length() + colorCodesLength;
            box.append("|| ").append(d).append(" ".repeat(spaceLength)).append("||\n");
        }
        box.append("||").append("=".repeat(LEN - 4)).append("||");

        System.out.println(box);
    }

    /**
     * Überprüft übergebenen {@code input}, auf Navigations-Anweisungen und führt diese ggf. aus
     * @param input zu überprüfende Eingabe
     */
    private static void checkForNavigationIn(String input) {
        if (input.equals("exit")) {
            System.exit(0);
        } else if (input.equals("home")) {
            if (login == 1) {
                kundenVerwaltung();
            } else {
                mitarbeiterVerwaltung();
            }
        } else if (input.equals("logout")) {
            startConsole();
        }
    }

    /**
     * Generiert eine Eingabe-Erfragung und gibt die Eingabe zurück
     * @param title auszugebende Überschrift
     * @param description auszugebender Text
     * @return erfragte Eingabe
     */
    private static String in(String title, String description) {
        print(title, description);
        String input = SCAN.nextLine();
        checkForNavigationIn(input);
        return input;
    }

    /**
     * Generiert eine Eingabe-Erfragung, nach fehlerhafter Eingabe und gibt die neue Eingabe zurück
     * @param title auszugebende Überschrift mit Fehlermeldung
     * @param description auszugebender Text
     * @return erfragte Eingabe
     */
    private static String errorIn(String title, String description) {
        print(Util.Colors.RED, title, description);
        String input = SCAN.nextLine();
        checkForNavigationIn(input);
        return input;
    }

    /**
     * Generiert eine Eingabe-Erfragung, wiederholt diese, solange die Eingabe nicht ausschließlich aus Zahlen besteht und gibt die validierte Eingabe zurück
     * @param title auszugebende Überschrift
     * @param description auszugebender Text
     * @return erfragte, validierte Eingabe
     */
    private static String numberIn(String title, String description) {
        print(title, description);

        String input = "";
        while (SCAN.hasNextLine()) {
            input = SCAN.nextLine();
            checkForNavigationIn(input);
            if (input.matches("[0-9]+")) {
                break;
            } else {
                print(Util.Colors.RED, title + " - FALSCHE EINGABE", description);
            }
        }
        return input;
    }

    /**
     * Generiert eine Eingabe-Erfragung, wiederholt diese, solange die Eingabe nicht ausschließlich aus Zahlen und maximal einem {@code .} <b>ODER</b> einem {@code ,} besteht und gibt die validierte Eingabe zurück
     * @param title auszugebende Überschrift
     * @param description auszugebender Text
     * @return erfragte, validierte Eingabe
     */
    private static String decimalIn(String title, String description) {
        print(title, description);

        String input = "";
        while (SCAN.hasNextLine()) {
            input = SCAN.nextLine();
            checkForNavigationIn(input);
            if (input.matches("\\d+(?:[,.]\\d+)*")) {
                break;
            } else {
                print(Util.Colors.RED, title + " - FALSCHE EINGABE", description);
            }
        }
        return input;
    }

    /**
     * Generiert eine Eingabe-Erfragung, wiederholt diese, solange die Eingabe nicht dem geforderten {@code Date}-Format entspricht und gibt die validierte Eingabe zurück
     * @param title auszugebende Überschrift
     * @param description auszugebender Text
     * @return erfragte, validierte Eingabe
     */
    private static String dateIn(String title, String description) {
        print(title, description);

        String input = "";
        while (SCAN.hasNextLine()) {
            input = SCAN.nextLine();
            checkForNavigationIn(input);
            try {
                Date.valueOf(input);
                break;
            } catch (Exception e) {
                print(Util.Colors.RED, title + " - FALSCHE EINGABE", description);
            }
        }
        return input;
    }

    /**
     * Generiert eine Eingabe-Erfragung, wiederholt diese, solange die Eingabe nicht dem geforderten {@code Timestamp}-Format entspricht und gibt die validierte Eingabe zurück
     * @param title auszugebende Überschrift
     * @param description auszugebender Text
     * @return erfragte, validierte Eingabe
     */
    private static String timestampIn(String title, String description) {
        print(title, description);

        String input = "";
        while (SCAN.hasNextLine()) {
            input = SCAN.nextLine();
            checkForNavigationIn(input);
            try {
                Timestamp.valueOf(input + ":00");
                break;
            } catch (Exception e) {
                print(Util.Colors.RED, title + " - FALSCHE EINGABE", description);
            }
        }
        return input + ":00";
    }

    /**
     * Generiert eine Eingabe-Erfragung, wiederholt diese, solange die Eingabe nicht in {@code validInputs} auftaucht und gibt die validierte Eingabe zurück
     * @param title auszugebende Überschrift
     * @param description auszugebender Text
     * @param validInputs String-Array mit erlaubten Eingaben
     * @return erfragte, validierte Eingabe
     */
    private static String select(String title, String description, String[] validInputs) {
        print(title, description);

        String input = "";
        while (SCAN.hasNextLine()) {
            input = SCAN.nextLine();
            checkForNavigationIn(input);
            if (Arrays.asList(validInputs).contains(input)) {
                break;
            } else {
                print(Util.Colors.RED, title + " - FALSCHE EINGABE", description);
            }
        }
        return input;
    }

    /**
     * @param buchung zu überprüfende {@code Buchung}
     * @return Liste aller zur übergebenen Buchung verfügbaren Präferenzen
     */
    private static List<Praeferenz> getVerfuegbarePraeferenzen(Buchung buchung) {
        boolean paarbuchung = buchung instanceof Paarbuchung;
        List<Praeferenz> praeferenzen = new ArrayList<>();
        praeferenzen.add(Praeferenz.KEINE);

        List<Boolean> sitzplaetze = buchung.getBusreise().getSitzplaetze();
        for (int i = 0; i <= 52; i += 4) {
            boolean pbFenster = !paarbuchung || ((Paarbuchung) buchung).generatePartnerSitzplatz(i + 1) != -1;
            if (!sitzplaetze.get(i) && pbFenster) {
                praeferenzen.add(Praeferenz.FENSTERPLATZ);
                break;
            }
            if (!sitzplaetze.get(i + 3) && (i + 3) != 27 && pbFenster) {
                praeferenzen.add(Praeferenz.FENSTERPLATZ);
                break;
            }
        }
        for (int i = 1; i <= 53; i += 4) {
            boolean pbGang = !paarbuchung || ((Paarbuchung) buchung).generatePartnerSitzplatz(i + 1) != -1;
            if (!sitzplaetze.get(i) && pbGang) {
                praeferenzen.add(Praeferenz.GANGPLATZ);
                break;
            }
            if (!sitzplaetze.get(i + 1) && (i + 1) != 26 && pbGang) {
                praeferenzen.add(Praeferenz.GANGPLATZ);
                break;
            }
        }
        return praeferenzen;
    }

    /**
     * @param praeferenzen Liste der auszugebenen Präferenzen
     * @return Auflistung der übergebenen Präferenzen
     */
    private static String getPraeferenzenListe(List<Praeferenz> praeferenzen) {
        StringBuilder praeferenzenSB = new StringBuilder();
        for (Praeferenz praeferenz : praeferenzen) {
            praeferenzenSB.append(praeferenz.getId()).append(" - ").append(praeferenz).append(", ");
        }
        return praeferenzenSB.substring(0, praeferenzenSB.length() - 2);
    }

    /**
     * @return Auflistung aller in der Datenbank gespeicherten Busreisen
     */
    private static String getBusreisenListe() {
        StringBuilder busreisenSB = new StringBuilder();
        for (Busreise busreise : Reiseverwaltung.getBusreisen().values()) {
            busreisenSB.append("\n(")
                    .append(busreise.getReiseNr())
                    .append(") ")
                    .append(DTF.format(busreise.getFahrtbeginn().toLocalDateTime()))
                    .append(" - ")
                    .append(busreise.getZielort().getStrasse())
                    .append(" ")
                    .append(busreise.getZielort().getHausnummer())
                    .append(", ")
                    .append(busreise.getZielort().getPlz())
                    .append(" ")
                    .append(busreise.getZielort().getOrt())
                    .append(" (")
                    .append(DF.format(busreise.getKostenProPerson()))
                    .append(" €/Person)");
        }
        return busreisenSB.toString();
    }

    /**
     * @param fahrerListe Liste der auszugebenen Fahrer
     * @return Auflistung der übergebenen Fahrer
     */
    private static String getFahrerListe(List<Fahrer> fahrerListe) {
        StringBuilder fahrerSB = new StringBuilder();
        for (Fahrer fahrer : fahrerListe) {
            fahrerSB.append("\n(")
                    .append(fahrer.getFahrerNr())
                    .append(") ")
                    .append(fahrer.getName())
                    .append(", ")
                    .append(fahrer.getVorname())
                    .append(" - Führerscheinklasse ")
                    .append(fahrer.getHoechsteFuehrerscheinklasse().name());
        }
        return fahrerSB.toString();
    }

    /**
     * @return Auflistung aller in der Datenbank gespeicherten Busse
     */
    private static String getBusListe() {
        StringBuilder busseSB = new StringBuilder();
        for (Bus bus : Reiseverwaltung.getBusse().values()) {
            busseSB.append("\n(")
                    .append(bus.getKennzeichen())
                    .append(") ")
                    .append(bus.getBusTyp().name())
                    .append(", Zulassung: ")
                    .append(bus.getZulassung())
                    .append(" - Kilometerstand: ")
                    .append(NF.format(bus.getGefahreneKilometer()));
        }
        return busseSB.toString();
    }

    /**
     * @return Auflistung aller in der Datenbank gespeicherten Reisezielorten ( {@code Adresse.adressTyp} = 1 )
     */
    private static String getReiseAdressenListe() {
        StringBuilder reiseAdressenSB = new StringBuilder();
        for (Adresse adresse : Reiseverwaltung.getReiseAdressen().values()) {
            reiseAdressenSB.append("\n(")
                    .append(adresse.getAdresseId())
                    .append(") ")
                    .append(adresse.getStrasse())
                    .append(" ")
                    .append(adresse.getHausnummer())
                    .append(", ")
                    .append(adresse.getPlz())
                    .append(" ")
                    .append(adresse.getOrt());
        }
        return reiseAdressenSB.toString();
    }
}