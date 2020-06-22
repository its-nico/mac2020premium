package code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;

public class schnellerImport {
    private static Datensatz datensatz;
    private static String kursstufe;
    private static String nachname;
    private static String vorname;
    private static String grund;
    private static String mac;

    private static String IPString;
    private static String FormatString;
    private static String test = "";

    private static Korrektur korrektur = new Korrektur();
    private OeffnenDialogClass odc = new OeffnenDialogClass();
    private static Dialogfenster dialogfenster = new Dialogfenster();

    public static void schnellerImport(String importfilepath, List1 liste) {

        IPString = "";
        FormatString = "";

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
                splitline(line); /* Die einzelnen Datensätze (lines) werden in ihre 5 Attribute aufgeteilt */
                korrektur.logErstellen(mac);
                if (korrektur.istIP(mac)){
                   // dialogfenster.keineMacAdresse(mac);
                    System.out.println("Der Datensatz zur Adresse " + mac + " wurde nicht übernommen, da es sich um eine IP-Adresse handelt.");
                    IPString = IPString + "\n"+  mac;
                }
                else {
                    mac = korrektur.autoKorrektur(mac);
                    if (korrektur.format(mac)) {
                        datensatz = new Datensatz(kursstufe, nachname, vorname, mac, grund);
                        liste.append(datensatz);
                    }
                    else {
                       // dialogfenster.falschesFormat(mac);
                        System.out.println("Die Adresse befindet sich nicht im für MAC-Adressen erforderlichen Format (xx:xx:xx:xx:xx:xx). Sie konnte nicht übernommen werden.");
                        FormatString = FormatString + "\n" + mac;
                    }

                }
            }
        }

        if (!IPString.equals(test)){
            dialogfenster.keineMacAdresse(IPString);
        }
        if (!FormatString.equals(test)){
            dialogfenster.falschesFormat(FormatString);
        }
    }

    public static void splitline(String line) {
        String[] splittedline = line.split("; "); /* Die Methode 'split' teilt den String 'line' mithilfe des definierten Trennzeichens '; '*/
        kursstufe = splittedline[0];
        nachname = splittedline[1];
        vorname = splittedline[2];
        mac = splittedline[3];
        grund = splittedline[4];
    }
}
