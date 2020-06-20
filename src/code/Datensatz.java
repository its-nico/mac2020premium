package code;

import java.io.Serializable;

public class Datensatz implements Serializable {

    private String kursstufe;
    private String nachname;
    private String vorname;
    private String mac;
    private String grund;

    /* >Konstruktor der Klasse Datensatz, ein neues Objekt wird erstellt*/
    public Datensatz(String pKursstufe, String pNachname, String pVorname, String pMac, String pGrund) {
        kursstufe = pKursstufe;
        nachname = pNachname;
        vorname = pVorname;
        grund = pGrund;
        mac = pMac;
    }

    /* getter und setter Methoden */

    public String getKursstufe() {
        return kursstufe;
    }

    public void setKursstufe(String kursstufe) {
        this.kursstufe = kursstufe;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
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
