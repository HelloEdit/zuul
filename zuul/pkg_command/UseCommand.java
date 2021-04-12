package zuul.pkg_command;

import zuul.pkg_game.Engine;
import zuul.pkg_game.Player;
import zuul.pkg_item.Item;
import zuul.pkg_ui.UserInterface;

/**
 * Handles the use pkg_command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class UseCommand extends Command {
    public UseCommand() {
        super("use");
    }

    /**
     * Uses the pkg_item specified.
     *
     * @param pEngine    The pkg_game engine.
     * @param pPlayer    The player using the pkg_command.
     * @param pInterface The user interface used by the pkg_game.
     */
    @Override
    public void execute(Engine pEngine, Player pPlayer, UserInterface pInterface) {
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
            vItem.use(pEngine, pPlayer, pInterface);
        } catch (Exception pError) {
            pInterface.println("Vous ne pouvez pas utiliser cet objet.");
        }
    }

}
