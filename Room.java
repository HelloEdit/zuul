import java.util.HashMap;

public class Room {
    private final String aName;
    private final String aDescription;
    private final HashMap<String, Room> aExits;

    /**
     * Creates a new room.
     * @param pName name of the room.
     * @param pDescription description of the room.
     */
    public Room(final String pName, final String pDescription) {
        this.aName = pName;
        this.aDescription = pDescription;
        this.aExits = new HashMap<>();
    }

    /**
     * Gets a long description of this room.
     *
     * @return the complete description.
     */
    public String getLongDescription() {
        return "Vous êtes " +
                this.aName + ".\n" +
                Room.capitalize(this.aDescription) + ".\n" +
                Room.capitalize(this.getExitString());
    }

    /**
     * Set an exit of the room.
     *
     * @param pDirection the direction of the exit.
     * @param pExit the Room to which the exit leads.
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
        StringBuilder vResult = new StringBuilder("Exits : ");
        for (String vExit : this.aExits.keySet())
            vResult.append(vExit).append(" ");

        return vResult.toString();
    }

    /**
     * Utility function to capitalize the first letter of a String.
     * @param pStr the string to be capitalized.
     * @return the new formatted String.
     */
    public static String capitalize(String pStr) {
        if (pStr == null) return "";
        return pStr.substring(0, 1).toUpperCase() + pStr.substring(1);
    }
} // Room
