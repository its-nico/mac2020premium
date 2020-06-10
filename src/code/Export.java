package code;

import java.io.*;

public class Export {

    public void export() {
        String path = "export.txt";
        try {
            File file = new File(path);
           if (file.createNewFile()) {
               System.out.println("File created");
            } else {
               System.out.println("File could not be created");
           }
        } catch (IOException e) {
            System.out.println("File could not be created");
        }

        FileReader fr = null;
        try {
            fr = new FileReader(path);
        } catch (FileNotFoundException e) {
            System.out.println("The file could not be found by FileReader (FileNotFoundException)");
        }
        BufferedReader br = new BufferedReader(fr);
    }
}
