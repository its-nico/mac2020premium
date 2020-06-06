package code;

public class Datensatz {
    private String vorname;
    private String nachname;
    private String kursstufe;
    private String mac;
    private String grund;

    public Datensatz(String pVorname, String pNachname, String pKursstufe, String pMac, String pGrund) {
        kursstufe = pKursstufe;
        vorname = pVorname;
        nachname = pNachname;
        mac = pMac;
        grund = pGrund;
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

    public String getKursstufe() {
        return kursstufe;
    }

    public void setKursstufe(String kursstufe) {
        this.kursstufe = kursstufe;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }


    public String getGrund() {
        return grund;
    }

    public void setGrund(String grund) {
        this.grund = grund;
    }

}
