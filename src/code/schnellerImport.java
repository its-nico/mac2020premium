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

    private static String[] splittedline;

    private static String IPString;
    private static String FormatString;
    private static final String test = ""; //"leerer" String wird später zum Verlgleichen genutzt

    private boolean bereitsImportiert = false;

    private static final Korrektur korrektur = new Korrektur();
    private final OeffnenDialogClass odc = new OeffnenDialogClass();
    private static final Dialogfenster dialogfenster = new Dialogfenster();
    private static final Manager manager = new Manager();

    public static void schnellerImport(String importfilepath, ArrayList liste) {

        IPString = ""; //Auf diesen String werden später die IP-Adressen aufaddiert
        FormatString = ""; //Auf diesen String werden später die MAC-Adressen, die nicht im korrekten Format sind, aufaddiert

        FileReader fr = null;
        try {
            fr = new FileReader(importfilepath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException f) {
            f.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);

        String line = "";
        while (line != null) {
            try {
                line = br.readLine(); //Jeweils ein komplette Zeile, also ein Datensatz, wird über die Variable 'line' gespeichert
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line != null) {
                splitline(line); //Die einzelnen Datensätze (lines) werden in ihre 5 Attribute aufgeteilt
                korrektur.logErstellen(mac); //Der Kopf des Logs wird erstellt
                if (korrektur.istIP(mac)){ //Handelt es sich um eine IP?
                    IPString = "<html>" + IPString + "<br>" + mac + "</html>"; //Alle IP-Adressen werden aufaddiert, um dann gemeinsam aufaddiert werden zu können
                }
                else {
                    mac = korrektur.autoKorrektur(mac); //Falls keine IP wird die Adresse autokorrigierrt
                    if (korrektur.format(mac)) { //Ist die Adresse im korrekten Format?
                        datensatz = new Datensatz(kursstufe, nachname, vorname, mac, grund); //Falls ja wird ein neuer Datensatz mit den Daten erstellt
                        liste.add(datensatz);
                    }
                    else {
                        FormatString = "<html>" + FormatString + "<br>" + mac + "</html>"; //Alle Adressen im falschen Format werden aufaddiert
                    }
                }
            }
        }

        if (!IPString.equals(test)){ //Ist der String mit den falschen IPs nicht mehr leer?
            dialogfenster.keineMacAdresse(IPString); //Die falschen Adressen werden in einem Dialogfenster ausgegeben
        }
        if (!FormatString.equals(test)){ //Ist der String mit den falschen MACs nicht mehr leer?
            dialogfenster.falschesFormat(FormatString); //Die falschen Adressen werden in einem Dialogfenster ausgegeben
        }
    }

    public static void splitline(String line) {
        splittedline = line.split(";"); //Die Methode 'split' teilt den String 'line' mithilfe des definierten Trennzeichens ';'
        kursstufe = splittedline[0];
        nachname = splittedline[1];
        vorname = splittedline[2];
        mac = splittedline[3];

        int length = splittedline.length;
        if (length > 4) { //Falls weitere Bemerkungen eingetragen wurden
            grund = splittedline[4];
        }
    }

    public void merkeDateipfad (String pPfad) {
        String merkeDateipfadFile = "./Importpfade.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(merkeDateipfadFile, true); //FileWriter wird erstellt
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write(pPfad);
            bw.write(System.getProperty("line.separator")); //So kann der bw die Werte untereinander einfügen, da er Zeilenümrüche erstellen kann
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean bereitsImportiert (String pPfad) {
        String merkeDateipfadFile = "./Importpfade.txt";

        FileWriter fw = null;
        try {
            fw = new FileWriter(merkeDateipfadFile, true); //FileWriter wird erstellt
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
            fr = new FileReader(merkeDateipfadFile); //FileReader wird erstellt
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
            }
            anzahlDateipfade++;
            if (line != null && line.equals(pPfad)) {
                bereitsImportiert = true;
            }
        }

        return bereitsImportiert;
    }
}