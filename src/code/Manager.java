package code;
import java.lang.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.net.URL;
import java.lang.*;

public class Manager {
    private static final List<Datensatz> list = new List<Datensatz>();
    private static Datensatz datensatz;
    private static String vorname;
    private static String nachname;
    private static String kursstufe;
    private static String mac;
    private static String grund;

    public static void main(String[] args) throws IOException {
        //Datensatz daten1 = new Datensatz("test","test","test","test","test");
        //Datensatz daten2 = new Datensatz("test","test","test2","test","test");
        //list.insert(daten1);
        //list.insert(daten2);

        dateizudatensatz();

        String listString = list.getContent().getKursstufe();

        System.out.println(listString);
    }

    public static void dateizudatensatz() throws IOException {

        File filename = new File("C:\\Users\\Admin\\Desktop\\mac2020premium\\resources\\Textdateien\\main.txt");
        // FileInputStream fis = null;


        //String filename =  "main.txt";
        FileReader fr = null;

        try {
            fr = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File " + filename + " not found (FileNotFoundException)");
        }
        BufferedReader br = new BufferedReader(fr);

        String line = "";
        while (line != null) {
            line = br.readLine();
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
