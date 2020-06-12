package code;
import java.io.*;
import java.util.regex.*;

public class Korrektur {

    public int zaehler = 1; //Zählervariable wird erstellt, damit die Logs später alle verschieden benannt werden können

    String textVorIP = null; //Der ursprünglich zu überprüfende Text wird in der Variable gespeichert, damit später das Log damit erstellt werden kann
    boolean bool;
    String textVorO = null;
    String textNachO = null; //Der korrigierte Text wird in der Variable gespeichert, damit später das Log damit erstellt werden kann
    String textVorLeerzeichen = null;
    String textNachLeerzeichen = null;
    String textVorBindestriche = null;
    String textNachBindestriche = null;

    /* Klasse führt automatisch alle Korrektur-Methoden auf einmal aus */
    public String autoKorrektur(String text) {
        if (istIP(text)) {
            System.err.println("Es handelt sich hierbei um eine IP-Adresse. Bitte geben Sie eine MAC-Adresse ein!");
            return null;
        }
        if (istKorrekt(text)){
            return text;
        }
        else {
            return (o(bindestriche(leerzeichen(text))));
        }
    }

    /* Alle Os (groß- und kleingeschrieben) werden mit Nullen ersetzt */
    public String o(String text) {
        textVorO = text;
        text = text.replace("o", "0");
        text = text.replace("O", "0");
        textNachO = text;
        return text;
    }

    /* Alle Leerzeichen werden gelöscht */
    public String leerzeichen(String text) {
        textVorLeerzeichen = text;
        text = text.replace(" ", "");
        textNachLeerzeichen = text;
        return text;
    }

    /* Alle Bindestriche werden mit Doppelpunkten ersetzt */
    public String bindestriche(String text) {
        textVorBindestriche = text;
        text = text.replace("-",":");
        textNachBindestriche = text;
        return text;
    }

    /* Überprüfung, ob es sich um eine IP-Adresse statt einer MAC-Adresse handelt */
    public boolean istIP(String text) {
        textVorIP = text;
        bool = (Pattern.matches("[^a-z^A-Z]", text)) && text.contains(".") && !text.contains(":") && !text.contains(" ");
        return bool;
    }

    /* Überprüfung, ob die MAC-Adresse korrekt ist */
    public boolean istKorrekt(String text) {
        return !text.contains("o") && !text.contains("O") && !text.contains("-") && !text.contains(" ") && !istIP(text);
    }

    public void logErstellen() {
        String exportfile = "./log" + zaehler + ".txt";

        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write("- - - - - - - - - - - - - - - - - - \n");
            bw.write("Folgende Änderungen wurden durch die Korrektur an der möglicherweise fehlerhaften MAC-Adresse (" + textVorIP + ") vorgenommen:\n \n");

            if (bool == true){
                bw.write("Es handelt sich um eine IP-Adresse, nicht um eine MAC-Adresse." +"\n");
            }
            else {
                if (!textVorLeerzeichen.equals(textNachLeerzeichen)) {
                    bw.write("Die Leerzeichen wurden gelöscht: " + textNachLeerzeichen +"\n");
                }
                if (!textVorBindestriche.equals(textNachBindestriche)) {
                    bw.write("Die Bindestriche wurden durch Doppelpunkte ersetzt: " + textNachBindestriche + "\n" );
                }
                if (!textVorO.equals(textNachO)){
                    bw.write("Die Buchstaben 'O' wurden durch Nullen ersetzt: " + textNachO + "\n" );
                }
            if (istKorrekt(textVorIP) == true){
                bw.write("Die MAC-Adresse ist bereits korrekt und wurde daher nicht verändert.");
            }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close(); /* Wenn der bw nicht geschlossen wird, bleibt die log-Datei leer */
        } catch (IOException e) {
            e.printStackTrace();
        }

        zaehler = zaehler + 1;
    }
}
