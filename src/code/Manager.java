package code;
import java.lang.*;
import java.io.*;
import java.util.*;

public class Manager {
    private static final List<Datensatz> list = new List<Datensatz>();

    public static void main(String[] args) {
        Datensatz daten1 = new Datensatz("test","test","test","test","test");
        Datensatz daten2 = new Datensatz("test","test","test2","test","test");
        list.insert(daten1);
        list.insert(daten2);

        String listString = (list.getContent()).getKursstufe();

        System.out.println(listString);
    }




}
