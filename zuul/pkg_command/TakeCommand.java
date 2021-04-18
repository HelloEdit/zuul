package zuul.pkg_command;

import zuul.pkg_game.GameEngine;
import zuul.pkg_game.Player;
import zuul.pkg_item.Item;
import zuul.pkg_ui.UserInterface;

/**
 * Handles the take command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class TakeCommand extends Command {
    public TakeCommand() {
        super("take");
    }

    /**
     * Takes the specified item in the current room.
     *
     * @param pGameEngine The game engine.
     * @param pPlayer     The player using the command.
     * @param pInterface  The user interface used by the game.
     */
    @Override
    public void execute(GameEngine pGameEngine, Player pPlayer, UserInterface pInterface) throws Item.CannotManageItemException {
        if (!this.hasSecondWord()) {
            pInterface.println("Vous devez spécifier l'pkg_item à prendre.");
            return;
        }

        Item vItem = pPlayer.takeItem(this.getSecondWord());

        pInterface.printf("Vous avez bien récupéré %s.", vItem.getName());
        pInterface.println();
    }

}
