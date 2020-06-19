package code;

import java.io.*;

public class Export {

    private static String mac;
    private static String mac1;

    public static void export() { //Funktion: Exportiert die MAC-Adressen aus der main.txt-Datei in die Datei export.txt
        String exportfile = "./export.txt";
        File fileRelative = new File("./main.txt");
        String maintextfile = null;

        try {
            maintextfile = fileRelative.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        FileReader fr = null;
        try {
            fr = new FileReader(maintextfile); /* FileReader wird erstellt */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);

        /* Ab hier beginnt der "spannende" Teil */

        String line = "";
        while (line != null) {
            try {
                line = br.readLine(); /* Jeweils ein komplette Zeile, also ein Datensatz, wird aus main.txt ausgelesem und über die Variable 'line' gespeichert */
            } catch (IOException e) {
                System.out.println("An error has occurred (IOException)");
            }
            if (line != null) {
                splitline(line); /* Die einzelnen Datensätze (lines) werden in ihre 5 Attribute aufgeteilt */
                try {
                    bw.write(mac); /* Nur das Attribut 'mac' wird in die Datei 'export.txt' geschrieben */
                    bw.write(System.getProperty("line.separator")); /* So kann der bw die Werte untereinander einfügen, da er Zeilenümrüche erstellen kann*/
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            bw.close(); /* Wenn der bw nicht geschlossen wird, bleibt die export-Datei leer */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void splitline(String line) {
        String[] splittedline = line.split("; "); /* Die Methode 'split' teilt den String 'line' mithilfe des definierten Trennzeichens '; '*/
        mac = splittedline[3]; /* Nur der 5. Wert der line wird auf der Variable 'mac' gespeichert */
    }

    public void exportiereMac (List1<Datensatz> liste){ //Funktion: Exportiert die MAC-Adressen aus der Liste<Datensatz> in die Datei export.txt
        File file = new File("./export.txt");

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

        liste.toFirst();
        while (liste.hasAccess()) {
            mac1 = (liste.getContent()).getMac();
            try {
                bw.write(mac1); /* Nur das Attribut 'mac' wird in die Datei 'export.txt' geschrieben */
                bw.write(System.getProperty("line.separator")); /* So kann der bw die Werte untereinander einfügen, da er Zeilenümrüche erstellen kann*/
            } catch (IOException e) {
                e.printStackTrace();
            }
            liste.next();
        }

        try {
            bw.close(); /* Wenn der bw nicht geschlossen wird, bleibt die export-Datei leer */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}