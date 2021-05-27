package menu.components;

import menu.gui.Gui;
import menu.gui.ShowOrder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OrderButtonListener implements ActionListener {
    Gui gui;

    OrderButtonListener(Gui gui){ this.gui = gui; }

    @Override
    public void actionPerformed(ActionEvent e) { ShowOrder.showOrder(gui); }
}