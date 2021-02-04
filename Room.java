import java.util.HashMap;

public class Room {
    private final String aDescription;
    private final HashMap<String, Room> aExits;

    /**
     * Creates a new room with a description.
     *
     * @param pDescription the description of the Room.
     */
    public Room(final String pDescription) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<>();
    }

    /**
     * Gets the room description.
     *
     * @return the room description.
     */
    public String getDescription() {
        return this.aDescription;
    }

    /**
     * Gets a long description of this room.
     *
     * @return the complete description.
     */
    public String getLongDescription() {
        return "You are " + this.aDescription + ".\n" + this.getExitString();
    }

    /**
     * Set an exit of the room.
     *
     * @param pDirection the direction of the exit.
     * @param pExit the Room to which the exit leads.
     */
    public void setExit(final String pDirection, final Room pExit) {
        this.aExits.put(pDirection, pExit);
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
} // Room
