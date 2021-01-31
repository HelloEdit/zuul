public class Room {
    private final String aDescription;
    private Room aNorthExit;
    private Room aEastExit;
    private Room aSouthExit;
    private Room aWestExit;

    /**
     * Creates a new Room with a description
     *
     * @param pDescription the description of the Room
     */
    public Room(final String pDescription) {
        this.aDescription = pDescription;
    }

    /**
     * Sets the exits of a Room.
     * The parameters are in the order given by the wind rose
     * (starting by the north) in a clockwise direction.
     *
     * @param pNorthExit the north exit
     * @param pEastExit  the east exit
     * @param pSouthExit the south exit
     * @param pWestExit  the west exit
     */
    public void setExits(final Room pNorthExit, final Room pEastExit, final Room pSouthExit, final Room pWestExit) {
        this.aNorthExit = pNorthExit;
        this.aEastExit = pEastExit;
        this.aSouthExit = pSouthExit;
        this.aWestExit = pWestExit;
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
     * Retrieves the Room associated with the exit in the requested direction.
     *
     * @param pDirection the direction of the exit.
     * @return the room associated with the exit.
     */
    public Room getExit(String pDirection) {
        switch (pDirection) {
            case "north":
                return this.aNorthExit;

            case "east":
                return this.aEastExit;

            case "south":
                return this.aSouthExit;

            case "west":
                return this.aWestExit;

            default:
                return null;
        }
    }
} // Room
