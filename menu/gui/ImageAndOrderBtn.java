package menu.gui;

import menu.components.OrderButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

class ImageAndOrderBtn extends JPanel {
    private static final String IMAGE_FILE_NAME = "food.jpg";

    ImageAndOrderBtn(Gui gui){
        super();
        JLabel imageLabel = new JLabel(new ImageIcon(Paths.get(System.getProperty("user.dir"),
                IMAGE_FILE_NAME).toString()));
        setLayout(new BorderLayout());
        add(imageLabel, BorderLayout.CENTER);
        add(new OrderButton(gui), BorderLayout.EAST);
    }
}