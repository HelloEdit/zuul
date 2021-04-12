package zuul.pkg_room;

/**
 * A transporter pkg_room that move you to a random pkg_room.
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
     * Name of the pkg_room set as forced destination.
     */
    private String aForcedExit;

    public TransporterRoom(final RoomRandomizer pRandomizer) {
        super("Salle de téléportation", "Pratique pour se déplacer dans le vaisseau");

        this.aRandomizer = pRandomizer;
    }

    /**
     * Returns a random pkg_room, independently of the direction provided.
     *
     * @param pDirection Direction of the exit.
     * @return A random pkg_room.
     */
    @Override
    public Room getExit(final String pDirection) {
        return this.aForcedExit == null
                ? this.aRandomizer.getRandomRoom()
                : this.aRandomizer.getRoom(this.aForcedExit);
    }

    /**
     * Forces a specific pkg_room name as the exit of the pkg_room.
     *
     * @param pExitName The forced exit name.
     */
    public void setForcedExit(final String pExitName) {
        this.aForcedExit = pExitName;
    }
}
