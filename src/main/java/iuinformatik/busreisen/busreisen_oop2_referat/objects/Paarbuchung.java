package iuinformatik.busreisen.busreisen_oop2_referat.objects;

import iuinformatik.busreisen.busreisen_oop2_referat.enums.BusTyp;
import iuinformatik.busreisen.busreisen_oop2_referat.enums.Praeferenz;

import java.util.List;

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

    public void setPartnerSitzplatz(int partnerSitzplatz) {
        this.partnerSitzplatz = partnerSitzplatz;
    }

    /**
     * Zuweisung des Sitzplatzes für Passagier und Passagier Partner.
     * @param sitzplatz Sitzplatz-Nr für {@code Buchung.passagier}
     */
    @Override
    public void setSitzplatz(int sitzplatz) {
        super.setSitzplatz(sitzplatz);
        this.partnerSitzplatz = generatePartnerSitzplatz(sitzplatz);
    }

    @Override
    public void setSitzplatzByPraeferenz() {
        List<Boolean> sitzplaetze = this.getBusreise().getSitzplaetze();
        if (this.getPraeferenz().equals(Praeferenz.FENSTERPLATZ)) {
            for (int i = 0; i <= 52; i += 4) {
                if (!sitzplaetze.get(i) && this.generatePartnerSitzplatz(i + 1) != -1) {
                    this.setSitzplatz(i + 1);
                    this.partnerSitzplatz = this.generatePartnerSitzplatz(i + 1);
                    return;
                }
                if (!sitzplaetze.get(i + 3) && this.generatePartnerSitzplatz(i + 4) != -1 && (i + 3 != 27)) {
                    this.setSitzplatz(i + 4);
                    this.partnerSitzplatz = this.generatePartnerSitzplatz(i + 4);
                    return;
                }
            }
        } else if (this.getPraeferenz().equals(Praeferenz.GANGPLATZ)) {
            for (int i = 1; i <= 53; i += 4) {
                if (!sitzplaetze.get(i) && this.generatePartnerSitzplatz(i + 1) != -1) {
                    this.setSitzplatz(i + 1);
                    this.partnerSitzplatz = this.generatePartnerSitzplatz(i + 1);
                    return;
                }
                if (!sitzplaetze.get(i + 1) && (i + 1) != 26 && this.generatePartnerSitzplatz(i + 2) != -1) {
                    this.setSitzplatz(i + 2);
                    this.partnerSitzplatz = this.generatePartnerSitzplatz(i + 2);
                    return;
                }
            }
        } else {
            for (int i = 1; i <= sitzplaetze.size(); i++) {
                if (!sitzplaetze.get(i) && this.generatePartnerSitzplatz(i + 1) != -1) {
                    this.setSitzplatz(i + 1);
                    this.partnerSitzplatz = this.generatePartnerSitzplatz(i + 1);
                    return;
                }
            }
        }
    }

    /**
     * Automatische Ermittlung des Partner-Sitzplatzes.
     * @param sitzplatz Sitzplatz-Nr für {@code Buchung.passagier}
     * @return Sitzplatz-Nr für {@code Paarbuchung.passagierPartner} oder -1, wenn keiner der benachbarten Sitzplätze frei ist.
     */
    public int generatePartnerSitzplatz(int sitzplatz) {
        Busreise busreise = getBusreise();
        int partnerSitzplatz = -1;

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
            if (sitzplatz % 2 == 0 && sitzplatz >= 6) {
                if (!busreise.getSitzplaetze().get(sitzplatz - 2)) {
                    partnerSitzplatz = sitzplatz - 1;
                }

                if (partnerSitzplatz == -1 && (sitzplatz == 54 || sitzplatz == 56) && !busreise.getSitzplaetze().get(sitzplatz)) {
                    partnerSitzplatz = sitzplatz + 1;
                }

            } else if (sitzplatz % 2 != 0 && sitzplatz >= 5) {
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
        return partnerSitzplatz;
    }
}