package menu.components;

import menu.gui.Gui;
import menu.gui.OrderUtils;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Listener for menu item check box
 */
public class MenuCheckBoxListener implements ItemListener {
    String name;
    Gui gui;

    /**
     * Create a new listener
     * @param name The name of the item which the check box is associated with
     * @param gui The gui for the menu
     */
    public MenuCheckBoxListener(String name, Gui gui){
        super();
        this.name = name;
        this.gui = gui;
    }

    /**
     * The action to perform when the check box is selected/unselected
     * @param e The event trigerring this listener
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        int state = e.getStateChange();
        Integer[] values = gui.getOrderMap().get(name);
        int price = values[OrderUtils.PRICE_INDEX];
        int amount = values[OrderUtils.QUANTITY_INDEX];
        if (state == ItemEvent.SELECTED) {
            gui.getOrderMap().put(name, new Integer[]{price, OrderUtils.SELECTED, amount});
        }
        else {
            gui.getOrderMap().put(name, new Integer[]{price, OrderUtils.NOT_SELECTED, amount});
        }
    }
}