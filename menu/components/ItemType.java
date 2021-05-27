package menu.components;

/**
 * The menu is divided into sections by the types specified here
 */
public enum ItemType {
    MAIN("Main Dishes"), STARTER("Starters"), DRINK("Drinks");
    private final String type;

    /**
     * Create a new type
     * @param type The type
     */
    ItemType(String type) {
        this.type = type;
    }

    /**
     * Use this to specify the type in the menu
     * @return The string of the type
     */
    @Override
    public String toString() {
        return type;
    }
}