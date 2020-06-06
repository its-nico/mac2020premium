package code;
import java.util.regex.*;

public class Korrektur {

    /* Klasse führt automatisch alle Korrektur-Methoden auf einmal aus */
    public String autoKorrektur(String text){

        if (istIP(text)) {
            System.err.println("Es handelt sich hierbei um eine IP-Adresse. Bitte geben Sie eine MAC-Adresse ein!");
            return null;
        }

        if (istKorrekt(text)){
            return text;
        }
        else {
            return (o(leerzeichen(bindestriche(text))));
        }
    }

    /* Alle Os (groß- und kleingeschrieben) werden mit Nullen ersetzt */
    public String o(String text) {
        text = text.replace("o", "0");
        text = text.replace("O", "0");
        return text;
    }

    /* Alle Leerzeichen werden gelöscht */
    public String leerzeichen(String text) {
        text = text.replace(" ", "");
        return text;
    }

    /* Alle Bindestriche werden mit Doppelpunkten ersetzt */
    public String bindestriche(String text){
        text = text.replace("-",":");
        return text;
    }

    /* Überprüfung, ob es sich um eine IP-Adresse statt einer MAC-Adresse handelt */
    public boolean istIP(String text) {

        return (Pattern.matches("[^a-z^A-Z]", text)) && text.contains(".") && !text.contains(":") && !text.contains(" ");
    }

    /* Überprüfung, ob die MAC-Adresse korrekt ist */
    public boolean istKorrekt(String text) {

        return !text.contains("o") && !text.contains("O") && !text.contains("-") && !text.contains(" ") && !istIP(text);
    }
}
