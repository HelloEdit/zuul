package zuul.pkg_room;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class is responsible to handle the randomness of selecting a pkg_room
 * for special purposes like the transporter pkg_room.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class RoomRandomizer {
    /**
     * The lists of all possibles pkg_room to be transported in.
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
     * Adds a new pkg_room to the randomizer choices.
     *
     * @param pRoom The pkg_room to be added.
     */
    public void add(final Room pRoom) {
        this.aRooms.put(pRoom.getName(), pRoom);
    }

    /**
     * Gets a specific pkg_room.
     *
     * @param pName Name of the pkg_room.
     * @return The pkg_room asked.
     */
    public Room getRoom(final String pName) {
        return this.aRooms.get(pName);
    }

    /**
     * Gets a random pkg_room from the randomize choices.
     *
     * @return The random pkg_room selected.
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
}
