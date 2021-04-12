package zuul.pkg_item;

import zuul.pkg_game.Engine;
import zuul.pkg_game.Player;
import zuul.pkg_room.Room;
import zuul.pkg_ui.UserInterface;

/**
 * A beamer is an pkg_item used to teleport yourself to a previous pkg_room.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Beamer extends Item {
    private Room aSavedLocation;

    /**
     * Creates the beamer pkg_item.
     */
    public Beamer() {
        super("beamer", "Pratique pour revenir sur vos pas rapidement !", 50);
    }

    /**
     * Use the beamer, once to charge it and once to fire it.
     *
     * @param pEngine    The pkg_game engine.
     * @param pPlayer    The player using the pkg_command.
     * @param pInterface The user interface used by the pkg_game.
     */
    @Override
    public void use(final Engine pEngine, Player pPlayer, UserInterface pInterface) {
        if (this.aSavedLocation == null) {
            this.aSavedLocation = pPlayer.getRoom();
            pInterface.println("Beamer chargé !");
        } else {
            pPlayer.clearRoomHistory();
            pPlayer.setCurrentRoom(this.aSavedLocation);
            pPlayer.deleteItem(this.getName());

            pInterface.println("Beamer utilisé !");
            pInterface.println();

            pEngine.printLocationInfo();
        }
    }
}
