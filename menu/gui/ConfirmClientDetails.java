package menu.gui;

import javax.swing.*;

abstract class ConfirmClientDetails {
    static final int ID_LEN = 9;
    static final int NAME_MIN_LEN = 2;
    private static final String INVALID_ID_MSG = "Invalid id!";
    private static final String INVALID_NAME_MSG = "Invalid Name!";
    private static final String MSG_TITLE = "ERROR";

    static int confirmId(String id, Gui gui) {
        if (id == null) return 0;
        if (id.length() != ID_LEN) {
            JOptionPane.showMessageDialog(gui.getFrame(), INVALID_ID_MSG, MSG_TITLE,
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        for (int i = 0; i < id.length(); i++) {
            try {
                Integer.parseInt(String.valueOf(id.charAt(i)));
            }
            catch (NumberFormatException e) {
                return -1;
            }
        }
        return 1;
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