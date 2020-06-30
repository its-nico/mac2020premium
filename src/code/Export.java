package code;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.util.ArrayList;

public class Export {

    public void exportiereMac (ArrayList<Datensatz> liste){ //Funktion: Exportiert die MAC-Adressen aus der Liste<Datensatz> in die Datei export.txt
        File file = new File("./export.txt");
        StringBuilder clipboardstring = new StringBuilder();

        //Zuvor alle mit dem File assoziierten Streams schließen...
        if(file.exists()){
            file.delete(); //Falls export.txt existiert, wird es gelöscht, damit immer nur die aktuelle in der Liste gespeicherten MAC-Adressen angezeigt werden
        }

        String exportfile = "./export.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); //FileWriter wird erstellt
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        int len = liste.size();
        for (int i = 0; i < len; i++) { //Jedes Element in der Liste...
            Datensatz datensatz1 = liste.get(i); //Datensatz an der aktuellen Position i wird auf Datensatz-Objekt gespeichert
            String mac1 = datensatz1.getMac(); //MAC dieses Datensatzes wird gespeichert
            try {
                bw.write(mac1); //Nur das Attribut 'mac' wird in die Datei 'export.txt' geschrieben
                bw.write(System.getProperty("line.separator")); //So kann der bw die Werte untereinander einfügen, da er Zeilenümrüche erstellen kann
                clipboardstring.append(mac1).append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StringSelection stringSelection = new StringSelection(clipboardstring.toString());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); //Zwischenablage-Objekt wird erstellt
        clipboard.setContents(stringSelection, null); //String, der alle MAC-Adressen in Export.txt enthält, wird in Zwischenablage abgelegt

        try {
            bw.close(); //Wenn der bw nicht geschlossen wird, bleibt die export-Datei leer
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportiereDatensaetze (ArrayList<Datensatz> liste){ //Funktion: Exportiert die MAC-Adressen aus der Liste<Datensatz> in die Datei export.txt
        File file = new File("./datensaetze.txt");

        //Zuvor alle mit dem File assoziierten Streams schließen...
        if(file.exists()){
            file.delete(); //Falls export.txt existiert, wird es gelöscht, damit immer nur die aktuelle in der Liste gespeicherten MAC-Adressen angezeigt werden
        }

        String datensaetzeFile = "./datensaetze.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(datensaetzeFile, true); //FileWriter wird erstellt
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        int len = liste.size();
        for (int i = 0; i < len; i++) { //Jedes Element in der Liste...
            Datensatz datensatz1 = liste.get(i); //Datensatz an der aktuellen Position i wird auf Datensatz-Objekt gespeichert

            String kursstufe = datensatz1.getKursstufe();
            String vorname = datensatz1.getVorname();
            String nachname = datensatz1.getNachname();
            String mac = datensatz1.getMac();
            String grund = datensatz1.getGrund();

            try {
                bw.write(kursstufe + ";" + vorname + ";" + nachname + ";" + mac + ";" + grund); //Alle Attribute des Datenssatzes werden, von ; getrennt, in die txt-Datei geschrieben
                bw.write(System.getProperty("line.separator")); //So kann der bw die Werte untereinander einfügen, da er Zeilenümrüche erstellen kann
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            bw.close(); //Wenn der bw nicht geschlossen wird, bleibt die datensaetze-Datei leer
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}