package zuul.pkg_room;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class is responsible to handle the randomness of selecting a room
 * for special purposes like the transporter room.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class RoomRandomizer {
    /**
     * The lists of all possibles room to be transported in.
     */
    private final Map<String, Room> aRooms;

    /**
     * The randomness source.
     */
    private final Random aRandom;

    /**
     * Creates a new randomizer.
     */
    public RoomRandomizer() {
        this.aRooms = new HashMap<>();
        this.aRandom = new Random();
    }

    /**
     * Adds a new room to the randomizer choices.
     *
     * @param pRoom The room to be added.
     */
    public void add(final Room pRoom) {
        this.aRooms.put(pRoom.getName().toLowerCase(), pRoom);
    }

    /**
     * Add new rooms to the randomizer choices.
     *
     * @param pRooms The rooms to be added
     */
    public void addAll(final Room... pRooms) {
        for (Room room : pRooms) this.add(room);
    }

    /**
     * Gets a specific room.
     *
     * @param pName Name of the room.
     * @return The room asked.
     */
    public Room getRoom(final String pName) {
        return this.aRooms.get(pName);
    }

    /**
     * Gets a random room from the randomize choices.
     *
     * @return The random room selected.
     */
    public Room getRandomRoom() {
        if (this.aRooms.isEmpty()) return null;

        int vSize = this.aRooms.size();

        return this.aRooms.values()
                .stream()
                .skip(this.aRandom.nextInt(vSize))
                .findFirst()
                .orElse(null);
    }

    /**
     * Gets the number of possible rooms.
     *
     * @return The number of possibilities.
     */
    public int size() {
        return this.aRooms.size();
    }
}
