package menu.components;

public enum ItemType {
    MAIN("Main Dishes"), STARTER("Starters"), DRINK("Drinks");
    private final String title;

    ItemType(String title) { this.title = title; }

    @Override
    public String toString() { return title; }
}