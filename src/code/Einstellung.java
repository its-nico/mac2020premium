package code;

import java.io.Serializable;
import java.util.ArrayList;

public class Einstellung implements Serializable { //Die Serialisierung ermöglicht das Speichern der Einstellungen innerhalb der Liste

    private String einstellungTyp;
    private boolean einstellungWert;

    private ArrayList<Einstellung> einstellungenList = new ArrayList<>();

    //Konstruktor der Klasse Datensatz, ein neues Objekt wird erstellt, dabei werden alle Attribute als Parameter übergeben
    public Einstellung(String pEinstellungTyp, boolean pEinstellungWert) {
        einstellungTyp = pEinstellungTyp;
        einstellungWert = pEinstellungWert;
    }

    //getter-Methoden

    public String getEinstellungTyp() {
        return einstellungTyp;
    }

    public boolean getEinstellungWert() {
        return einstellungWert;
    }

    public void setEinstellungWert(boolean pEinstellungWert) {
        einstellungWert = pEinstellungWert;
    }
}

