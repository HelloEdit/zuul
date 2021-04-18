package zuul.pkg_command;

import zuul.pkg_game.GameEngine;
import zuul.pkg_game.Player;
import zuul.pkg_game.Timer;
import zuul.pkg_room.Room;
import zuul.pkg_ui.UserInterface;

/**
 * Handles the go command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class GoCommand extends Command {
    public GoCommand() {
        super("go");
    }

    /**
     * Moves the player in another room by taking an available exit.
     *
     * @param pGameEngine    The game engine.
     * @param pPlayer    The player using the command.
     * @param pInterface The user interface used by the game.
     */
    @Override
    public void execute(GameEngine pGameEngine, Player pPlayer, UserInterface pInterface) throws Room.RoomNotFoundException, Timer.TimerLimitException {
        if (!this.hasSecondWord()) {
            pInterface.println("Cette direction est inconnue.");
            pInterface.printf("Vous pouvez aller : %s", pPlayer.getExitsDescription());
            pInterface.println();
            return;
        }

        pPlayer.goToExit(this.getSecondWord());

        pGameEngine.printLocationInfo();
    }
}
