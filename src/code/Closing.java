package code;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Closing extends JFrame{

    public Closing(){
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent ef) { //Window Listener reagiert, sonbald das Programm geschlossen wird
                int result2 = JOptionPane.showConfirmDialog(null,
                        "Möchten Sie vor dem Beenden speichern?",
                        "Beenden bestätigen",
                        JOptionPane.YES_NO_OPTION);

                switch (result2) {
                    case JOptionPane.YES_OPTION:

                    case JOptionPane.NO_OPTION:
                }
            }
        });
    }

    public static void close(Manager manager){
        int result = JOptionPane.showConfirmDialog(null,
                "Möchten Sie vor dem Beenden speichern?",
                "Beenden bestätigen",
                JOptionPane.YES_NO_OPTION);

        switch (result) {
            case JOptionPane.YES_OPTION: //Aktionen bei Klicken auf den "Ja-Button"
                manager.speichern(); //vor dem schließen wird gespeichert
                System.exit(0);
            case JOptionPane.NO_OPTION:
                System.exit(0);
                // e.getWindow().dispose();

        }
    }
}