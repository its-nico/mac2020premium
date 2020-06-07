package code;
import java.lang.*;
import java.io.*;

public class Manager {
    private static final List<Datensatz> list = new List<Datensatz>();
    private static Datensatz datensatz;
    private static String vorname;
    private static String nachname;
    private static String kursstufe;
    private static String mac;
    private static String grund;

    public static void main(String[] args) throws FileNotFoundException {
        dateizudatensatz();
        String listString = list.getContent().getKursstufe();
        System.out.println(listString);
    }

    public static void dateizudatensatz() throws FileNotFoundException {
        String filename = "main.txt";

        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        String line = "";
        while (line != null) {
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line != null) {
                splitline(line);
            }
        }

        datensatz.setVorname(vorname);
        datensatz.setNachname(nachname);
        datensatz.setKursstufe(kursstufe);
        datensatz.setMac(mac);
        datensatz.setGrund(grund);

        list.insert(datensatz);
    }

    public static void splitline(String line) {
        String[] splittedline = line.split("; ");
        splittedline[0] = vorname;
        splittedline[1] = nachname;
        splittedline[2] = kursstufe;
        splittedline[3] = mac;
        splittedline[4] = grund;
    }
}
