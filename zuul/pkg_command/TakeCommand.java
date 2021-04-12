package zuul.pkg_command;

import zuul.pkg_game.Engine;
import zuul.pkg_game.Player;
import zuul.pkg_item.Item;
import zuul.pkg_ui.UserInterface;

/**
 * Handles the take pkg_command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class TakeCommand extends Command {
    public TakeCommand() {
        super("take");
    }

    /**
     * Takes the specified pkg_item in the current pkg_room.
     *
     * @param pEngine    The pkg_game engine.
     * @param pPlayer    The player using the pkg_command.
     * @param pInterface The user interface used by the pkg_game.
     */
    @Override
    public void execute(Engine pEngine, Player pPlayer, UserInterface pInterface) throws Item.CannotManageItemException {
        if (!this.hasSecondWord()) {
            pInterface.println("Vous devez spécifier l'pkg_item à prendre.");
            return;
        }

        Item vItem = pPlayer.takeItem(this.getSecondWord());

        pInterface.printf("Vous avez bien récupéré %s.", vItem.getName());
        pInterface.println();
    }

}
