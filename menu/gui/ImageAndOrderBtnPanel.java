package menu.gui;

import menu.components.OrderButton;
import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

/**
 * The panel containing the image and the order button
 */
public class ImageAndOrderBtnPanel extends JPanel {
    private static final String IMAGE_FILE_NAME = "food.jpg";

    /**
     * Create a new panel
     * @param gui The gui associated with this panel
     */
    public ImageAndOrderBtnPanel(Gui gui){
        super();
        JLabel imageLabel = new JLabel(new ImageIcon(Paths.get(System.getProperty("user.dir"),
                IMAGE_FILE_NAME).toString()));
        setLayout(new BorderLayout());
        add(imageLabel, BorderLayout.CENTER);
        add(new OrderButton(gui), BorderLayout.EAST);
    }
}