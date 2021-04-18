package zuul.pkg_command;

import zuul.pkg_game.GameEngine;
import zuul.pkg_game.Player;
import zuul.pkg_ui.UserInterface;

/**
 * Handles the unknown behaviour.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class UnknownCommand extends Command {
    public UnknownCommand() {
        super(null);
    }

    /**
     * Handles the behavior when a command entered by the user is not recognized.
     *
     * @param pGameEngine The game engine.
     * @param pPlayer     The player using the command.
     * @param pInterface  The user interface used by the game.
     */
    @Override
    public void execute(GameEngine pGameEngine, Player pPlayer, UserInterface pInterface) {
        pInterface.println("Commande inconnue.");
        pInterface.println("Les commandes suivantes sont disponibles:");
        pInterface.println("\t" + Parser.getCommandList());
    }

}
