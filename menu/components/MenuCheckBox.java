package menu.components;

import javax.swing.*;

/**
 * Check box for selecting an item in the menu
 */
public class MenuCheckBox extends JCheckBox {
    private static final String SEPARATOR = "\t";

    /**
     * Create a new checkbox
     * @param item The item
     */
    public MenuCheckBox(MenuItem item){
        super(String.format("%s%s%s", item.getName(), SEPARATOR, item.getPrice()));
    }
}