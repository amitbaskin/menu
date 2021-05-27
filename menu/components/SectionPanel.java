package menu.components;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;

import menu.gui.Gui;

class SectionPanel extends JPanel {
    private static final String FONT_NAME = "Arial";
    private static final int FONT_STYLE = Font.ITALIC;
    private static final int FONT_SIZE = 28;
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final int COLUMNS_AMOUNT = 2;

    SectionPanel(Gui gui, ItemType type){
        ArrayList<menu.components.MenuItem> items = gui.getMenuItems(type);
        setLayout(new GridLayout(items.size(), COLUMNS_AMOUNT));
        TitledBorder border = BorderFactory.createTitledBorder(type.toString());
        border.setTitleFont(new Font(FONT_NAME, FONT_STYLE, FONT_SIZE));
        setBorder(border);
        setBackground(BACKGROUND_COLOR);

        for (menu.components.MenuItem item : items){
            add(getMenuCheckBox(item, gui));
            add(getMenuComboBox(item.getName(), gui));
        }
    }

    private static JCheckBox getMenuCheckBox(MenuItem item, Gui gui) {
        JCheckBox box = new MenuCheckBox(item);
        gui.addCheckBox(box);
        box.addItemListener(new MenuCheckBoxListener(item.getName(), gui));
        return box;
    }

    private static JComboBox<Integer> getMenuComboBox(String name, Gui gui){
        JComboBox<Integer> box = new JComboBox<>(gui.getDishAmountsLst());
        gui.addComboBox(box);
        box.addItemListener(new MenuComboBoxListener(name, box, gui));
        return box;
    }
}