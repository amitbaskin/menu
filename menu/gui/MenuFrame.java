package menu.gui;

import javax.swing.*;
import java.awt.*;

/**
 * The frame of the whole menu
 */
public class MenuFrame extends JFrame {
    private static final String MENU_TITLE = "The Best Restaurant Ever!   -   Menu";
    private static final int FRAME_V_GAP = 30;
    private static final int GRID_ROWS = 1;
    private static final int GRID_COLUMNS = 2;

    /**
     * Create a new frame
     */
    public MenuFrame(){
        super(MENU_TITLE);
        GridLayout frameLayout = new GridLayout(GRID_ROWS, GRID_COLUMNS);
        frameLayout.setVgap(FRAME_V_GAP);
        setLayout(frameLayout);
    }
}