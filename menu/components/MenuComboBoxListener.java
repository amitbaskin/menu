package menu.components;

import menu.gui.Gui;
import menu.gui.OrderUtils;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class MenuComboBoxListener implements ItemListener {
    private final String name;
    private final JComboBox<Integer> box;
    private final Gui gui;

    MenuComboBoxListener(String name, JComboBox<Integer> box, Gui gui) {
        this.name = name;
        this.box = box;
        this.gui = gui;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Integer[] values = gui.getOrderMap().get(name);
        int price = values[OrderUtils.PRICE_INDEX];
        int newAmount = box.getSelectedIndex() + 1;
        int selection = values[OrderUtils.SELECTION_INDEX];
//        if (selection == OrderUtils.SELECTED) {
//            int oldAmount = values[OrderUtils.QUANTITY_INDEX];
//            int oldSm = oldAmount * price;
//            int newSm = newAmount * price;
//            gui.decrementTotal(oldSm);
//            gui.incrementTotal(newSm);
//        }
        gui.getOrderMap().put(name, new Integer[]{price, selection, newAmount});
    }
}