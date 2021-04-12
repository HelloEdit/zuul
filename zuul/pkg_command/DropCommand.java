package zuul.pkg_command;

import zuul.pkg_game.Engine;
import zuul.pkg_game.Player;
import zuul.pkg_item.Item;
import zuul.pkg_ui.UserInterface;

/**
 * Handles the drop command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class DropCommand extends Command {
    public DropCommand() {
        super("drop");
    }

    /**
     * Drops the specified item in the current room.
     *
     * @param pEngine    The game engine.
     * @param pPlayer    The player using the command.
     * @param pInterface The user interface used by the game.
     */
    @Override
    public void execute(Engine pEngine, Player pPlayer, UserInterface pInterface) {
        if (!this.hasSecondWord()) {
            pInterface.println("Vous devez spécifier l'objet à laisser.");
            return;
        }

        Item vItem;
        try {
            vItem = pPlayer.dropItem(this.getSecondWord());
        } catch (Item.CannotManageItemException pError) {
            pInterface.println(pError.getMessage());
            return;
        }

        pInterface.printf("Vous avez bien déposé %s.", vItem.getName());
        pInterface.println();
    }

}
