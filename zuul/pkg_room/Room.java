package zuul.pkg_room;

import zuul.Utils;
import zuul.pkg_item.Item;
import zuul.pkg_item.ItemList;
import zuul.pkg_personage.Personage;

import java.io.File;
import java.util.HashMap;

/**
 * This room represents a room in the Zuul game.
 * It allows to manipulate the items inside as well as various game mechanics.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Room {
    /**
     * Name of the room.
     */
    private final String aName;

    /**
     * Description of the room.
     */
    private final String aDescription;

    /**
     * All the exits that can be reached from this room.
     */
    private final HashMap<String, Room> aExits;

    /**
     * All the characters in the room.
     */
    private final HashMap<String, Personage> aPersonages;

    /**
     * The items in this room.
     */
    private final ItemList aItems;

    /**
     * The image path attached to the room.
     */
    private final String aImage;

    /**
     * Creates a new room.
     *
     * @param pName        Name of the room.
     * @param pDescription Description of the room.
     */
    public Room(String pName, String pDescription) {
        // it is assumed that the image file has the same name as the room.
        this.aName = pName;
        this.aDescription = pDescription;
        this.aExits = new HashMap<>();
        this.aPersonages = new HashMap<>();
        this.aItems = new ItemList();

        this.aImage = pName.toLowerCase().replaceAll(" ", "_") + ".png";
    }

    /**
     * Retrieves the Room associated with the exit in the requested direction.
     *
     * @param pDirection Direction of the exit.
     * @return The room associated with the exit.
     */
    public Room getExit(String pDirection) {
        return this.aExits.get(pDirection);
    }

    /**
     * Checks if a specific room can be reached from the actual room.
     *
     * @param vRoom The room to be reached.
     * @return true if the room can be reached. False otherwise.
     */
    public boolean hasExit(final Room vRoom) {
        return this.aExits.containsValue(vRoom);
    }

    /**
     * Set an exit of the room.
     *
     * @param pDirection Direction of the exit.
     * @param pExit      Room to which the exit leads.
     */
    public void setExit(final String pDirection, final Room pExit) {
        this.aExits.put(pDirection, pExit);
    }

    /**
     * Creates and adds a new item in the room.
     *
     * @param pName        Name of the item.
     * @param pDescription Description of the item.
     * @param pWeight      Weight of the item.
     */
    public void addItem(final String pName, final String pDescription, final int pWeight) {
        this.aItems.addItem(pName, pDescription, pWeight);
    }

    /**
     * Creates and adds a new item in the room.
     *
     * @param pName        Name of the item.
     * @param pDescription Description of the item.
     */
    public void addItem(final String pName, final String pDescription) {
        this.addItem(pName, pDescription, 1);
    }

    /**
     * Adds a new item in the room.
     *
     * @param pItem Item to be added.
     */
    public void addItem(final Item pItem) {
        this.aItems.addItem(pItem);
    }

    /**
     * Creates and adds a new personage in the room.
     *
     * @param pName Name of the personage
     * @param pDescription Description of the personage.
     */
    public void addPersonage(final String pName, final String pDescription) {
        this.aPersonages.put(pName, new Personage(pName, pDescription));
    }

    /**
     * Adds a personage to the room.
     *
     * @param pPersonage personage to be added.
     */
    public void addPersonage(final Personage pPersonage) {
        this.aPersonages.put(pPersonage.getName(), pPersonage);
    }

    /**
     * Removes an item from the room.
     *
     * @param pName Name of the item.
     * @return The item removed.
     */
    public Item removeItem(final String pName) {
        return this.aItems.removeItem(pName);
    }

    /**
     * Gets a specific item of the room.
     *
     * @param pSearch The item name.
     * @return The item asked.
     */
    public Item getItem(final String pSearch) {
        return this.aItems.getItem(pSearch);
    }

    /**
     * Gets a specific character in the room.
     *
     * @param pSearch The personage name.
     * @return The personage asked.
     */
    public Personage getPersonage(String pSearch) {
        return this.aPersonages.get(pSearch);
    }

    /**
     * Gets the room's description.
     *
     * @return The Room description.
     */
    public String getDescription() {
        return Utils.capitalize(this.aDescription);
    }

    /**
     * Gets the names of the personage in the room.
     *
     * @return The names.
     */
    public String getPersonagesNames() {
        if (this.aPersonages.isEmpty())
            return "Aucun personnages";

        return String.join(", ", this.aPersonages.keySet());
    }

    /**
     * Gets a long description of this room.
     *
     * @return The complete description.
     */
    public String getLongDescription() {
        String vTemplate = "Vous êtes dans \"%s\".%n" + // name
                "%s.%n" + // description
                "%s.%n" + // exits
                "%s."; // items lookup

        String vItems = this.aItems.isEmpty()
                ? "Il n'y a pas d'objets"
                : "Vous voyez dans cette pièce : " + this.aItems.getItemsNames();

        String vPersonages = this.aItems.isEmpty()
                ? "Il n'y a pas de personnages"
                : "Il semble y avoir des gens : " + this.getPersonagesNames();

        String vExits = "Vous pouvez sortir par : " + this.getExitString();

        return String.format(vTemplate, this.aName, this.aDescription, vExits, vItems);
    }

    /**
     * Gets a string listing all existing exits of the room.
     *
     * @return A string with the possibles exits.
     */
    public String getExitString() {
        if (this.aExits.isEmpty()) return "Aucune sortie n'est disponible";
        return String.join(", ", this.aExits.keySet());
    }

    /**
     * Gets the room name.
     *
     * @return The room name.
     */
    public String getName() {
        return Utils.capitalize(this.aName);
    }

    /**
     * Gets the room's image file name.
     *
     * @return The room's image name.
     */
    public String getImage() {
        File vFile = new File("./assets/" + this.aImage);

        return vFile.exists()
                ? this.aImage
                : "default.png";
    }

    /**
     * If a room is not found.
     */
    public static class RoomNotFoundException extends Exception {
        public RoomNotFoundException() {
            super("Impossible de trouver la salle.");
        }

        public RoomNotFoundException(final String pMsg) {
            super(pMsg);
        }
    }
}
