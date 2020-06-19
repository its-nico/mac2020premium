package code;

import code.Datensatz;
import code.OeffnenDialogClass;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class schnellerImport {
    private static Datensatz datensatz;
    private static String kursstufe;
    private static String nachname;
    private static String vorname;
    private static String grund;
    private static String mac;

    private static Korrektur korrektur = new Korrektur();

    OeffnenDialogClass odc = new OeffnenDialogClass();



    public static void schnellerImport(String importfilepath, List1 liste) {
        //odc.main();
        //String importfilepath = odc.getInputVerzStr();
        //File filepath = new File("importfile"); /* Dateiname wird durch OeffnenDialogClass übergeben */

        FileReader fr = null;
        try {
            fr = new FileReader(importfilepath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("The file could not be found by FileReader (FileNotFoundException)");
        } catch (NullPointerException f) {
            f.printStackTrace();
            System.out.println("Der Benutzer hat JFileChooser mit X geschlossen, ohne eine Datei auszuwählen");
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
                korrektur.logErstellen();
                splitline(line); /* Die einzelnen Datensätze (lines) werden in ihre 5 Attribute aufgeteilt */
                if (korrektur.istIPOhneLog(mac)){
                    System.out.println("Der Datensatz zur Adresse " + mac + " wurde nicht übernommen, da es sich um eine IP-Adresse handelt.");
                }
                else {
                    datensatz = new Datensatz(kursstufe, nachname, vorname, mac, grund);
                    liste.append(datensatz);
                }
            }
        }
    }

    public static void splitline(String line) {
        String[] splittedline = line.split("; "); /* Die Methode 'split' teilt den String 'line' mithilfe des definierten Trennzeichens '; '*/
        kursstufe = splittedline[0];
        nachname = splittedline[1];
        vorname = splittedline[2];
        mac = korrektur.autoKorrektur(splittedline[3]);
        grund = splittedline[4];
    }
}
