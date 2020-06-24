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

    public static  ArrayList getList ()  {
        return LIST_1;
    }
    
    public static String wannListeGeaendert() { //speichert in Text-Datei, wann folgende Funktionen ausgeführt wurden: Liste leeren, Datenbank leeren (da dann keine Redundanz-Gefahr bei Datenbank-Upload besteht)
        String wannGeändert = null;
        
        String listeGeaendertFile = "./listeGeändert.txt";

        FileWriter fw = null;
        try {
            fw = new FileWriter(listeGeaendertFile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write(""); //Hier wird nichts in die Datei geschrieben, damit eine Datei Importpfade.txt erstellt wird, falls keine existiert
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileReader fr = null;
        try {
            fr = new FileReader(listeGeaendertFile); /* FileReader wird erstellt */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);

        try {
            wannGeändert = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wannGeändert;
    }
    
    public static void aktualisiereListeGeaendert() {
        String listeGeändertFile = "./listeGeändert.txt";

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String uhrzeit = sdf.format(new Date());
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy");
        String tag = sdf2.format(new Date());

        FileWriter fw = null;
        try {
            fw = new FileWriter(listeGeändertFile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write(""); //Hier wird nichts in die Datei geschrieben, damit eine Datei Importpfade.txt erstellt wird, falls keine existiert
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.write(uhrzeit+tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String wannListeHochgeladen() { //speichert in Text-Datei, wann folgende Funktionen ausgeführt wurden: Liste leeren, Datenbank leeren (da dann keine Redundanz-Gefahr bei Datenbank-Upload besteht)
        String wannHochgeladen = null;

        String listeHochgeladenFile = "./listeHochgeladen.txt";

        FileWriter fw = null;
        try {
            fw = new FileWriter(listeHochgeladenFile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write(""); //Hier wird nichts in die Datei geschrieben, damit eine Datei Importpfade.txt erstellt wird, falls keine existiert
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileReader fr = null;
        try {
            fr = new FileReader(listeHochgeladenFile); /* FileReader wird erstellt */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);

        try {
            wannHochgeladen = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wannHochgeladen;
    }

    public static void aktualisiereListeHochgeladen() {
        String listeGeaendertFile = "./listeGeändert.txt";

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String uhrzeit = sdf.format(new Date());
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy");
        String tag = sdf2.format(new Date());

        FileWriter fw = null;
        try {
            fw = new FileWriter(listeGeaendertFile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write(""); //Hier wird nichts in die Datei geschrieben, damit eine Datei Importpfade.txt erstellt wird, falls keine existiert
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.write(uhrzeit+tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

