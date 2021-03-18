import java.util.HashMap;
import java.util.Stack;

/**
 * This class represents a player of the game Zuul.
 */
public class Player {
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
    private final HashMap<String, Item> aInventory;

    /**
     * The current room where the player is.
     */
    private Room aCurrentRoom;

    /**
     * Create a new player.
     */
    public Player() {
        this.aPreviousRooms = new Stack<>();
        this.aInventory = new HashMap<>();
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
     * @param vItemName name of the item to be taken.
     * @return the item just added.
     * @throws CantManageItemException if the item to be taken doesn't exist in the room.
     */
    public Item takeItem(final String vItemName) throws CantManageItemException {
        if (this.aCurrentRoom == null) throw new CantManageItemException("Le joueur n'est pas dans une salle.");

        // remove the item from the room...
        Item vItem = this.aCurrentRoom.removeItem(vItemName);
        if (vItem == null) throw new CantManageItemException("L'objet voulu n'existe pas dans la salle.");

        // ...and then add it to the player
        this.aInventory.put(vItem.getName(), vItem);

        // returns a reference of the item just dropped
        return vItem;
    }

    /**
     * Drops the item held by the player in the current room.
     *
     * @param pName name of the item to be removed.
     * @return the item just dropped.
     */
    public Item dropItem(final String pName) throws CantManageItemException {
        if (this.aCurrentRoom == null) throw new CantManageItemException("Le joueur n'est pas dans une salle.");

        // remove the item from the player...
        Item vItem = this.aInventory.remove(pName);

        if (vItem == null) throw new CantManageItemException("Vous ne possédez pas l'objet que vous voulez déposer.");

        // ... and add it to the current room
        this.aCurrentRoom.addItem(vItem);

        return vItem;
    }

    public static class CantManageItemException extends Exception {
        public CantManageItemException(final String pMessage) {
            super(pMessage);
        }
    }
}
