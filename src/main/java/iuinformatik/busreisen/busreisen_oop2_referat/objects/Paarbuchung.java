package iuinformatik.busreisen.busreisen_oop2_referat.objects;

import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;

public class Paarbuchung extends Buchung {

    private int partnerSitzplatz = -1;
    private Passagier passagierPartner;

    public int getPartnerSitzplatz() {
        return partnerSitzplatz;
    }

    /**
     * Zuweisung des Sitzplatzes und automatische Ermittlung und Zuweisung des Partner-Sitzplatzes.
     * @param sitzplatz Sitzplatz-Nr für {@code Buchung.passagier}
     */
    @Override
    public void setSitzplatz(int sitzplatz) {
        super.setSitzplatz(sitzplatz);

        Bus bus = getBusreise().getBus();
        if (bus.getBusTyp() == BusTyp.Kleinbus) {
            if (sitzplatz == 4 || sitzplatz == 7) {
                if (!bus.getSitzplaetze().get(sitzplatz)) {
                    partnerSitzplatz = sitzplatz + 1;
                }

            } else if (sitzplatz == 5 || sitzplatz == 8) {
                if (!bus.getSitzplaetze().get(sitzplatz - 2)) {
                    partnerSitzplatz = sitzplatz - 1;
                } else if (!bus.getSitzplaetze().get(sitzplatz)) {
                    partnerSitzplatz = sitzplatz + 1;
                }

            } else if (sitzplatz == 6 || sitzplatz == 9) {
                if (!bus.getSitzplaetze().get(sitzplatz - 2)) {
                    partnerSitzplatz = sitzplatz - 1;
                }
            }

        } else if (bus.getBusTyp() == BusTyp.Reisebus) {
            if (sitzplatz % 2 == 0 && sitzplatz > 5) {
                if (!bus.getSitzplaetze().get(sitzplatz - 2)) {
                    partnerSitzplatz = sitzplatz - 1;
                }

                if (partnerSitzplatz == -1 && (sitzplatz == 54 || sitzplatz == 56) && !bus.getSitzplaetze().get(sitzplatz)) {
                    partnerSitzplatz = sitzplatz + 1;
                }

            } else if (sitzplatz % 2 != 0 && sitzplatz > 5) {
                if (sitzplatz == 57) {
                    if (!bus.getSitzplaetze().get(sitzplatz - 2)) {
                        partnerSitzplatz = sitzplatz - 1;
                    }
                } else {
                    if (!bus.getSitzplaetze().get(sitzplatz)) {
                        partnerSitzplatz = sitzplatz + 1;
                    }

                    if (partnerSitzplatz == -1 && (sitzplatz == 55) && !bus.getSitzplaetze().get(sitzplatz - 2)) {
                        partnerSitzplatz = sitzplatz - 1;
                    }
                }
            }
        }

        if (partnerSitzplatz == -1) {
            // TODO: kein möglichen benachbarten Sitzplatz gefunden, es muss ein neues Sitzplatz gewählt werden
        }
    }

    public Passagier getPassagierPartner() {
        return passagierPartner;
    }

    public void setPassagierPartner(Passagier passagierPartner) {
        this.passagierPartner = passagierPartner;
    }
}