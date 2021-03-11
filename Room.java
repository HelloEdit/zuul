import java.util.HashMap;

/**
 * This class represents a room from the Zuul project.
 * It contains exits and a description.
 */
public class Room {
    /**
     * The name of the room.
     */
    private final String aName;

    /**
     * The description of the room.
     */
    private final String aDescription;

    /**
     * The exits of the room.
     */
    private final HashMap<String, Room> aExits;
    /**
     * The image for the room.
     */
    private final String aImageName;
    /**
     * The item in the room.
     */
    private Item aItem;

    /**
     * Creates a new room.
     *
     * @param pName        name of the room.
     * @param pDescription description of the room.
     * @param pImageName   image name of the room.
     */
    public Room(final String pName, final String pDescription, final String pImageName) {
        this.aName = Room.capitalize(pName);
        this.aDescription = Room.capitalize(pDescription);
        this.aImageName = pImageName;
        this.aExits = new HashMap<>();
    }

    /**
     * Creates a new room.
     *
     * @param pName        name of the room.
     * @param pDescription description of the room.
     */
    public Room(final String pName, final String pDescription) {
        this(pName, pDescription, pName.toLowerCase() + ".png");
    }

    /**
     * Utility function to capitalize the first letter of a String.
     *
     * @param pInput the string to be capitalized.
     * @return the new formatted String.
     */
    private static String capitalize(String pInput) {
        if (pInput == null) return "";
        return pInput.substring(0, 1).toUpperCase() + pInput.substring(1);
    }

    /**
     * Gets a long description of this room.
     *
     * @return the complete description.
     */
    public String getLongDescription() {
        String vText = String.format(
                "Vous êtes actuellement dans la salle \"%s\"\n%s.\n",
                this.aName,
                this.aDescription
        );

        if (this.aItem == null) vText += "Il n'y a pas d'objet.\n";
        else vText += "Il y a un objet : \"" + this.aItem.getDescription() + "\".\n";

        vText += this.getExitString();

        return vText;
    }

    /**
     * Set an exit of the room.
     *
     * @param pDirection the direction of the exit.
     * @param pExit      the Room to which the exit leads.
     * @return the current room.
     */
    public Room setExit(final String pDirection, final Room pExit) {
        this.aExits.put(pDirection, pExit);

        return this;
    }

    /**
     * Retrieves the Room associated with the exit in the requested direction.
     *
     * @param pDirection the direction of the exit.
     * @return the room associated with the exit.
     */
    public Room getExit(String pDirection) {
        return this.aExits.get(pDirection);
    }

    /**
     * Gets all existing exits of the room.
     *
     * @return a string with the possibles exits.
     */
    public String getExitString() {
        if (this.aExits.size() == 0) return "Aucune sortie n'est disponible.";

        StringBuilder vResult = new StringBuilder("Vous pouvez sortir par : ");
        for (String vExit : this.aExits.keySet())
            vResult.append(vExit).append(" ");

        return vResult.toString();
    }

    /**
     * Set an item for the actual room.
     *
     * @param pDescription description of the item.
     * @param pWeight      weight of the item. 0 if the item has not weight.
     */
    public Room setItem(final String pDescription, final int pWeight) {
        this.aItem = new Item(pDescription, pWeight);

        return this;
    }

    /**
     * Gets the room's image name.
     *
     * @return a string describing the room's image name.
     */
    public String getImageName() {
        return aImageName;
    }
}
