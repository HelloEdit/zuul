package zuul.pkg_room;

import zuul.Utils;
import zuul.pkg_item.Item;
import zuul.pkg_item.ItemList;
import zuul.pkg_personage.Personage;

import java.io.File;
import java.util.HashMap;

/**
 * This pkg_room represents a pkg_room in the Zuul pkg_game.
 * It allows to manipulate the items inside as well as various pkg_game mechanics.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Room {
    /**
     * Name of the pkg_room.
     */
    private final String aName;

    /**
     * Description of the pkg_room.
     */
    private final String aDescription;

    /**
     * All the exits that can be reached from this pkg_room.
     */
    private final HashMap<String, Room> aExits;

    /**
     * All the characters in the pkg_room.
     */
    private final HashMap<String, Personage> aPersonage;

    /**
     * The items in this pkg_room.
     */
    private final ItemList aItems;

    /**
     * The image path attached to the pkg_room.
     */
    private final String aImage;

    /**
     * Creates a new pkg_room.
     *
     * @param pName        Name of the pkg_room.
     * @param pDescription Description of the pkg_room.
     */
    public Room(String pName, String pDescription) {
        // it is assumed that the image file has the same name as the pkg_room.
        this.aName = pName;
        this.aDescription = pDescription;
        this.aExits = new HashMap<>();
        this.aPersonage = new HashMap<>();
        this.aItems = new ItemList();
        this.aImage = pName.replaceAll(" ", "_") + ".png";
    }

    /**
     * Retrieves the Room associated with the exit in the requested direction.
     *
     * @param pDirection Direction of the exit.
     * @return The pkg_room associated with the exit.
     */
    public Room getExit(String pDirection) {
        return this.aExits.get(pDirection);
    }

    /**
     * Checks if a specific pkg_room can be reached from the actual pkg_room.
     *
     * @param vRoom The pkg_room to be reached.
     * @return true if the pkg_room can be reached. False otherwise.
     */
    public boolean hasExit(final Room vRoom) {
        return this.aExits.containsValue(vRoom);
    }

    /**
     * Set an exit of the pkg_room.
     *
     * @param pDirection Direction of the exit.
     * @param pExit      Room to which the exit leads.
     */
    public void setExit(final String pDirection, final Room pExit) {
        this.aExits.put(pDirection, pExit);
    }

    /**
     * Creates and adds a new pkg_item in the pkg_room.
     *
     * @param pName        Name of the pkg_item.
     * @param pDescription Description of the pkg_item.
     * @param pWeight      Weight of the pkg_item.
     */
    public void addItem(final String pName, final String pDescription, final int pWeight) {
        this.aItems.addItem(pName, pDescription, pWeight);
    }

    /**
     * Creates and adds a new pkg_item in the pkg_room.
     *
     * @param pName        Name of the pkg_item.
     * @param pDescription Description of the pkg_item.
     */
    public void addItem(final String pName, final String pDescription) {
        this.addItem(pName, pDescription, 1);
    }

    /**
     * Adds a new pkg_item in the pkg_room.
     *
     * @param pItem Item to be added.
     */
    public void addItem(final Item pItem) {
        this.aItems.addItem(pItem);
    }

    /**
     * Removes an pkg_item from the pkg_room.
     *
     * @param pName Name of the pkg_item.
     * @return The pkg_item removed.
     */
    public Item removeItem(final String pName) {
        return this.aItems.removeItem(pName);
    }

    /**
     * Gets a specific pkg_item of the pkg_room.
     *
     * @param pSearch The pkg_item name.
     * @return The pkg_item asked.
     */
    public Item getItem(final String pSearch) {
        return this.aItems.getItem(pSearch);
    }

    /**
     * Gets a specific character in the pkg_room.
     *
     * @param pSearch The pkg_personage name.
     * @return The pkg_personage asked.
     */
    public Personage getPersonage(String pSearch) {
        return this.aPersonage.get(pSearch);
    }

    /**
     * Gets the pkg_room's description.
     *
     * @return The Room description.
     */
    public String getDescription() {
        return Utils.capitalize(this.aDescription);
    }

    /**
     * Gets a long description of this pkg_room.
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

        String vExits = "Vous pouvez sortir par : " + this.getExitString();

        return String.format(vTemplate, this.aName, this.aDescription, vExits, vItems);
    }

    /**
     * Gets a string listing all existing exits of the pkg_room.
     *
     * @return A string with the possibles exits.
     */
    public String getExitString() {
        if (this.aExits.isEmpty()) return "Aucune sortie n'est disponible";
        return String.join(", ", this.aExits.keySet());
    }

    /**
     * Gets the pkg_room name.
     *
     * @return The pkg_room name.
     */
    public String getName() {
        return Utils.capitalize(this.aName);
    }

    /**
     * Gets the pkg_room's image file name.
     *
     * @return The pkg_room's image name.
     */
    public String getImage() {
        File vFile = new File("./assets/" + this.aImage);

        return vFile.exists()
                ? this.aImage
                : "default.png";
    }

    /**
     * If a pkg_room is not found.
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
