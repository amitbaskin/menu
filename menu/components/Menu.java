package menu.components;

import menu.ItemTypeException;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * The logical menu
 */
public class Menu {
    private static final String ITEM_TYPE_EXCEPTION_MSG =
            "The menu file contains illegal item type at line %d.";
    private static final String ILLEGAL_PRICE_FORMAT =
            "The menu file contains illegal price format at line %d.";
    private static final String ERROR_TITLE_MSG = "ERROR";
    private static final String EMPTY = "ERROR";
    private static final String MAIN_TYPE = "Main";
    private static final String STARTER_TYPE = "Starter";
    private static final String DRINK_TYPE = "Drink";
    private final ArrayList<MenuItem> mainDishes = new ArrayList<>();
    private final ArrayList<MenuItem> starters = new ArrayList<>();
    private final ArrayList<MenuItem> drinks = new ArrayList<>();

    /**
     * Get the drinks
     * @return The drinks
     */
    public ArrayList<MenuItem> getDrinks() {
        return drinks;
    }

    /**
     * Get the starters
     * @return The starters
     */
    public ArrayList<MenuItem> getStarters() {
        return starters;
    }

    /**
     * Get the main dishes
     * @return The main dishes
     */
    public ArrayList<MenuItem> getMainDishes() {
        return mainDishes;
    }

    /**
     * Create a new menu
     * @param file The file to generate the menu from
     * @throws FileNotFoundException In case the file specified is not to be found
     */
    public Menu(File file) throws FileNotFoundException {
        int counter = 0;
        Scanner input = new Scanner(file);
        MenuItem menuItem;
        while (input.hasNext()) {
            try{
                counter += 3;
                menuItem = getMenuItem(input);
                addItem(menuItem);
            }
            catch (ItemTypeException e) {
                itemTypeExceptionDialog(counter);
            }
            catch (NumberFormatException e) {
                numberFormatExceptionDialog(counter);
            }
        }
    }

    /**
     * Notifying of a problem where expecting a price but getting something else instead
     * @param lineNum The number of the line where the error occurred
     */
    private void numberFormatExceptionDialog(int lineNum){
        JOptionPane.showMessageDialog(null, String.format(ILLEGAL_PRICE_FORMAT, lineNum),
                ERROR_TITLE_MSG, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Notifying of a problem where expecting for an item type of one of the possible item types, but
     * getting something else instead
     * @param lineNum The number of the line where the error occurred
     */
    private void itemTypeExceptionDialog(int lineNum){
        JOptionPane.showMessageDialog(null, String.format(ITEM_TYPE_EXCEPTION_MSG, lineNum-1),
                ERROR_TITLE_MSG, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Get the next line in the file
     * @param scanner The scanner to get the next line from
     * @return The next line or an empty string in case of EOF
     */
    private String getLine(Scanner scanner){
        String line = scanner.nextLine();
        while (line.trim().length() == 0) {
            if (scanner.hasNext()) line = scanner.nextLine();
            else return "";
        }
        return line;
    }

    /**
     * Get hard type of item type from an input string
     * @param type The input string
     * @return The item type
     * @throws ItemTypeException In case the input string is not one of the possible item types
     */
    private ItemType getItemType(String type) throws ItemTypeException {
        switch (type) {
            case MAIN_TYPE:
                return ItemType.MAIN;
            case STARTER_TYPE:
                return ItemType.STARTER;
            case DRINK_TYPE:
                return ItemType.DRINK;
            default:
                throw new ItemTypeException();
        }
    }

    /**
     * Add item to menu
     * @param item The item to add
     */
    private void addItem(MenuItem item){
        ItemType type = item.getType();
        if (type == ItemType.MAIN) mainDishes.add(item);
        else if (type == ItemType.STARTER) starters.add(item);
        else drinks.add(item);
    }

    /**
     * Construct an item
     * @param scanner The scanner to get the next line from
     * @return The item constructed from the retrieved line
     * @throws ItemTypeException In case of expecting an item type but getting something else instead
     * @throws NumberFormatException In case of expecting a price but getting something else instead
     */
    private MenuItem getMenuItem(Scanner scanner) throws ItemTypeException, NumberFormatException {
        String name = getLine(scanner);
        ItemType type;
        try{
            type = getItemType(getLine(scanner));
        }
        catch (ItemTypeException e){
            getLine(scanner); //get rid of the price coming next line
            throw new ItemTypeException();
        }
        int price = Integer.parseInt(getLine(scanner));
        return new MenuItem(type, name, price);
    }
}