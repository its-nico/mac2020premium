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
   // private static Import anImport = new Import();
    private static schnellerImport schnellerImport = new schnellerImport();

    private static Speichern speichern;
    private static SpeichernUnterClass speichernUnter = new SpeichernUnterClass();


    public static void main(String[] args) {
        System.out.println(LIST_1.isEmpty());
        LIST_1 = speichern.laden();
        //   dateizudatensatz(); /* Datensätze, die in txt-Datei gespeichert sind, werden als Datensatz-Objekte in die Liste eingefügt (via insert-Methode) */
        //     speichern.abspeichern(LIST_1);
        System.out.println(LIST_1.isEmpty());




    }

  /*  public static String korrigiere(String pText){
        return korrektur.autoKorrektur(pText);
    } */

    public static void  exportiere(){
        export.export();
    }

    public static void schnellerImport (String pPfad){
        schnellerImport.schnellerImport(pPfad, LIST_1);
    }

    public static void ergaenze(String pKursstufe, String pNachname, String pVorname, String pMac, String pGrund) {
        korrektur.logErstellen();
        if (korrektur.istIPOhneLog(mac)){
            System.out.println("Der Datensatz zur Adresse " + mac + " wurde nicht übernommen, da es sich um eine IP-Adresse handelt.");
        }
        else {
            Datensatz datensatzNeu = new Datensatz(pKursstufe, pNachname, pVorname, korrektur.autoKorrektur(pMac), pGrund);
            LIST_1.append(datensatzNeu);
        }
    }



}

