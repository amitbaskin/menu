package menu.components;

public class MenuItem {
    private final ItemType type;
    private final String name;
    private final int price;

    public int getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }
    public ItemType getType() {
        return type;
    }

    public MenuItem(ItemType type, String name, int price){
        this.type = type;
        this.name = name;
        this.price = price;
    }
}
