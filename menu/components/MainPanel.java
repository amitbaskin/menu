package menu.components;

import menu.gui.Gui;
import javax.swing.*;
import java.awt.*;

/**
 * The main panel of the menu
 */
public class MainPanel extends JPanel {
    private static final int FRAME_V_GAP = 30;
    private static final int ROWS_NUM = 3;
    private static final int COLS_NUM = 1;

    /**
     * Create the main panel
     * @param gui The gui associated with this main panel
     */
    public MainPanel(Gui gui){
        super();
        GridLayout mainPanelLayout = new GridLayout(ROWS_NUM, COLS_NUM);
        mainPanelLayout.setVgap(FRAME_V_GAP);
        setLayout(mainPanelLayout);
        add(new JScrollPane(new SectionPanel(gui, ItemType.MAIN)));
        add(new JScrollPane(new SectionPanel(gui, ItemType.STARTER)));
        add(new JScrollPane(new SectionPanel(gui, ItemType.DRINK)));
    }
}