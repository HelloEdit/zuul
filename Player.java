import java.util.HashMap;
import java.util.Stack;

/**
 * This class represents a player of the game Zuul.
 */
public class Player {
    public final static int MAX_WEIGHT = 100;

    private int aWeight;

    /**
     * The history of the rooms were the player was.
     *
     * @see Stack
     */
    private final Stack<Room> aPreviousRooms;

    /**
     * The player inventory.
     *
     * @see HashMap
     */
    private final ItemList aInventory;

    /**
     * The current room where the player is.
     */
    private Room aCurrentRoom;

    /**
     * Create a new player.
     */
    public Player() {
        this.aPreviousRooms = new Stack<>();
        this.aInventory = new ItemList();
        this.aWeight = 0;
    }

    /**
     * Add the current room to the history.
     */
    public void saveCurrentRoom() {
        this.aPreviousRooms.push(this.aCurrentRoom);
    }

    /**
     * Gets the current room were the player is.
     *
     * @return the current room.
     */
    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    }

    /**
     * Sets the new current room.
     *
     * @param pRoom the new current room.
     */
    public void setCurrentRoom(final Room pRoom) {
        this.aCurrentRoom = pRoom;
    }

    /**
     * Gets the previous room were the player was.
     *
     * @return the previous room.
     */
    public Room getPreviousRoom() {
        if (this.aPreviousRooms.isEmpty()) return null;
        return this.aPreviousRooms.pop();
    }

    /**
     * Takes an item from the current room and add it to the inventory.
     *
     * @param pItemName name of the item to be taken.
     * @return the item just added.
     * @throws CannotManageItemException if the item to be taken doesn't exist in the room.
     */
    public Item takeItem(final String pItemName) throws CannotManageItemException {
        if (this.aCurrentRoom == null)
            throw new CannotManageItemException("Aucune salle dans laquelle prendre l'objet.");

        // get the item from the current room...
        Item vItem = this.aCurrentRoom.getItem(pItemName);
        if (vItem == null) throw new CannotManageItemException("L'objet à prendre n'existe pas dans la salle.");

        // if the difference is less than zero, the total weight of the items exceeds the allowed limit
        if (this.aWeight + vItem.getWeight() > Player.MAX_WEIGHT) {
            throw new CannotManageItemException("Vous ne pouvez pas prendre cet objet, votre inventaire est trop lourd !");
        }

        // then we definitely remove it from the room
        this.aWeight += vItem.getWeight();
        this.aCurrentRoom.removeItem(vItem);

        // ...and add it to the player's inventory
        this.aInventory.addItem(vItem);

        return vItem;
    }

    /**
     * Drops an item in the current room.
     *
     * @param pItemName the item to be dropped.
     * @return the item that has been dropped.
     * @throws CannotManageItemException if the requested object cannot be dropped.
     */
    public Item dropItem(final String pItemName) throws CannotManageItemException {
        if (this.aCurrentRoom == null) throw new CannotManageItemException("Aucune salle dans laquelle poser l'objet.");

        // remove the item from the player inventory...
        Item vItem = this.aInventory.removeItem(pItemName);
        if (vItem == null) throw new CannotManageItemException("L'objet a déposer n'existe pas dans votre inventaire.");

        // subtract the item weight
        this.aWeight -= vItem.getWeight();

        // ...and add it to the room
        this.aCurrentRoom.addItem(vItem);

        return vItem;
    }

    /**
     * Gets the inventory description, with all items' name and the weight carried.
     *
     * @return inventory description
     */
    public String getInventoryDescription() {
        return String.format("Inventaire : %s (poids: %d)%n", this.aInventory.getAllNames(), this.aWeight);
    }

    /**
     * Gets the current items' weight carried.
     *
     * @return total items' weight.
     */
    public int getWeight() {
        return this.aWeight;
    }

    /**
     * Gets the items' count.
     *
     * @return items' count.
     */
    public int itemsCount() {
        return this.aInventory.size();
    }

    public static class CannotManageItemException extends Exception {
        public CannotManageItemException(final String pMessage) {
            super(pMessage);
        }
    }
}
