package zuul.pkg_command;

import zuul.pkg_game.Engine;
import zuul.pkg_game.Player;
import zuul.pkg_item.Item;
import zuul.pkg_ui.UserInterface;

/**
 * Handles the pkg_item pkg_command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class ItemCommand extends Command {
    public ItemCommand() {
        super("pkg_item");
    }

    /**
     * Shows the description of a specific pkg_item, or of the whole inventory.
     *
     * @param pEngine    The pkg_game engine.
     * @param pPlayer    The player using the pkg_command.
     * @param pInterface The user interface used by the pkg_game.
     */
    @Override
    public void execute(Engine pEngine, Player pPlayer, UserInterface pInterface) {
        if (!this.hasSecondWord()) {
            Item vItem = pPlayer.getItem(this.getSecondWord());

            if (vItem == null) pInterface.println("Vous ne possédez pas cet pkg_item.");
            else pInterface.println(vItem.getLongDescription());

            return;
        }

        pInterface.println("Vous ne possédez pas cet objet.");
        pInterface.println(pPlayer.getInventoryDescription());
    }
}
