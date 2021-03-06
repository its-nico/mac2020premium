package code;

import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import java.sql.SQLException;

public class Manager {

    //ArrayList-Objekte werden erstellt
    private static ArrayList<Datensatz> LIST_1 = new ArrayList<>(); //Neue generische Liste des Datentpys 'Datensatz' wird erstellt
    private static ArrayList<Einstellung> LIST_2 = new ArrayList<>(); //Neue generische Liste des Datentpys 'Einstellung' wird erstellt

    //Referenzen auf fremde Klassen
    private static final Export export = new Export();
    private static final Korrektur korrektur = new Korrektur();
    private static final schnellerImport schnellerImport = new schnellerImport();
    private static final Dialogfenster dialogfenster = new Dialogfenster();
    private static final Datenbank datenbank = new Datenbank();

    private static Datensatz datensatz;
    private static Einstellung einstellung;

    private static Speichern speichern;

    //Methode exportiereMac() aus Export wird aufgerufen. Die Liste wird als Parameter übergeben
    public static void exportiereMac(){
        export.exportiereMac(LIST_1);
    }

    //Methode exportiereDatensaetze() aus Export wird aufgerufen. Die Liste wird als Parameter übergeben
    public static void exportiereDatensaetze() {
        export.exportiereDatensaetze(LIST_1);
    }

    //Methode laden() aus Speichern wird aufgrufen. So lädt das Programm die zuvor gespeicherte Liste (Serialisierung)
    public static void laden(){
        LIST_1 = speichern.laden();
    }

    //Die Liste wird hier gespeichert (Serialisierung). Hierfür wir die Methode abspeichern() aus Speichern aufgerufen
    public static void speichern(){
        speichern.abspeichern(LIST_1);
    }

    //Methode ladenEinstellungen() aus Speichern wird aufgrufen. So lädt das Programm die Einstellungen (Serialisierung)
    public static void ladenEinstellungen(){
        LIST_2 = speichern.ladenEinstellungen();
    }

    //Die Einstellungen werden hier gespeichert (Serialisierung). Hierfür wir die Methode abspeichernEinstellungen() aus Speichern aufgerufen
    public static void speichernEinstellungen(){
        speichern.abspeichernEinstellungen(LIST_2);
    }

    //Import wir hier ausgeführt. Als Parameter muss ein Pfad übergeben werden, dieser wird in der Klasse ManuelleEingabe durch OeffnenDialogClass bestimmt
    public static void schnellerImport (String pPfad){
        if (pPfad != null) {
            boolean bereitsImportiert = schnellerImport.bereitsImportiert(pPfad); //true, wenn Dateipfad bereits Dateipfad einer der letzten Import-Dateien war
            if (!bereitsImportiert) { //wurde die Datei noch nicht importiert, werden diese Methoden ausgeführt
                schnellerImport.merkeDateipfad(pPfad); //der aktuelle Dateipfad wird gespeichert, sodass später eine Warnung bei einem möglichen doppelten Import ausgegeben werden kann
                schnellerImport.schnellerImport(pPfad, LIST_1); //die Datensätze werden mit der MEthode schnellerImport() importiert
            } else {
                dialogfenster.bereitsImportiertDialog(pPfad, LIST_1); //wurde die Datei möglicherweise bereits importiert, wird eine Warnung ausgegeben
            }
        }
    }

