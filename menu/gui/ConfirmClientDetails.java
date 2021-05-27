package menu.gui;

import javax.swing.*;

/**
 * An abstract class for confirming the details provided by the user
 */
public abstract class ConfirmClientDetails {
    static final int ID_LEN = 9;
    static final int NAME_MIN_LEN = 2;
    private static final String INVALID_ID_MSG = "Invalid id!";
    private static final String INVALID_NAME_MSG = "Invalid Name!";
    private static final String MSG_TITLE = "ERROR";

    public enum ConfirmationCode {GOT_NULL, ERROR, CONFIRMED}

    /**
     * Confirm the client's id
     * @param id The id of the client
     * @param gui The gui for the menu
     * @return The confirmation code
     */
    public static ConfirmationCode confirmId(String id, Gui gui) {
        if (id == null) return ConfirmationCode.GOT_NULL;
        if (id.length() != ID_LEN) {
            JOptionPane.showMessageDialog(gui.getFrame(), INVALID_ID_MSG, MSG_TITLE,
                    JOptionPane.ERROR_MESSAGE);
            return ConfirmationCode.ERROR;
        }
        for (int i = 0; i < id.length(); i++) {
            try {
                Integer.parseInt(String.valueOf(id.charAt(i)));
            }
            catch (NumberFormatException e) {
                return ConfirmationCode.ERROR;
            }
        }
        return ConfirmationCode.CONFIRMED;
    }

    static int confirmName(String name, Gui gui) {
        if (name == null) return 0;
        if (name.length() < NAME_MIN_LEN) {
            JOptionPane.showMessageDialog(gui.getFrame(), INVALID_NAME_MSG, MSG_TITLE,
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        char cur;
        for (int i = 0; i < name.length(); i++) {
            cur = name.charAt(i);
            if (!Character.isAlphabetic(cur)) {
                JOptionPane.showMessageDialog(gui.getFrame(), INVALID_NAME_MSG, MSG_TITLE,
                        JOptionPane.ERROR_MESSAGE);
                return -1;
            }
        }
        return 1;
    }
}