package menu.components;

import menu.gui.Gui;
import javax.swing.*;
import java.awt.*;

/**
 * A button for submitting the order
 */
public class OrderButton extends JButton {
    private static final String BUTTON_NAME = "Order";
    private static final String FONT_NAME = "Arial";
    private static final int STYLE = Font.BOLD;
    private static final int SIZE = 25;
    private static final Color FOREGROUND_COLOR = Color.orange;
    private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;

    /**
     * Create a new button
     * @param gui The gui for the menu
     */
    public OrderButton(Gui gui){
        super(BUTTON_NAME);
        setFont(new Font(FONT_NAME, STYLE, SIZE));
        setForeground(FOREGROUND_COLOR);
        setBackground(BACKGROUND_COLOR);
        setOpaque(true);
        addActionListener(new OrderButtonListener(gui));
    }
}