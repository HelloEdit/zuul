package zuul.pkg_item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 * This class is used to represent a set of game items such as an inventory.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class ItemList {
    /**
     * Items of the list.
     */
    private final ObservableMap<String, Item> aItems;

    /**
     * Creates a new list of items.
     */
    public ItemList() {
        // I made the choice here to use an Observable HashMap from
        // JavaFX in order to be able to watch the items in the map.
        this.aItems = FXCollections.observableHashMap();
    }

    /**
     * Creates and stores in the list a new item.
     *
     * @param pName        Name of the item.
     * @param pDescription Description of the item.
     * @param pWeight      Weight of the item.
     */
    public void addItem(final String pName, final String pDescription, final int pWeight) {
        this.addItem(new Item(pName, pDescription, pWeight));
    }

    /**
     * Adds an item in the list.
     *
     * @param pItem The item to be added.
     */
    public void addItem(final Item pItem) {
        this.aItems.put(pItem.getName(), pItem);
    }

    /**
     * Gets an item from the list.
     *
     * @param pName Name of the item to get.
     * @return The item retrieved.
     */
    public Item getItem(final String pName) {
        return this.aItems.get(pName);
    }

    /**
     * Removes an item from the list.
     * This method removes from the list the item with the name given in parameter.
     *
     * @param pKey Name of the item to be removed.
     * @return The item removed.
     */
    public Item removeItem(final String pKey) {
        return this.aItems.remove(pKey);
    }

    /**
     * Tests if the list is empty.
     *
     * @return true if the list contains at least one item, false otherwise.
     */
    public boolean isEmpty() {
        return this.aItems.isEmpty();
    }

    /**
     * Returns a string of all items' name joined together with ", ".
     *
     * @return The string of the names joined together.
     */
    public String getItemsNames() {
        return String.join(", ", this.aItems.keySet());
    }

    /**
     * Gets the item list.
     *
     * @return The items list.
     */
    public ObservableMap<String, Item> getObservableItems() {
        return this.aItems;
    }
}
