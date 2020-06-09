package code;
import java.lang.*;
import java.io.*;

public class Manager {
    private static final List<Datensatz> list = new List<Datensatz>(); /* Neue generische Liste des Datentpys 'Datensatz' wird erstellt */
    private static Datensatz datensatz;
    private static String vorname;
    private static String nachname;
    private static String kursstufe;
    private static String mac;
    private static String grund;

    public static void main(String[] args) {
        dateizudatensatz(); /* Datensätze, die in txt-Datei gespeichert sind, werden als Datensatz-Objekte in die Liste eingefügt (via insert-Methode) */
    }

    public static void dateizudatensatz() {
        String filename = "text.txt";

        FileReader fr = null;
        try {
            fr = new FileReader(filename);
        } catch (FileNotFoundException e) {
            System.out.println("The file could not be found (FileNotFoundException)\nCheck if the setting 'Working Directory' is correct in your project configuration");
        }
        BufferedReader br = new BufferedReader(fr);

        String line = "";
        while (line != null) {
            try {
                line = br.readLine(); /* Jeweils ein kompklette Zeile, also ein atensatz, wird über die Variable 'line' gespeichert */
            } catch (IOException e) {
                System.out.println("An error has occurred (IOException)");
            }
            if (line != null) {
                splitline(line); /* Die einzelnen Datensätze (lines) werden in ihre 5 Attribute aufgeteilt */
            }
        }

        datensatz = new Datensatz(vorname, nachname, kursstufe, mac, grund);

        list.insert(datensatz);
    }

    public static void splitline(String line) {
        String[] splittedline = line.split("; "); /* Die Methode 'split' teilt den String 'line' mithilfe des definierten Trennzeichens '; '*/
        vorname = splittedline[0];
        nachname = splittedline[1];
        kursstufe = splittedline[2];
        mac = splittedline[3];
        grund = splittedline[4];
    }
}