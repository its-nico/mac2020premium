package code;

import java.lang.*;
import java.io.*;

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
    private static Fehlermeldungen fehlermeldungen = new Fehlermeldungen();

    private static Speichern speichern;
    private static SpeichernUnterClass speichernUnter = new SpeichernUnterClass();

    public static void main(String[] args) {

    }

  /*  public static String korrigiere(String pText){
        return korrektur.autoKorrektur(pText);
    } */

    public static void  exportiere(){
        export.export();
    }

    public static void  exportiereMac(){
        export.exportiereMac(LIST_1);
    }

    public static void laden(){
        LIST_1 = speichern.laden();
    }

    public static void speichern(){
        speichern.abspeichern(LIST_1);
    }

    public static void schnellerImport (String pPfad){
        schnellerImport.schnellerImport(pPfad, LIST_1);
    }

    public static void ergaenze(String pKursstufe, String pNachname, String pVorname, String pMac, String pGrund) {
        korrektur.logErstellen(pMac);
        if (korrektur.istIPOhneLog(pMac)){
            System.out.println("Der Datensatz zur Adresse " + pMac + " wurde nicht übernommen, da es sich um eine IP-Adresse handelt.");

        } else {
            Datensatz datensatzNeu = new Datensatz(pKursstufe, pNachname, pVorname, korrektur.autoKorrektur(pMac), pGrund);
            LIST_1.append(datensatzNeu);
        }
    }

    public static void listeLoeschen() {
        LIST_1 = new List1<Datensatz>();
    }
}

