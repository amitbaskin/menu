package menu.gui;

import menu.components.Menu;
import menu.components.MenuItem;
import java.util.ArrayList;

/**
 * Utilities for making an order
 */
public abstract class OrderUtils {
    public static final int NOT_SELECTED = 0;
    public static final int SELECTED = 1;
    public static final int PRICE_INDEX = 0;
    public static final int SELECTION_INDEX = 1;
    public static final int QUANTITY_INDEX = 2;
    public static final int INITIAL_QUANTITY = 1;

    /**
     * Insert the given items into the order maps
     * @param items The items to insert
     * @param gui The gui of the menu
     */
    private static void putArrayLstToMaps(ArrayList<MenuItem> items, Gui gui){
        for (MenuItem item : items){
            String name = item.getName();
            int price = item.getPrice();
            gui.getOrderMap().put(name, new Integer[]{price, NOT_SELECTED, INITIAL_QUANTITY});
        }
    }

    /**
     * Initialize the order maps
     * @param gui The gui of the menu
     */
    public static void initializeOrderMap(Gui gui){
        Menu menu = gui.getMenu();
        putArrayLstToMaps(menu.getMainDishes(), gui);
        putArrayLstToMaps(menu.getStarters(), gui);
        putArrayLstToMaps(menu.getDrinks(), gui);
    }
}