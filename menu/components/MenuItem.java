package menu.components;

/**
 * An item in the menu
 */
public class MenuItem {
    private final ItemType type;
    private final String name;
    private final int price;

    /**
     * Create a new item
     * @param type The type of the item
     * @param name The name of the item
     * @param price The price of the item
     */
    public MenuItem(ItemType type, String name, int price){
        this.type = type;
        this.name = name;
        this.price = price;
    }

    /**
     * Getter for the price
     * @return The price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Getter for the name
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the type
     * @return The type
     */
    public ItemType getType() {
        return type;
    }
}