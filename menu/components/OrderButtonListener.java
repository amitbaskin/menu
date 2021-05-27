package menu.components;

import menu.gui.Gui;
import menu.gui.ShowOrder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for the order button
 */
public class OrderButtonListener implements ActionListener {
    Gui gui;

    /**
     * Create a new listener
     * @param gui The gui for the menu
     */
    public OrderButtonListener(Gui gui){ this.gui = gui; }

    /**
     * The action to perform when the button is clicked
     * @param e The event triggering this listener
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ShowOrder.showOrder(gui);
    }
}