package zuul.pkg_command;

import zuul.pkg_game.Engine;
import zuul.pkg_game.Player;
import zuul.pkg_room.Room;
import zuul.pkg_ui.UserInterface;

import java.util.EmptyStackException;

/**
 * Handles the back pkg_command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class BackCommand extends Command {
    public BackCommand() {
        super("back");
    }

    /**
     * Goes back to the previous pkg_room if possible.
     *
     * @param pEngine    The pkg_game engine.
     * @param pPlayer    The player using the pkg_command.
     * @param pInterface The user interface used by the pkg_game.
     */
    @Override
    public void execute(Engine pEngine, Player pPlayer, UserInterface pInterface) throws Room.RoomNotFoundException {
        if (this.hasSecondWord()) {
            pInterface.println("Aucun second mot attendu.");
            return;
        }

        Room vPrevious;
        try {
            vPrevious = pPlayer.toPreviousRoom();
        } catch (EmptyStackException pError) {
            throw new Room.RoomNotFoundException();
        }

        pInterface.printf("Vous êtes de retour dans %s", vPrevious.getName());
        pInterface.println();
    }
}