    //Methode wird für die manuelle Eingabe genutzt
    public static String ergaenze(String pKursstufe, String pNachname, String pVorname, String pMac, String pGrund) {
        korrektur.logErstellen(pMac); //Anfang des Logs wird erstellt
        if (korrektur.istIP(pMac)){ //handelt es sich um eine IP-Adresse?
            return ("<html>Der Datensatz zur Adresse " + pMac + " wurde nicht übernommen, da es sich um eine IP-Adresse handelt.<br>Weitere Informationen finden Sie im Korrektur-Verzeichnis.</html>");

        } else { //Methoden werden ausgeführt, falls es sich nicht um eine IP-Adresse handelt
            String pMac2 = korrektur.autoKorrektur(pMac); //die Adresse wird autokorrigiert
            if (korrektur.format(pMac2)) { //Überprüfung des für MAC-Adressen erforderlichen Formats
                datensatz = new Datensatz(pKursstufe, pNachname, pVorname, pMac2, pGrund); //neuer Datensatz mit übergebenen Parametern wird erzeugt
                LIST_1.add(datensatz); //Datensatz wird an die Liste ergänzt

                return ("<html>Der Datensatz zur MAC-Adresse " + pMac2 + " wurde erfolgreich hinzugefügt.<br>Weitere Informationen finden Sie im Korrektur-Verzeichnis.</html>"); //Rückgabe wird für das Text-Feld in der GUI-genutzt
            }
            else {
                return ("<html>Die Adresse befindet sich nicht im für MAC-Adressen erforderlichen Format (xx:xx:xx:xx:xx:xx) oder enthält unerlaubte Zeichen. <br> Der Datensatz konnte daher nicht übernommen werden.<br>Weitere Informationen finden Sie im Korrektur-Verzeichnis.</html>"); //Rückgabe wird für das Text-Feld in der GUI-genutzt
            }
        }
    }

    //Die Methode löscht die Liste, indem sie sie mit einer leeren Liste überschreibt
    public static void listeLoeschen() {
        LIST_1 = new ArrayList<>();
        File file = new File("./Importpfade.txt");
        if (file.exists()) {
            file.delete(); //Außerdem wird die Datei, in der die letzten Importe gepseichert werden, auch gelöscht
        }
    }

    //Die Liste wird in die Datenbank durch die Methode datenbankErgaenzen() aus Datenbank aufgenommen
    public static  void datenbankErgaenzen() throws SQLException {
        datenbank.datenbankErgaenzen(LIST_1);
    }

    //Die aktuelle Liste wird zurückgegeben
    public static  ArrayList getList1()  {
        return LIST_1;
    }

    //Die Liste zum Abspeichern der Einstellungen wird zurückgegeben
    public static ArrayList getList2 ()  {
        return LIST_2;
    }

    //Die Einstellungen werden mit Hilfe der Liste geändert
    public static void aendereEinstellung(String pEinstellungTyp, boolean pEinstellungWert) {
        Einstellung einstellung;
        int len = LIST_2.size();
        if (len == 0) { //Wenn serialisierte Listen-Datei nicht vorhanden war, sondern neu erstellt werden musste...
            //...werden alle Einstellungen-Objekte neu erstellt und mit dem Einstellungswert true in die Liste eingefügt
            einstellung = new Einstellung("Hinweisfenster anzeigen", true);
            LIST_2.add(einstellung);
            einstellung = new Einstellung("Fehlerfenster anzeigen", true);
            LIST_2.add(einstellung);
            einstellung = new Einstellung("Bei Export kopieren", true);
            LIST_2.add(einstellung);
            einstellung = new Einstellung("Speichern-Abfrage", true);
            LIST_2.add(einstellung);
        }

        for (int i = 0; i < len; i++) {
            einstellung = LIST_2.get(i);
            if (einstellung.getEinstellungTyp().equals(pEinstellungTyp)) {
                einstellung.setEinstellungWert(pEinstellungWert); //Wenn richtiges Einstellungs-Objekt gefunden ist, wird der Einstellungswert verändert
            }
        }
    }

    //Aktuelle Einstellungswerte werden zurückgegeben
    public boolean getEinstellungWertZuEinstellungTyp(String pEinstellungTyp) {
        boolean getEinstellungWert = true;
        int len = LIST_2.size();
        for (int i = 0; i < len; i++) {
            Einstellung einstellung = LIST_2.get(i);
            if (einstellung.getEinstellungTyp().equals(pEinstellungTyp)) { //Wenn Listen-Element gefunden wird, dass den Einstellungswert für den übergebenen Einstellungstyp speichert, wird der Einstellungstyp auf den entsprechenden Wert gesetzt
                getEinstellungWert = einstellung.getEinstellungWert();
            }
        }
        return getEinstellungWert;
    }
}