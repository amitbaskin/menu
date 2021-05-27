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

public class Gui {
    private static final String MENU_FILE_NAME = "menu.txt";
    private static final int MAX_ITEM_AMOUNT = 17;
    private static final int FRAME_SIZE = 1500;
    private static Integer[] dishAmountsLst;
//    private int totalOrderPrice;
    private final JFrame frame;
    private final Menu menu;
    private final HashMap<String, Integer[]> orderMap;
    private final ArrayList<JCheckBox> checkBoxes;
    private final ArrayList<JComboBox<Integer>> comboBoxes;

//    int getTotalOrderPrice() { return totalOrderPrice; }
    ArrayList<JComboBox<Integer>> getComboBoxes() { return comboBoxes; }
    ArrayList<JCheckBox> getCheckBoxes() { return checkBoxes; }
    JFrame getFrame() { return frame; }
    Menu getMenu() { return menu; }
    void setDishAmountsLst(Integer[] dishAmountsLst) { Gui.dishAmountsLst = dishAmountsLst; }
//    void resetTotalPrice(){ totalOrderPrice = 0; }
//    public void incrementTotal(int inc){ totalOrderPrice += inc; }
//    public void decrementTotal(int dec){ totalOrderPrice -= dec; }
    public void addCheckBox(JCheckBox box){checkBoxes.add(box);}
    public void addComboBox(JComboBox<Integer> box){getComboBoxes().add(box);}
    public Integer[] getDishAmountsLst() { return dishAmountsLst; }
    public HashMap<String, Integer[]> getOrderMap() { return orderMap; }

    private void initializeDishAmounts(){
        setDishAmountsLst(new Integer[MAX_ITEM_AMOUNT]);
        Integer[] dishAmountsLst = getDishAmountsLst();
        for(int i=0; i<dishAmountsLst.length; i++){
            dishAmountsLst[i] = i+1;
        }
    }

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

    public Gui() throws FileNotFoundException {
        initializeDishAmounts();
//        totalOrderPrice = 0;
        menu = new Menu(new File(System.getProperty("user.dir"), MENU_FILE_NAME));
        orderMap = new HashMap<>();
        checkBoxes = new ArrayList<>();
        comboBoxes = new ArrayList<>();
        frame = new MenuFrame();
    }

    public void initialize(){
        frame.add(new MainPanel(this));
        frame.add(new ImageAndOrderBtnPanel(this));
        OrderUtils.initializeOrderMap(this);
        frame.setSize(FRAME_SIZE, FRAME_SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}