package code;

import java.lang.*;

public class Manager {
    private static List1<Datensatz> LIST_1 = new List1<Datensatz>(); /* Neue generische Liste des Datentpys 'Datensatz' wird erstellt */
    private static Datensatz datensatz;
    private static String kursstufe;
    private static String nachname;
    private static String vorname;
    private static String mac;
    private static String grund;

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
        boolean bereitsImportiert = schnellerImport.bereitsImportiert(pPfad);
        boolean answer = false;
        if (!bereitsImportiert) {
            schnellerImport.merkeDateipfad(pPfad);
            schnellerImport.schnellerImport(pPfad, LIST_1);
        } else {
            answer = dialogfenster.bereitsImportiertDialog();
            if (answer) {
                schnellerImport.schnellerImport(pPfad, LIST_1);
            } else {
                System.out.println("Die Datei wurde nicht importiert");
            }
        }
    }

    public static String ergaenze(String pKursstufe, String pNachname, String pVorname, String pMac, String pGrund) {
        korrektur.logErstellen(pMac);
        if (korrektur.istIPOhneLog(pMac)){
            return ("Der Datensatz zur Adresse " + pMac + " wurde nicht übernommen, da es sich um eine IP-Adresse handelt.");

        } else {
            mac = korrektur.autoKorrektur(mac);
            if (korrektur.format(mac)) {
                datensatz = new Datensatz(kursstufe, nachname, vorname, mac, grund);
                LIST_1.append(datensatz);
                return ("Der Datensatz zur MAC-Adresse " + pMac + " wurde erfolgreich hinzugefügt.");
            }
            else {
                return ("Die Adresse befindet sich nicht im für MAC-Adressen erforderlichen Format (xx:xx:xx:xx:xx:xx). Sie konnte nicht übernommen werden.");
            }
        }
    }

    public static void listeLoeschen() {
        LIST_1 = new List1<Datensatz>();
    }
}

