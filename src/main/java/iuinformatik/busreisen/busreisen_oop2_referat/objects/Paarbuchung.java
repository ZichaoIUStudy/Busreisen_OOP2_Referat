package iuinformatik.busreisen.busreisen_oop2_referat.objects;

import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;

public class Paarbuchung extends Buchung {

    private Passagier passagierPartner;
    private int partnerSitzplatz = -1;

    public Passagier getPassagierPartner() {
        return passagierPartner;
    }

    public void setPassagierPartner(Passagier passagierPartner) {
        this.passagierPartner = passagierPartner;
    }

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

        Busreise busreise = getBusreise();
        if (busreise.getBus().getBusTyp() == BusTyp.KLEINBUS) {
            if (sitzplatz == 4 || sitzplatz == 7) {
                if (!busreise.getSitzplaetze().get(sitzplatz)) {
                    partnerSitzplatz = sitzplatz + 1;
                }

            } else if (sitzplatz == 5 || sitzplatz == 8) {
                if (!busreise.getSitzplaetze().get(sitzplatz - 2)) {
                    partnerSitzplatz = sitzplatz - 1;
                } else if (!busreise.getSitzplaetze().get(sitzplatz)) {
                    partnerSitzplatz = sitzplatz + 1;
                }

            } else if (sitzplatz == 6 || sitzplatz == 9) {
                if (!busreise.getSitzplaetze().get(sitzplatz - 2)) {
                    partnerSitzplatz = sitzplatz - 1;
                }
            }

        } else if (busreise.getBus().getBusTyp() == BusTyp.REISEBUS) {
            if (sitzplatz % 2 == 0 && sitzplatz > 5) {
                if (!busreise.getSitzplaetze().get(sitzplatz - 2)) {
                    partnerSitzplatz = sitzplatz - 1;
                }

                if (partnerSitzplatz == -1 && (sitzplatz == 54 || sitzplatz == 56) && !busreise.getSitzplaetze().get(sitzplatz)) {
                    partnerSitzplatz = sitzplatz + 1;
                }

            } else if (sitzplatz % 2 != 0 && sitzplatz > 5) {
                if (sitzplatz == 57) {
                    if (!busreise.getSitzplaetze().get(sitzplatz - 2)) {
                        partnerSitzplatz = sitzplatz - 1;
                    }
                } else {
                    if (!busreise.getSitzplaetze().get(sitzplatz)) {
                        partnerSitzplatz = sitzplatz + 1;
                    }

                    if (partnerSitzplatz == -1 && (sitzplatz == 55) && !busreise.getSitzplaetze().get(sitzplatz - 2)) {
                        partnerSitzplatz = sitzplatz - 1;
                    }
                }
            }
        }

        if (partnerSitzplatz == -1) {
            // TODO: kein möglichen benachbarten Sitzplatz gefunden, es muss ein neues Sitzplatz gewählt werden
        }
    }
}