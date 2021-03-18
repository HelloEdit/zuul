import java.util.HashMap;

public class ItemList {
    /**
     * The items of the list.
     */
    private final HashMap<String, Item> aItems;

    /**
     * Creates a new list of items.
     */
    public ItemList() {
        this.aItems = new HashMap<>();
    }

    /**
     * Creates and stores in the list a new item.
     *
     * @param pName name of the item.
     * @param pDescription description of the item.
     * @param pWeight weight of the item.
     */
    public void addItem(final String pName, final String pDescription, final int pWeight) {
        this.aItems.put(pName, new Item(pName, pDescription, pWeight));
    }

    /**
     * Adds an item in the list.
     *
     * @param pItem the item to be added.
     */
    public void addItem(final Item pItem) {
        this.aItems.put(pItem.getName(), pItem);
    }

    /**
     * Gets an item from the list.
     *
     * @param pName name of the item to get.
     * @return the acquired item.
     */
    public Item getItem(final String pName) {
        return this.aItems.get(pName);
    }

    /**
     * Removes an item from the list.
     * This method removes from the list the item with the name given in parameter.
     *
     * @param pName name of the item to be removed.
     * @return the item removed.
     */
    public Item removeItem(final String pName) {
        return this.aItems.remove(pName);
    }

    /**
     * Removes an item from the list.
     * This method removes from the list the item corresponding to the reference given in parameter
     *
     * @param pItem item to be removed
     * @return the item removed.
     */
    public Item removeItem(final Item pItem) {
        return this.aItems.remove(pItem);
    }

    /**
     * Returns true if this item list contains no items.
     *
     * @return true if the list contains at least one item, false otherwise.
     */
    public boolean isEmpty() {
        return this.aItems.isEmpty();
    }

    /**
     * Returns a string of all items' name joined together with ", ".
     *
     * @return string of the names of all items.
     */
    public String getAllNames() {
        return String.join(", ", this.aItems.keySet());
    }
}
