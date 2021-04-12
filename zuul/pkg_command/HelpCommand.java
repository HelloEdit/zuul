package zuul.pkg_command;

import zuul.pkg_game.Engine;
import zuul.pkg_game.Player;
import zuul.pkg_ui.UserInterface;

/**
 * Handles the help pkg_command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    /**
     * Displays help for the player.
     *
     * @param pEngine    The pkg_game engine.
     * @param pPlayer    The player using the pkg_command.
     * @param pInterface The user interface used by the pkg_game.
     */
    @Override
    public void execute(Engine pEngine, Player pPlayer, UserInterface pInterface) {
        pInterface.println("Vous êtes l'élu qui va rétablir l'équilibre de la galaxie.");
        pInterface.println("Vous devez trouver le crystal sacré pour cela.");
        pInterface.println("Les commandes disponibles sont les suivantes :");
        pInterface.printf("\t%s%n", Parser.getCommandList());
    }
}
