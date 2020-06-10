package code;
import java.lang.*;
import java.io.*;

public class Manager {
    private static final List<Datensatz> list = new List<Datensatz>(); /* Neue generische Liste des Datentpys 'Datensatz' wird erstellt */
    private static Datensatz datensatz;
    private static String kursstufe;
    private static String nachname;
    private static String vorname;
    private static String grund;
    private static String mac;

    public static void main(String[] args) {
        dateizudatensatz(); /* Datensätze, die in txt-Datei gespeichert sind, werden als Datensatz-Objekte in die Liste eingefügt (via insert-Methode) */
    }

    public static void dateizudatensatz() {
        String filename = "C:\\Users\\46B120U11\\IdeaProjects\\mac2020premium\\resources\\Textdateien\\main.txt";

        FileReader fr = null;
        try {
            fr = new FileReader(filename);
        } catch (FileNotFoundException e) {
            System.out.println("The file could not be found by FileReader (FileNotFoundException)");
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

        datensatz = new Datensatz(kursstufe, nachname, vorname, grund, mac);

        list.insert(datensatz);
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