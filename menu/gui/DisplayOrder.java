package menu.gui;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.exit;

/**
 * An abstract class for displaying the order to the user
 */
public abstract class DisplayOrder {
    private static final String NAME_REQUEST_MSG = String.format("Please enter your name, only alphabetic " +
        "characters, at least %d:", ConfirmClientDetails.NAME_MIN_LEN);
    private static final String ID_REQUEST_MSG = String.format("Please enter your id number, exactly %d " +
            "digits:", ConfirmClientDetails.ID_LEN);
    private static final String ITEM_PRICE_MSG = "%s: %d X %d = %d Shekels\n\n";
    private static final String TOTAL_PRICE_MSG = "Total: %d Shekels";
    private static final String NO_SELECTION_MSG = "You didn't select any thing...";
    private static final String ERROR_MSG = "Unable to write your order. Terminating program.";
    private static final String ERROR_TITLE = "ERROR";
    private static final String EMPTY = "";
    private static final String NEW_FILE_SUFFIX = ".txt";
    private static final String CONFIRMATION_TITLE = "Confirm Order";
    private static final String CONFIRMATION_OPTION = "Confirm";
    private static final String CHANGE_OPTION = "Change";
    private static final String REVOKE_OPTION = "Revoke";
    private static final int ORDER_FRAME_SIZE = 20;
    private static int TOTAL_ORDER_PRICE = 0;

    /**
     * Getting the user's details
     * @param gui The gui for the menu
     * @return The name of the file in which to save the order in
     */
    private static String getClientDetails(Gui gui) {
        String name;
        do {
            name = JOptionPane.showInputDialog(NAME_REQUEST_MSG);
            if (name == null) return EMPTY;
        }
        while (ConfirmClientDetails.confirmName(name, gui) == -1);
        String id;
        do {
            id = JOptionPane.showInputDialog(ID_REQUEST_MSG);
            if (id == null) return EMPTY;
        }
        while (ConfirmClientDetails.confirmId(id, gui) == ConfirmClientDetails.ConfirmationCode.ERROR);
        return name + id;
    }

    /**
     * Constructs the message to display to the user with the order details
     * @param stringBuilder The sting-builder to construct the message in
     * @param gui The gui for the menu
     */
    private static void ConstructOrderMsg(StringBuilder stringBuilder, Gui gui) {
        Integer[] values;
        int amount;
        int price;
        int sm;
        TOTAL_ORDER_PRICE = 0;
        for (String name : gui.getOrderMap().keySet()) {
            values = gui.getOrderMap().get(name);
            if (values[OrderUtils.SELECTION_INDEX] == OrderUtils.SELECTED) {
                amount = values[OrderUtils.QUANTITY_INDEX];
                price = values[OrderUtils.PRICE_INDEX];
                sm = amount * price;
                TOTAL_ORDER_PRICE += sm;
                stringBuilder.append(String.format(ITEM_PRICE_MSG, name, amount, price, sm));
            }
        }
        stringBuilder.append(String.format(TOTAL_PRICE_MSG, TOTAL_ORDER_PRICE));
    }

    /**
     * Gets a scrolling pane for the order display
     * @param order The order
     * @return The scrolling pane
     */
    private static JScrollPane getOrderScrollPane(String order) {
        JTextArea textArea = new JTextArea(ORDER_FRAME_SIZE, ORDER_FRAME_SIZE);
        textArea.setText(order);
        textArea.setEditable(false);
        return new JScrollPane(textArea);
    }

    /**
     * Get the selection from the user regarding the confirmation of his order
     * @param order The order
     * @param gui The gui for the menu
     * @return The selection
     */
    private static int getOrderConfirmationSelection(String order, Gui gui) {
        JScrollPane orderScrollPane = getOrderScrollPane(order);
        return JOptionPane.showOptionDialog(gui.getFrame(), orderScrollPane, CONFIRMATION_TITLE,
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                new Object[]{CONFIRMATION_OPTION, CHANGE_OPTION, REVOKE_OPTION}, CONFIRMATION_OPTION);
    }

    /**
     * Handles the scenario where the user selects YES during confirmation
     * @param selection The selection of the user
     * @param order The order
     * @param gui The gui for the menu
     * @throws IOException In case there's a problem creating the new file
     */
    private static void checkForYesOptionScenario(int selection, String order, Gui gui) throws IOException {
        if (selection == JOptionPane.YES_OPTION) {
            String fileName = getClientDetails(gui);
            if (fileName.equals(EMPTY)) return;
            new File(fileName + NEW_FILE_SUFFIX);
            FileWriter myWriter = new FileWriter(fileName + NEW_FILE_SUFFIX);
            myWriter.write(order);
            myWriter.close();
            GuiReset.resetGUi(gui);
        }
    }

    /**
     * Handles the scenario where the user selects NO during confirmation
     * @param selection The user's selection
     * @param gui The gui for the menu
     */
    private static void checkForNoOptionScenario(int selection, Gui gui) {
        if (selection == JOptionPane.CANCEL_OPTION) {
            GuiReset.resetGUi(gui);
        }
    }

    /**
     * Display the order
     * @param gui The gui for the menu
     */
    public static void displayOrder(Gui gui) {
        StringBuilder str = new StringBuilder();
        ConstructOrderMsg(str, gui);
        String order = String.valueOf(str);
        if (TOTAL_ORDER_PRICE == 0) {
            JOptionPane.showMessageDialog(gui.getFrame(), NO_SELECTION_MSG);
            return;
        }
        int selection = getOrderConfirmationSelection(order, gui);
        try{
            checkForYesOptionScenario(selection, order, gui);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, ERROR_MSG, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
            exit(1);
        }
        checkForNoOptionScenario(selection, gui);
    }
}