package menu.gui;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.System.exit;

public abstract class ShowOrder {
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
        while (ConfirmClientDetails.confirmId(id, gui) == -1);
        return name + id;
    }

    private static void buildOrderMsg(StringBuilder str, Gui gui) {
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
                str.append(String.format(ITEM_PRICE_MSG, name, amount, price, sm));
            }
        }
//        str.append(String.format(TOTAL_PRICE_MSG, gui.getTotalOrderPrice()));
        str.append(String.format(TOTAL_PRICE_MSG, TOTAL_ORDER_PRICE));
    }

    private static JScrollPane getOrderScrollPane(String order) {
        JTextArea textArea = new JTextArea(ORDER_FRAME_SIZE, ORDER_FRAME_SIZE);
        textArea.setText(order);
        textArea.setEditable(false);
        return new JScrollPane(textArea);
    }

    private static int getOrderConfirmationSelection(String order, Gui gui) {
        JScrollPane orderScrollPane = getOrderScrollPane(order);
        return JOptionPane.showOptionDialog(gui.getFrame(), orderScrollPane, CONFIRMATION_TITLE,
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                new Object[]{CONFIRMATION_OPTION, CHANGE_OPTION, REVOKE_OPTION}, CONFIRMATION_OPTION);
    }

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

    private static void checkForNoOptionScenario(int selection, Gui gui) {
        if (selection == JOptionPane.CANCEL_OPTION) {
            GuiReset.resetGUi(gui);
        }
    }

    public static void showOrder(Gui gui) {
        StringBuilder str = new StringBuilder();
        buildOrderMsg(str, gui);
        String order = String.valueOf(str);
//        if (order.equals(String.format(TOTAL_PRICE_MSG, 0))) {
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