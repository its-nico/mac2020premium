package code;

import java.io.Serializable;

public class Datensatz implements Serializable {

    private final String kursstufe;
    private final String nachname;
    private final String vorname;
    private String mac;
    private final String grund;

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

    public String getNachname() {
        return nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public String getMac() {
        return mac;
    }

    public String getGrund() {
        return grund;
    }
}
