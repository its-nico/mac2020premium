package code;
import java.io.*;
import java.util.ArrayList;

public class Speichern {
    static String datnam = "savedList.ser";
    public static void abspeichern(ArrayList pList) {
        OutputStream fos = null;
        try {
            fos = new FileOutputStream(datnam);
            ObjectOutputStream stream = new ObjectOutputStream(fos);
            stream.writeObject(pList);
            stream.close();
        } catch (IOException ioex) {
            System.err.println("Fehler beim Schreiben der Liste (mit referenzierten Datensätzen) aufgetreten.");
            ioex.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList laden () {
            ArrayList liste = null;
            InputStream fis = null;
            try {
                fis = new FileInputStream(datnam);
                ObjectInputStream stream = new ObjectInputStream(fis);
                liste = (ArrayList) stream.readObject();
                stream.close();
                return liste;
            } catch (ClassNotFoundException cnfex) {
                System.err.println("Die Klasse des geladenen Objekts konnte nicht gefunden werden.");
            } catch (IOException ioex) {
                System.err.println("Das Objekt konnte nicht geladen werden.");
                ioex.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (Exception e) {
                    return liste = null;
                }
            return liste;
            }
        }
    }