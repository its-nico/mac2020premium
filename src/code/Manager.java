package code;

import java.lang.*;

public class Manager {
    private static List1<Datensatz> LIST_1 = new List1<Datensatz>(); /* Neue generische Liste des Datentpys 'Datensatz' wird erstellt */
    private static Datensatz datensatz;

    private static Export export = new Export();
    private static Korrektur korrektur = new Korrektur();
    private static schnellerImport schnellerImport = new schnellerImport();
    private static Dialogfenster dialogfenster = new Dialogfenster();

    private static Speichern speichern;
    private static SpeichernUnterClass speichernUnter = new SpeichernUnterClass();

    public static void main(String[] args) {

    }

  /*  public static String korrigiere(String pText){
        return korrektur.autoKorrektur(pText);
    } */

    public static void exportiere(){
        export.export();
    }

    public static void exportiereMac(){
        export.exportiereMac(LIST_1);
    }

    public static void laden(){
        LIST_1 = speichern.laden();
    }

    public static void speichern(){
        speichern.abspeichern(LIST_1);
    }

    public static void schnellerImport (String pPfad){
        boolean bereitsImportiert = schnellerImport.bereitsImportiert(pPfad); //true, wenn Dateipfad bereits Dateipfad einer der letzten Import-Dateien war
        //schnellerImport.behalteFünfPfade();
        boolean answer = false;
        if (!bereitsImportiert) {
            schnellerImport.merkeDateipfad(pPfad);
            schnellerImport.schnellerImport(pPfad, LIST_1);
        } else {
            dialogfenster.bereitsImportiertDialog(pPfad, LIST_1);
        }
    }

    public static String ergaenze(String pKursstufe, String pNachname, String pVorname, String pMac, String pGrund) {
        korrektur.logErstellen(pMac);
        if (korrektur.istIPOhneLog(pMac)){
            return ("Der Datensatz zur Adresse " + pMac + " wurde nicht übernommen, da es sich um eine IP-Adresse handelt.\nWeitere Informationen finden Sie im Korrektur-Verzeichnis.");

        } else {
            pMac = korrektur.autoKorrektur(pMac);
            if (korrektur.format(pMac)) {
                datensatz = new Datensatz(pKursstufe, pNachname, pVorname, pMac, pGrund);
                LIST_1.append(datensatz);
                return ("Der Datensatz zur MAC-Adresse " + pMac + " wurde erfolgreich hinzugefügt.\nWeitere Informationen finden Sie im Korrektur-Verzeichnis.");
            }
            else {
                return ("<html>Die Adresse befindet sich nicht im für MAC-Adressen erforderlichen Format (xx:xx:xx:xx:xx:xx). <br> Der Datensatz konnte daher nicht übernommen werden.<br>Weitere Informationen finden Sie im Korrektur-Verzeichnis.</html>");
            }
        }
    }

    public static void listeLoeschen() {
        LIST_1 = new List1<Datensatz>();
    }
}

