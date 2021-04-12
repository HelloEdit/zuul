package zuul.pkg_game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableMap;
import zuul.pkg_item.Item;
import zuul.pkg_item.ItemList;
import zuul.pkg_room.Room;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * This class is intended to manage the player state through the game.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Player {
    /**
     * Player inventory.
     */
    private final ItemList aInventory;

    /**
     * History of the rooms were the player was.
     */
    private final Stack<Room> aPreviousRooms;

    /**
     * Maximum weight for the player's items.
     */
    private final IntegerProperty aMaxWeight;

    /**
     * Inventory weight.
     */
    private final IntegerProperty aWeight;
    /**
     * Room where the player is.
     */
    private final ObjectProperty<Room> aCurrentRoom;
    /**
     * Remaining movements to the player.
     * negative number if the movements are unlimited.
     */
    private Timer aTimer;

    /**
     * Creates a new player.
     */
    public Player() {
        this.aPreviousRooms = new Stack<>();
        this.aInventory = new ItemList();

        this.aCurrentRoom = new SimpleObjectProperty<>(null);
        this.aMaxWeight = new SimpleIntegerProperty(100);
        this.aWeight = new SimpleIntegerProperty(0);
        this.aTimer = new Timer();
    }

    /**
     * Goes back to the previous room.
     *
     * @return The previous room.
     * @throws EmptyStackException If there is no previous room.
     */
    public Room toPreviousRoom() throws EmptyStackException {
        Room vPrevious = this.aPreviousRooms.pop();
        this.aCurrentRoom.set(vPrevious);

        return vPrevious;
    }

    /**
     * Clears the room history.
     */
    public void clearRoomHistory() {
        this.aPreviousRooms.clear();
    }

    /**
     * Takes an exit and move the player to the new room.
     *
     * @param pDirection Exit to be taken.
     * @throws Room.RoomNotFoundException If no room is found for the direction given.
     * @throws Timer.TimerLimitException  If the player cannot makes any movement because of the timer.
     */
    public void goToExit(final String pDirection) throws Room.RoomNotFoundException, Timer.TimerLimitException {
        Room vCurrent = this.aCurrentRoom.get();

        Room vNext = vCurrent.getExit(pDirection);
        if (vNext == null) throw new Room.RoomNotFoundException("Cette sortie n'existe pas.");


        this.setCurrentRoom(vNext);

        // tells the timer that the player is doing an action
        this.aTimer.action();

        // check if the door was a trapdoor
        if (!vNext.hasExit(vCurrent)) {
            this.clearRoomHistory();
        }
    }

    /**
     * Takes an item in the current room.
     *
     * @param pItemName The item to be taken.
     * @return The item that has been taken.
     * @throws Item.CannotManageItemException If the requested object cannot be took.
     */
    public Item takeItem(final String pItemName) throws Item.CannotManageItemException {
        Room vCurrentRoom = this.aCurrentRoom.get();
        if (vCurrentRoom == null)
            throw new Item.CannotManageItemException("Aucune salle dans laquelle prendre l'objet.");

        // get the item from the current room...
        Item vItem = vCurrentRoom.getItem(pItemName);
        if (vItem == null) throw new Item.CannotManageItemException("L'objet à prendre n'existe pas dans la salle.");

        // if the sum is more than the max, the total weight of the items exceeds the allowed limit
        if (this.aWeight.get() + vItem.getWeight() > this.aMaxWeight.get()) {
            throw new Item.CannotManageItemException("Vous ne pouvez pas prendre cet objet, votre inventaire est trop chargé !");
        }

        this.aWeight.set(this.aWeight.get() + vItem.getWeight());

        // then we definitely remove it from the room
        vCurrentRoom.removeItem(vItem.getName());

        // ...and add it to the player's inventory
        this.aInventory.addItem(vItem);

        return vItem;
    }

    /**
     * Drops an item in the current room.
     *
     * @param pItemName The item to be dropped.
     * @return The item that has been dropped.
     * @throws Item.CannotManageItemException If the item is not found in the inventory.
     */
    public Item dropItem(final String pItemName) throws Item.CannotManageItemException {
        Room vCurrentRoom = this.aCurrentRoom.get();
        if (vCurrentRoom == null) throw new Item.CannotManageItemException("Aucune salle dans laquelle poser l'objet.");

        // remove the item from the player inventory...
        Item vItem = this.deleteItem(pItemName);
        if (vItem == null) throw new Item.CannotManageItemException("Vous ne possédez pas cet item.");

        // ...and add it to the room
        vCurrentRoom.addItem(vItem);

        return vItem;
    }

    /**
     * Deletes an item from the player inventory.
     *
     * @param pItemName Name of the item to be deleted.
     * @return The item deleted.
     */
    public Item deleteItem(String pItemName) {
        // remove the item from the player inventory...
        Item vItem = this.aInventory.removeItem(pItemName);
        if (vItem == null) return null;

        // subtract the item weight to the inventory
        this.aWeight.set(this.aWeight.get() - vItem.getWeight());

        return vItem;
    }

    /**
     * Gets an item from the player inventory.
     *
     * @param pItemName Name of the item.
     * @return The item retrieved.
     */
    public Item getItem(final String pItemName) {
        return this.aInventory.getItem(pItemName);
    }

    /**
     * Gets a description of the possibles exits.
     *
     * @return The description of possibles exits.
     */
    public String getExitsDescription() {
        return "Vous pouvez aller : " + this.aCurrentRoom.get().getExitString() + ".";
    }

    /**
     * Gets inventory description.
     *
     * @return The description of the inventory.
     */
    public String getInventoryDescription() {
        if (this.aInventory.isEmpty()) return "L'inventaire est vide !";

        return String.format("Inventaire : %s (poids %d)", this.aInventory.getItemsNames(), this.aWeight.get());
    }

    /**
     * Gets the room description string.
     *
     * @return The room description.
     */
    public String getRoomDescription() {
        return this.aCurrentRoom.get().getLongDescription();
    }

    /**
     * Gets the current room were the player is.
     *
     * @return Current room.
     */
    public Room getRoom() {
        return this.aCurrentRoom.get();
    }

    /**
     * Gets the maximum weight of the inventory.
     *
     * @return Maximum weight.
     */
    public int getMaxWeight() {
        return this.aMaxWeight.get();
    }

    /**
     * Sets the new maximum weight of the player's inventory.
     *
     * @param pMaxWeight New maximum weight.
     */
    public void setMaxWeight(final int pMaxWeight) {
        this.aMaxWeight.set(pMaxWeight);
    }

    /**
     * Gets items inventory.
     *
     * @return Item inventory.
     */
    public ItemList getInventory() {
        return this.aInventory;
    }

    /**
     * Sets a new timer.
     *
     * @param pTimer The timer to be used.
     */
    public void setTimer(final Timer pTimer) {
        this.aTimer = pTimer;
    }

    /**
     * Sets the new current room where the player will be.
     *
     * @param pRoom The new current room.
     */
    public void setCurrentRoom(final Room pRoom) {
        if (this.aCurrentRoom.get() != null) this.aPreviousRooms.push(this.aCurrentRoom.get());
        this.aCurrentRoom.set(pRoom);
    }

    // Notes: These methods were created to make the JavaFX interface responsive. They are not meant to be
    // called in any other context. To respect the encapsulation, you must use the usual getters.

    /**
     * Gets the observable inventory of the player
     *
     * @return Observable inventory.
     */
    public ObservableMap<String, Item> getObservableInventory() {
        return this.aInventory.getObservableItems();
    }

    /**
     * Gets the observable object for the room were the player is.
     *
     * @return Observable current room.
     */
    public ObjectProperty<Room> getObservableRoom() {
        return this.aCurrentRoom;
    }

    /**
     * Gets the current weight carried by the player.
     * This object is observable.
     *
     * @return Weight of the inventory.
     */
    public IntegerProperty getObservableWeight() {
        return this.aWeight;
    }

    /**
     * Gets the maximum weight that the player can carry.
     * This object is observable.
     *
     * @return Maximum weight of the inventory.
     */
    public IntegerProperty getObservableMaxWeight() {
        return this.aMaxWeight;
    }
}
