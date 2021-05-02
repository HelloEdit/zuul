package zuul.pkg_room;

/**
 * A transporter room that move you to a random room.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class TransporterRoom extends Room {
    /**
     * Lists all possible rooms for teleportation.
     */
    private final RoomRandomizer aRandomizer;

    /**
     * Name of the room set as forced destination.
     */
    private String aForcedExit;

    /**
     * Creates a new transporter room.
     *
     * @param pName        Name of the transporter room.
     * @param pDescription Description of the transporter room.
     */
    public TransporterRoom(final String pName, final String pDescription) {
        super(pName, pDescription);

        this.aRandomizer = new RoomRandomizer();
    }

    /**
     * Adds a room as possible destination.
     *
     * @param pRoom Room to be added as possible destination.
     */
    public void add(final Room pRoom) {
        this.aRandomizer.add(pRoom);
    }

    /**
     * Adds possible destination for the transporter.
     *
     * @param pRooms Rooms to be added as possible destinations.
     */
    public void addAll(Room... pRooms) {
        this.aRandomizer.addAll(pRooms);
    }

    /**
     * Returns a random room, independently of the direction provided.
     *
     * @param pDirection Direction of the exit.
     * @return A random room.
     */
    @Override
    public Room getExit(final String pDirection) {
        if (this.aRandomizer.size() == 0) {
            throw new IllegalStateException("Le téléporteur à besoin d'avoir au moins une sortie possible.");
        }

        return this.aForcedExit == null
                ? this.aRandomizer.getRandomRoom()
                : this.aRandomizer.getRoom(this.aForcedExit);
    }

    /**
     * Forces a specific room name as the exit of the room.
     *
     * @param pExitName The forced exit name.
     */
    public void setForcedExit(final String pExitName) {
        this.aForcedExit = pExitName;
    }
}
