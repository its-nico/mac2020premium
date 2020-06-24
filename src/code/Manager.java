package code;

import java.lang.*;

import java.util.ArrayList;
import java.sql.SQLException;


public class Manager {
    private static ArrayList<Datensatz> LIST_1 = new ArrayList<>(); /* Neue generische Liste des Datentpys 'Datensatz' wird erstellt */
    private static Datensatz datensatz;

    private static final Export export = new Export();
    private static final Korrektur korrektur = new Korrektur();
    private static final schnellerImport schnellerImport = new schnellerImport();
    private static final Dialogfenster dialogfenster = new Dialogfenster();
    private static final Datenbank datenbank = new Datenbank();

    private static Speichern speichern;

    private static final CollectionUtil dopplung = new CollectionUtil();

    public static void main(String[] args) {
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
        if (pPfad != null) {
            boolean bereitsImportiert = schnellerImport.bereitsImportiert(pPfad); //true, wenn Dateipfad bereits Dateipfad einer der letzten Import-Dateien war
            boolean answer = false;
            if (!bereitsImportiert) {
                schnellerImport.merkeDateipfad(pPfad);
                schnellerImport.schnellerImport(pPfad, LIST_1);
            } else {
                dialogfenster.bereitsImportiertDialog(pPfad, LIST_1);
            }
        }
    }

    public static String ergaenze(String pKursstufe, String pNachname, String pVorname, String pMac, String pGrund) {
        korrektur.logErstellen(pMac);
        if (korrektur.istIP(pMac)){
            return ("<html>Der Datensatz zur Adresse " + pMac + " wurde nicht übernommen, da es sich um eine IP-Adresse handelt.<br>Weitere Informationen finden Sie im Korrektur-Verzeichnis.</html>");

        } else {
            pMac = korrektur.autoKorrektur(pMac);
            if (korrektur.format(pMac)) {
                datensatz = new Datensatz(pKursstufe, pNachname, pVorname, pMac, pGrund);
                LIST_1.add(datensatz);

                return ("<html>Der Datensatz zur MAC-Adresse " + pMac + " wurde erfolgreich hinzugefügt.<br>Weitere Informationen finden Sie im Korrektur-Verzeichnis.</html>");
            }
            else {
                return ("<html>Die Adresse befindet sich nicht im für MAC-Adressen erforderlichen Format (xx:xx:xx:xx:xx:xx). <br> Der Datensatz konnte daher nicht übernommen werden.<br>Weitere Informationen finden Sie im Korrektur-Verzeichnis.</html>");
            }
        }
    }

    public static void listeLoeschen() {
        LIST_1 = new ArrayList<>();
    }

    public static void dopplungenLoeschen() {
        LIST_1 = dopplung.removeDuplicate(LIST_1);
    }

    public static  void datenbankErgaenzen() throws SQLException {
        datenbank.datenbankErgaenzen(LIST_1);
    }
}

