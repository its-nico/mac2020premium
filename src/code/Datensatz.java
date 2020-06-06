package code;

public class Datensatz {
    private String kursstufe;
    private String vorname;
    private String nachname;
    private String grund;

    public Datensatz(String pKursstufe, String pVorname, String pNachname, String pMac, String pGrund) {
        kursstufe = pKursstufe;
        vorname = pVorname;
        nachname = pNachname;
        grund = pGrund;
    }

    public String getKursstufe() {
        return kursstufe;
    }

    public void setKursstufe(String kursstufe) {
        this.kursstufe = kursstufe;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGrund() {
        return grund;
    }

    public void setGrund(String grund) {
        this.grund = grund;
    }

}
