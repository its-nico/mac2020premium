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
            file.delete();
        }

        String exportfile = "./export.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        int len = liste.size();
        for (int i = 0; i < len; i++) {
            Datensatz datensatz1 = liste.get(i);
            String mac1 = datensatz1.getMac();
            try {
                bw.write(mac1); /* Nur das Attribut 'mac' wird in die Datei 'export.txt' geschrieben */
                bw.write(System.getProperty("line.separator")); /* So kann der bw die Werte untereinander einfügen, da er Zeilenümrüche erstellen kann*/
                clipboardstring.append(mac1).append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StringSelection stringSelection = new StringSelection(clipboardstring.toString());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        try {
            bw.close(); /* Wenn der bw nicht geschlossen wird, bleibt die export-Datei leer */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}