package zuul.pkg_command;

import zuul.pkg_game.Engine;
import zuul.pkg_game.Player;
import zuul.pkg_room.Room;
import zuul.pkg_ui.UserInterface;

import java.util.EmptyStackException;

/**
 * Handles the back command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class BackCommand extends Command {
    public BackCommand() {
        super("back");
    }

    /**
     * Goes back to the previous room if possible.
     *
     * @param pEngine    The game engine.
     * @param pPlayer    The player using the command.
     * @param pInterface The user interface used by the game.
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
        pEngine.printLocationInfo();
    }
}
