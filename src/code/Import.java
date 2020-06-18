package code;

import java.io.*;

public class Import {
    private static final List1<Datensatz> LIST_1 = new List1<Datensatz>(); /* Neue generische Liste des Datentpys 'Datensatz' wird erstellt */
    private static Datensatz datensatz;
    private static String kursstufe;
    private static String nachname;
    private static String vorname;
    private static String grund;
    private static String mac;

    OeffnenDialogClass odc = new OeffnenDialogClass();

    public static void main(String[] args) { /* Main-Methode wird benötigt, damit Methode impoert1 eigenständig gestartet werden kann (ohne dass andere Main-Klasse Methode startet) */
        import1();
    }

    public static List1<Datensatz> import1() {
        //odc.main();
        //String importfilepath = odc.getInputVerzStr();
        //File filepath = new File("importfile"); /* Dateiname wird durch OeffnenDialogClass übergeben */
        String importfilepath = "C:/Users/user/Desktop/test.txt";

        FileReader fr = null;
        try {
            fr = new FileReader(importfilepath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("The file could not be found by FileReader (FileNotFoundException)");
        }
        BufferedReader br = new BufferedReader(fr);

        String line = "";
        while (line != null) {
            try {
                line = br.readLine(); /* Jeweils ein komplette Zeile, also ein Datensatz, wird über die Variable 'line' gespeichert */
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("An error has occurred (IOException)");
            }
            if (line != null) {
                splitline(line); /* Die einzelnen Datensätze (lines) werden in ihre 5 Attribute aufgeteilt */
                datensatz = new Datensatz(kursstufe, nachname, vorname, grund, mac);
                LIST_1.append(datensatz);
            }
        }
        return LIST_1;
    }

    public static void splitline(String line) {
        String[] splittedline = line.split("; "); /* Die Methode 'split' teilt den String 'line' mithilfe des definierten Trennzeichens '; '*/
        kursstufe = splittedline[0];
        nachname = splittedline[1];
        vorname = splittedline[2];
        grund = splittedline[3];
        mac = splittedline[4];
    }
}
