package menu.gui;

import menu.components.ItemType;
import menu.components.MainPanel;
import menu.components.Menu;
import menu.components.MenuItem;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileNotFoundException;

/**
 * The gui for the menu
 */
public class Gui {
    private static final String MENU_FILE_NAME = "menu.txt";
    private static final int MAX_ITEM_AMOUNT = 17;
    private static final int FRAME_SIZE = 1500;
    private static Integer[] dishAmountsLst;
    private final JFrame frame;
    private final Menu menu;
    private final HashMap<String, Integer[]> orderMap;
    private final ArrayList<JCheckBox> checkBoxes;
    private final ArrayList<JComboBox<Integer>> comboBoxes;

    /**
     * Create a new gui
     * @throws FileNotFoundException In case the file to generate the menu from is not to be found
     */
    public Gui() throws FileNotFoundException {
        initializeDishAmounts();
        menu = new Menu(new File(System.getProperty("user.dir"), MENU_FILE_NAME));
        orderMap = new HashMap<>();
        checkBoxes = new ArrayList<>();
        comboBoxes = new ArrayList<>();
        frame = new MenuFrame();
    }

    /**
     * Get the combo boxes
     * @return The combo boxes
     */
    public ArrayList<JComboBox<Integer>> getComboBoxes() {
        return comboBoxes;
    }

    /**
     * Get the check boxes
     * @return The check boxes
     */
    public ArrayList<JCheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    /**
     * Get the frame
     * @return The frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Get the menu
     * @return The menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Set the amounts of the dishes
     * @param dishAmountsLst The amounts list to set
     */
    public void setDishAmountsLst(Integer[] dishAmountsLst) {
        Gui.dishAmountsLst = dishAmountsLst;
    }

    /**
     * Add a check box
     * @param box The check box to add
     */
    public void addCheckBox(JCheckBox box) {
        checkBoxes.add(box);
    }

    /**
     * Add a combo box
     * @param box The combo box to add
     */
    public void addComboBox(JComboBox<Integer> box) {
        getComboBoxes().add(box);
    }

    /**
     * Get the list of dishes amounts
     * @return The list
     */
    public Integer[] getDishAmountsLst() {
        return dishAmountsLst;
    }

    /**
     * Get the map of the order
     * @return The map
     */
    public HashMap<String, Integer[]> getOrderMap() {
        return orderMap;
    }

    /**
     * Initialize the amounts of the dishes
     */
    private void initializeDishAmounts(){
        setDishAmountsLst(new Integer[MAX_ITEM_AMOUNT]);
        Integer[] dishAmountsLst = getDishAmountsLst();
        for(int i=0; i<dishAmountsLst.length; i++){
            dishAmountsLst[i] = i+1;
        }
    }

    /**
     * Get the items in the menu by type
     * @param type The type to get by
     * @return The items by the input type
     */
    public ArrayList<MenuItem> getMenuItems(ItemType type){
        switch (type){
            case MAIN:
                return menu.getMainDishes();
            case STARTER:
                return menu.getStarters();
            case DRINK:
                return menu.getDrinks();
            default:
                return null;
        }
    }

    /**
     * Initialize the gui
     */
    public void initialize(){
        frame.add(new MainPanel(this));
        frame.add(new ImageAndOrderBtnPanel(this));
        OrderUtils.initializeOrderMap(this);
        frame.setSize(FRAME_SIZE, FRAME_SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}