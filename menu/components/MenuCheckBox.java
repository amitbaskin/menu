package menu.components;

import javax.swing.*;

class MenuCheckBox extends JCheckBox {
    private static final String SEPARATOR = "\t";
    MenuCheckBox(String name, MenuItem item){
        super(String.format("%s%s%s", name, SEPARATOR, item.getPrice()));
    }
}