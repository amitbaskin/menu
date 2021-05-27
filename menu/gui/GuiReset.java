package menu.gui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An abstract class for resetting the gui
 */
public abstract class GuiReset {
    private static final int COMBOBOX_INITIAL_INDEX = 0;

    /**
     * Reset the map of the order
     * @param orderMap The map to reset
     */
    private static void resetOrderMap(HashMap<String, Integer[]> orderMap){
        for (String key : orderMap.keySet()){
            int price = orderMap.get(key)[0];
            orderMap.put(key, new Integer[]{price, OrderUtils.NOT_SELECTED, OrderUtils.INITIAL_QUANTITY});
        }
    }

    /**
     * Reset the check boxes
     * @param boxes The check boxes to reset
     */
    private static void resetCheckBoxes(ArrayList<JCheckBox> boxes){
        for (JCheckBox box : boxes){
            box.setSelected(false);
        }
    }

    /**
     * Reset the combo boxes
     * @param boxes The combo boxes to reset
     */
    private static void resetComboBoxes(ArrayList<JComboBox<Integer>> boxes){
        for (JComboBox<Integer> box : boxes){
            box.setSelectedIndex(COMBOBOX_INITIAL_INDEX);
        }
    }

    /**
     * Reset the gui itself
     * @param gui The gui to reset
     */
    static void resetGUi(Gui gui){
        resetOrderMap(gui.getOrderMap());
        resetCheckBoxes(gui.getCheckBoxes());
        resetComboBoxes(gui.getComboBoxes());
    }
}