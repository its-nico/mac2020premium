package code;

import java.io.*;
import java.util.ArrayList;

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

    private boolean bereitsImportiert = false;

    private static Korrektur korrektur = new Korrektur();
    private OeffnenDialogClass odc = new OeffnenDialogClass();
    private static Dialogfenster dialogfenster = new Dialogfenster();

    public static void schnellerImport(String importfilepath, ArrayList liste) {

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
                    korrektur.logEnde(false);
                    System.out.println("Der Datensatz zur Adresse " + mac + " wurde nicht übernommen, da es sich um eine IP-Adresse handelt.");
                    IPString = "<html>" + IPString + "<br>" + mac + "</html>";
                }
                else {
                    mac = korrektur.autoKorrektur(mac);
                    if (korrektur.format(mac)) {
                       // korrektur.logEnde(true);
                        datensatz = new Datensatz(kursstufe, nachname, vorname, mac, grund);
                        liste.add(datensatz);
                    }
                    else {
                        //korrektur.logEnde(false);
                        System.out.println("Die Adresse befindet sich nicht im für MAC-Adressen erforderlichen Format (xx:xx:xx:xx:xx:xx). Sie konnte nicht übernommen werden.");
                        FormatString = "<html>" + FormatString + "<br>" + mac + "</html>";
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
        if (splittedline[4] != null) {
            grund = splittedline[4];
        } else {
            grund = "";
        }

    }

    public void merkeDateipfad (String pPfad) {
        String merkeDateipfadFile = "./Importpfade.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(merkeDateipfadFile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write(pPfad);
            bw.write(System.getProperty("line.separator")); /* So kann der bw die Werte untereinander einfügen, da er Zeilenümrüche erstellen kann*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void behalteFuenfPfade() { /* Hier wird sichergestellt, dass nur die letzten 5 Dateipfade gespeichert werden */
        String merkeDateipfadFile = "./Importpfade.txt";
        int anzahlDateipfade = 0;

        FileReader fr = null;
        try {
            fr = new FileReader(merkeDateipfadFile); /* FileReader wird erstellt */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);

        String line = "";
        while (line != null) {
            if (line != null) {
                try {
                    line = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("An error has occurred (IOException)");
                }
                anzahlDateipfade++;
                System.out.println("Anzahl: " + anzahlDateipfade);
            }
        }

        FileReader fr2 = null;
        try {
            fr2 = new FileReader(merkeDateipfadFile); /* FileReader wird erstellt */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br2 = new BufferedReader(fr2);

        while (anzahlDateipfade > 5) {
            try {
                String currentline = br2.readLine();
                //System.out.println(currentline);
                if (currentline != null) {
                    currentline.trim();
                    System.out.println("Versucht, Zeile zu löschen. Anzahl: " + anzahlDateipfade);
                }
                anzahlDateipfade--;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean bereitsImportiert (String pPfad) {
        String merkeDateipfadFile = "./Importpfade.txt";

        FileWriter fw = null;
        try {
            fw = new FileWriter(merkeDateipfadFile, true); /* FileWriter wird erstellt */
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
            fr = new FileReader(merkeDateipfadFile); /* FileReader wird erstellt */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);

        int anzahlDateipfade = 0;
        String line = "";
        while (line != null && anzahlDateipfade < 5) {
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("An error has occurred (IOException)");
            }
            anzahlDateipfade++;
            if (line != null && line.equals(pPfad)) {
                bereitsImportiert = true;
            } /*Hier darf keine else-Statement sein, da jede Zeile der Datei Importpfade.txt überprüft wird.
            bereitsImportiert hat am Anfang den Wert false und wirt auf true gesetzt, sobald der Dateipfad einmal in der Datei vorkommt */
        }
        return bereitsImportiert;
    }
}
