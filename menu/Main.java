package menu;

import menu.gui.Gui;
import javax.swing.*;
import java.io.FileNotFoundException;
import static java.lang.System.exit;

/**
 * The running class
 */
public abstract class Main {
    private static final String ERROR_MSG = "Menu file not found. Terminating program.";
    private static final String ERROR_TITLE = "ERROR";

    /**
     * The running method
     * @param args The arguments for this program (ignored)
     */
    public static void main(String[] args) {
        run();
    }

    /**
     * Running helper
     */
    private static void run(){
        try{
            Gui gui = new Gui();
            gui.initialize();
        }
        catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, ERROR_MSG, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
            exit(1);
        }
    }
}