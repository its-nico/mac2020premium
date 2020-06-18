package code;

import java.awt.*;
import java.io.File;

public class FileOpener {

    public FileOpener(String pfad)  {
        File opener = new File (pfad);
        Desktop desk = Desktop.getDesktop();

        try{
            desk.open(opener);}
        catch (Exception ex){}

    }

   /* public static void main (String [] args) {
        FileOpener neu = new FileOpener();
    } */


}
