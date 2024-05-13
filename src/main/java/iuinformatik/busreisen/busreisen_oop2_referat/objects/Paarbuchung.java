package iuinformatik.busreisen.busreisen_oop2_referat.objects;

public class Paarbuchung extends Buchung {
    private Passagier passagierPartner;

    public Passagier getPassagierPartner() {
        return passagierPartner;
    }

    public void setPassagierPartner(Passagier newPassagierPartner) {
        this.passagierPartner = newPassagierPartner;
    }
}
