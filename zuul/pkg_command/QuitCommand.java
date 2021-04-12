package zuul.pkg_command;

import zuul.pkg_game.Engine;
import zuul.pkg_game.Player;
import zuul.pkg_ui.UserInterface;

/**
 * Handles the quit command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class QuitCommand extends Command {
    public QuitCommand() {
        super("quit");
    }

    /**
     * Quits the game.
     *
     * @param pEngine    The game engine.
     * @param pPlayer    The player using the command.
     * @param pInterface The user interface used by the game.
     */
    @Override
    public void execute(Engine pEngine, Player pPlayer, UserInterface pInterface) {
        if (this.hasSecondWord()) {
            pInterface.println("Aucun second mot attendu.");
            return;
        }

        pInterface.close();
    }

}
