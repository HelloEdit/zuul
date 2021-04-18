package zuul.pkg_command;

import zuul.pkg_game.GameEngine;
import zuul.pkg_game.Player;
import zuul.pkg_item.Item;
import zuul.pkg_ui.UserInterface;

/**
 * Handles the item command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class ItemCommand extends Command {
    public ItemCommand() {
        super("item");
    }

    /**
     * Shows the description of a specific item, or of the whole inventory.
     *
     * @param pGameEngine The game engine.
     * @param pPlayer     The player using the command.
     * @param pInterface  The user interface used by the game.
     */
    @Override
    public void execute(GameEngine pGameEngine, Player pPlayer, UserInterface pInterface) {
        if (!this.hasSecondWord()) {
            Item vItem = pPlayer.getItem(this.getSecondWord());

            if (vItem == null) pInterface.println("Vous ne possédez pas cet item.");
            else pInterface.println(vItem.getLongDescription());

            return;
        }

        pInterface.println("Vous ne possédez pas cet objet.");
        pInterface.println(pPlayer.getInventoryDescription());
    }
}
