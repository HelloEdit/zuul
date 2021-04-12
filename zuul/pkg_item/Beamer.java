package zuul.pkg_item;

import zuul.pkg_game.Engine;
import zuul.pkg_game.Player;
import zuul.pkg_room.Room;
import zuul.pkg_ui.UserInterface;

/**
 * A beamer is an item used to teleport yourself to a previous room.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Beamer extends Item {
    /**
     * Saved location for the beamer teleportation.
     */
    private Room aSavedLocation;

    /**
     * Creates the beamer item.
     */
    public Beamer() {
        super("beamer", "pratique pour revenir sur vos pas rapidement !", 50);
    }

    /**
     * Use the beamer, once to charge it and once to fire it.
     *
     * @param pEngine    The game engine.
     * @param pPlayer    The player using the command.
     * @param pInterface The user interface used by the game.
     */
    @Override
    public void use(final Engine pEngine, Player pPlayer, UserInterface pInterface) {
        if (this.aSavedLocation == null) {
            throw new NullPointerException(this.getName() + " n'est pas chargé.");
        }

        pPlayer.clearRoomHistory();
        pPlayer.setCurrentRoom(this.aSavedLocation);
        pPlayer.deleteItem(this.getName());

        pInterface.printf("%s utilisé !%n", this.getName());
        pInterface.println();

        pEngine.printLocationInfo();
    }

    /**
     * Saves the location to be teleported on.
     *
     * @param pRoom The location to be saved.
     */
    public void setSavedLocationLocation(final Room pRoom) {
        if (this.aSavedLocation != null) {
            throw new UnsupportedOperationException("Le chargement a déjà été fait !");
        }

        this.aSavedLocation = pRoom;
    }
}
