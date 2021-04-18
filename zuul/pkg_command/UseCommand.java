package zuul.pkg_command;

import zuul.pkg_game.GameEngine;
import zuul.pkg_game.Player;
import zuul.pkg_item.Item;
import zuul.pkg_ui.UserInterface;

/**
 * Handles the use command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class UseCommand extends Command {
    public UseCommand() {
        super("use");
    }

    /**
     * Uses the item specified.
     *
     * @param pGameEngine The game engine.
     * @param pPlayer     The player using the command.
     * @param pInterface  The user interface used by the game.
     */
    @Override
    public void execute(GameEngine pGameEngine, Player pPlayer, UserInterface pInterface) {
        if (!this.hasSecondWord()) {
            pInterface.println("Vous devez spécifier l'objet à utiliser.");
            return;
        }

        Item vItem = pPlayer.getItem(this.getSecondWord());
        if (vItem == null) {
            pInterface.println("Vous ne possédez pas cet objet.");
            return;
        }

        try {
            vItem.use(pGameEngine, pPlayer, pInterface);
        } catch (Exception pError) {
            pInterface.println("Vous ne pouvez pas utiliser cet objet.");
            pInterface.println(pError.getMessage());
        }
    }

}
