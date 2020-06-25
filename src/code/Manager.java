package code;

import java.io.*;
import java.lang.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Date;


public class Manager {
    private static ArrayList<Datensatz> LIST_1 = new ArrayList<>(); /* Neue generische Liste des Datentpys 'Datensatz' wird erstellt */
    private static Datensatz datensatz;

    //Referenzen auf fremde Klassen
    private static final Export export = new Export();
    private static final Korrektur korrektur = new Korrektur();
    private static final schnellerImport schnellerImport = new schnellerImport();
    private static final Dialogfenster dialogfenster = new Dialogfenster();
    private static final Datenbank datenbank = new Datenbank();

    private static Speichern speichern;

    public static void main(String[] args) {
    }

    //Methode exportiereMac() aus Export wird aufgerufen. Die Liste wird als Parameter übergeben.
    public static void exportiereMac(){
        export.exportiereMac(LIST_1);
    }

    //Methode laden() aus Speichern wird aufgrufen. So lädt das Programm die zuvor gespeicherte Liste (Serialisierung).
    public static void laden(){
        LIST_1 = speichern.laden();
    }

    //Die Liste wird hier gespeichert (Serialisierung). Hierfür wir die Methode abspeichern() aus Speichern aufgerufen.
    public static void speichern(){
        speichern.abspeichern(LIST_1);
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
            pMac = korrektur.autoKorrektur(pMac); //die Adresse wird autokorrigiert
            if (korrektur.format(pMac)) { //Überprüfung des für MAC-Adressen erforderlichen Formats
                datensatz = new Datensatz(pKursstufe, pNachname, pVorname, pMac, pGrund); //neuer Datensatz mit übergebenen Parametern wird erzeugt
                LIST_1.add(datensatz); //Datensatz wird an die Liste ergänzt

                return ("<html>Der Datensatz zur MAC-Adresse " + pMac + " wurde erfolgreich hinzugefügt.<br>Weitere Informationen finden Sie im Korrektur-Verzeichnis.</html>"); //Rückgabe wird für das Text-Feld in der GUI-genutzt
            }
            else {
                return ("<html>Die Adresse befindet sich nicht im für MAC-Adressen erforderlichen Format (xx:xx:xx:xx:xx:xx). <br> Der Datensatz konnte daher nicht übernommen werden.<br>Weitere Informationen finden Sie im Korrektur-Verzeichnis.</html>"); //Rückgabe wird für das Text-Feld in der GUI-genutzt
            }
        }
    }

    public static void listeLoeschen() {
        LIST_1 = new ArrayList<>();
        File file = new File("./Importpfade.txt");
        if (file.exists()) {
            file.delete();
        }
    }

    public static  void datenbankErgaenzen() throws SQLException {
        datenbank.datenbankErgaenzen(LIST_1);
    }

    public static  ArrayList getList ()  {
        return LIST_1;
    }
}

