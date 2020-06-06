package code;

public class Korrektur {

 //   public static void main(String[] args) {
 //       nullen("aa");
 //   }

    public String nullen(String text) {
        text = text.replace("o", "0");
        text = text.replace("O", "0");
        return text;
    }

    public String leerzeichen(String text) {
        text = text.replace(" ", "");
        return text;
    }

    public String bindestriche(String text){
        text = text.replace("-",":");
        return text;
    }

   /* public String istIP(String text) {

    }

    public boolean istKorrekt(String text) {

    } */
}
