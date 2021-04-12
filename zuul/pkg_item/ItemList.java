package zuul.pkg_item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 * This class is used to represent a set of pkg_game items such as an inventory.
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
     * Creates and stores in the list a new pkg_item.
     *
     * @param pName        Name of the pkg_item.
     * @param pDescription Description of the pkg_item.
     * @param pWeight      Weight of the pkg_item.
     */
    public void addItem(final String pName, final String pDescription, final int pWeight) {
        this.addItem(new Item(pName, pDescription, pWeight));
    }

    /**
     * Adds an pkg_item in the list.
     *
     * @param pItem The pkg_item to be added.
     */
    public void addItem(final Item pItem) {
        this.aItems.put(pItem.getName(), pItem);
    }

    /**
     * Gets an pkg_item from the list.
     *
     * @param pName Name of the pkg_item to get.
     * @return The pkg_item retrieved.
     */
    public Item getItem(final String pName) {
        return this.aItems.get(pName);
    }

    /**
     * Removes an pkg_item from the list.
     * This method removes from the list the pkg_item with the name given in parameter.
     *
     * @param pName Name of the pkg_item to be removed.
     * @return The pkg_item removed.
     */
    public Item removeItem(final String pName) {
        return this.aItems.remove(pName);
    }

    /**
     * Tests if the list is empty.
     *
     * @return true if the list contains at least one pkg_item, false otherwise.
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
     * Gets the pkg_item list.
     *
     * @return The items list.
     */
    public ObservableMap<String, Item> getObservableItems() {
        return this.aItems;
    }
}
