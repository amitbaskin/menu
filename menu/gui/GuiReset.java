package menu.gui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

abstract class GuiReset {
    private static final int COMBOBOX_INITIAL_INDEX = 0;
    private static void resetOrderMap(HashMap<String, Integer[]> orderMap){
        for (String key : orderMap.keySet()){
            int price = orderMap.get(key)[0];
            orderMap.put(key, new Integer[]{price, OrderUtils.NOT_SELECTED, OrderUtils.INITIAL_QUANTITY});
        }
    }

    private static void resetCheckBoxes(ArrayList<JCheckBox> boxes){
        for (JCheckBox box : boxes){
            box.setSelected(false);
        }
    }

    private static void resetComboBoxes(ArrayList<JComboBox<Integer>> boxes){
        for (JComboBox<Integer> box : boxes){
            box.setSelectedIndex(COMBOBOX_INITIAL_INDEX);
        }
    }

    static void resetGUi(Gui gui){
        resetOrderMap(gui.getOrderMap());
        resetCheckBoxes(gui.getCheckBoxes());
        resetComboBoxes(gui.getComboBoxes());
//        gui.resetTotalPrice();
    }
}