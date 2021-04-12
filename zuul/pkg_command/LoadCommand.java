package zuul.pkg_command;

import zuul.pkg_game.Engine;
import zuul.pkg_game.Player;
import zuul.pkg_item.Beamer;
import zuul.pkg_item.Item;
import zuul.pkg_ui.UserInterface;

/**
 * Handles the charge command.
 */
public class LoadCommand extends Command {
    public LoadCommand() {
        super("load");
    }

    @Override
    public void execute(Engine pEngine, Player pPlayer, UserInterface pInterface) {
        if (!this.hasSecondWord()) {
            pInterface.println("Vous devez préciser l'objet à charger.");
            return;
        }

        Item vItem = pPlayer.getItem(this.getSecondWord());

        if (vItem == null) {
            pInterface.println("Vous ne possédez pas cet objet.");
            return;
        }

        if (!(vItem instanceof Beamer)) {
            pInterface.println("Cet objet ne peut pas être chargé.");
            return;
        }

        Beamer vBeamer = (Beamer) vItem;
        vBeamer.setSavedLocationLocation(pPlayer.getRoom());

        pInterface.printf("%s est bien chargé !", vItem.getName());
        pInterface.println();
    }
}
