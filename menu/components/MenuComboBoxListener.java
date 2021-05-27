package menu.components;

import menu.gui.Gui;
import menu.gui.OrderUtils;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Combo box listener for combo box of menu item
 */
public class MenuComboBoxListener implements ItemListener {
    private final String name;
    private final JComboBox<Integer> box;
    private final Gui gui;

    /**
     * Create a new combo box listener
     * @param name The name of the item which this combo box listener is associated with
     * @param box The combo box
     * @param gui The gui for the menu
     */
    public MenuComboBoxListener(String name, JComboBox<Integer> box, Gui gui) {
        this.name = name;
        this.box = box;
        this.gui = gui;
    }

    /**
     * The action to perform when the combo box is used
     * @param e The event triggering this listener
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        Integer[] values = gui.getOrderMap().get(name);
        int price = values[OrderUtils.PRICE_INDEX];
        int newAmount = box.getSelectedIndex() + 1;
        int selection = values[OrderUtils.SELECTION_INDEX];
        gui.getOrderMap().put(name, new Integer[]{price, selection, newAmount});
    }
}