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
}
