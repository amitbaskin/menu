package menu.components;

import menu.ItemTypeException;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

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

    public ArrayList<MenuItem> getDrinks() { return drinks; }
    public ArrayList<MenuItem> getStarters() { return starters; }
    public ArrayList<MenuItem> getMainDishes() { return mainDishes; }

    public Menu(File file) throws FileNotFoundException {
        int counter = 0;
        Scanner input = new Scanner(file);
        MenuItem menuItem;
        while (input.hasNext()) {
            try{
                counter += 3;
                menuItem = getMenuItem(input);
//                if (menuItem == null) break;
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

    private void numberFormatExceptionDialog(int counter){
        JOptionPane.showMessageDialog(null, String.format(ILLEGAL_PRICE_FORMAT, counter),
                ERROR_TITLE_MSG, JOptionPane.ERROR_MESSAGE);
    }

    private void itemTypeExceptionDialog(int counter){
        JOptionPane.showMessageDialog(null, String.format(ITEM_TYPE_EXCEPTION_MSG, counter-1),
                ERROR_TITLE_MSG, JOptionPane.ERROR_MESSAGE);
    }

    private String getLine(Scanner input){
        String line = input.nextLine();
        while (line.trim().length() == 0) {
            if(input.hasNext()) line = input.nextLine();
            else return "";
        }
        return line;
    }

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

    private void addItem(MenuItem item){
        ItemType type = item.getType();
        if (type == ItemType.MAIN) mainDishes.add(item);
        else if (type == ItemType.STARTER) starters.add(item);
        else drinks.add(item);
    }

    private MenuItem getMenuItem(Scanner input) throws ItemTypeException, NumberFormatException {
        String name = getLine(input);
//        if (name.equals(EMPTY)) return null;
        ItemType type;
        try{ type = getItemType(getLine(input)); }
        catch (ItemTypeException e){
            getLine(input); //get rid of the price coming next line
            throw new ItemTypeException();
        }
        int price = Integer.parseInt(getLine(input));
        return new MenuItem(type, name, price);
    }
}