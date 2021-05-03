package zuul.pkg_personage;

import zuul.pkg_room.Room;
import zuul.pkg_room.RoomRandomizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a non-player character who can move.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class MovingPersonage extends Personage {
    /**
     * All instances of moving non-player.
     */
    private static final List<MovingPersonage> AllMovingPersonages = new ArrayList<>();

    /**
     * Possible rooms for the movement of the personage.
     */
    private final RoomRandomizer aRandomizer;

    /**
     * Current room where the personage is.
     */
    private Room aCurrent;

    /**
     * Creates a new personage.
     *
     * @param aName        Name of the personage.
     * @param aDescription Description of the personage.
     */
    public MovingPersonage(String aName, String aDescription) {
        super(aName, aDescription);

        this.aRandomizer = new RoomRandomizer();

        AllMovingPersonages.add(this);
    }

    /**
     * Adds rooms to the travel possibilities.
     * @param pRooms Rooms to be added.
     */
    public void addRooms(final Room... pRooms) {
        this.aRandomizer.addAll(pRooms);
    }

    /**
     * Moves the personage in a random room.
     */
    public void move() {
        if (this.aRandomizer.size() <= 1) {
            throw new IllegalStateException(this.getName() + " doit avoir plus d'une pièce dans laquelle se déplacer.");
        }

        this.aCurrent.removePersonage(this.getName());
        Room vNextRoom = this.aRandomizer.getRandomRoom();
        vNextRoom.addPersonage(this);

        this.setCurrentRoom(vNextRoom);
    }

    /**
     * Sets the current room of the personage.
     * @param pRoom
     */
    public void setCurrentRoom(final Room pRoom) {
        this.aCurrent = pRoom;
    }

    /**
     * Moves all the non-player that can move.
     */
    public static void moveAll() {
        for (MovingPersonage vPerso : AllMovingPersonages)
            vPerso.move();
    }
}