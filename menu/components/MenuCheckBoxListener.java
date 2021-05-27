package menu.components;

import menu.gui.Gui;
import menu.gui.OrderUtils;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class MenuCheckBoxListener implements ItemListener {
    String name;
    Gui gui;

    MenuCheckBoxListener(String name, Gui gui){
        super();
        this.name = name;
        this.gui = gui;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        int state = e.getStateChange();
        Integer[] values = gui.getOrderMap().get(name);
        int price = values[OrderUtils.PRICE_INDEX];
        int amount = values[OrderUtils.QUANTITY_INDEX];
//        int sm = amount * price;
        if (state == ItemEvent.SELECTED) {
            gui.getOrderMap().put(name, new Integer[]{price, OrderUtils.SELECTED, amount});
//            gui.incrementTotal(sm);
        }
        else {
            gui.getOrderMap().put(name, new Integer[]{price, OrderUtils.NOT_SELECTED, amount});
//            gui.decrementTotal(sm);
        }
    }
}