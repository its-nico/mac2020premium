package code;

import java.lang.*;
import java.io.*;

public class Manager {
    private static List1<Datensatz> LIST_1 = new List1<Datensatz>(); /* Neue generische Liste des Datentpys 'Datensatz' wird erstellt */
    private static Datensatz datensatz;
    private static String kursstufe;
    private static String nachname;
    private static String vorname;
    private static String grund;
    private static String mac;

    private static Speichern speichern;

    private static Export export = new Export();

    private static Korrektur korrektur = new Korrektur();

    public static void main(String[] args) {
        System.out.println(LIST_1.isEmpty());
        LIST_1 = speichern.laden();
    //   dateizudatensatz(); /* Datensätze, die in txt-Datei gespeichert sind, werden als Datensatz-Objekte in die Liste eingefügt (via insert-Methode) */
 //     speichern.abspeichern(LIST_1);
        System.out.println(LIST_1.isEmpty());

    }
}

