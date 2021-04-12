package zuul.pkg_command;

import zuul.pkg_game.Engine;
import zuul.pkg_game.Player;
import zuul.pkg_item.Item;
import zuul.pkg_personage.Personage;
import zuul.pkg_room.Room;
import zuul.pkg_ui.UserInterface;

/**
 * Handles the look pkg_command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class LookCommand extends Command {
    public LookCommand() {
        super("look");
    }

    /**
     * Gets specific information about an pkg_item or a pkg_personage.
     *
     * @param pEngine    The pkg_game engine.
     * @param pPlayer    The player using the pkg_command.
     * @param pInterface The user interface used by the pkg_game.
     */
    @Override
    public void execute(Engine pEngine, Player pPlayer, UserInterface pInterface) {
        if (!this.hasSecondWord()) {
            pEngine.printLocationInfo();
            return;
        }


        String vSearch = this.getSecondWord();

        Item vItem;

        // observer loupe
        // if it is an possessed item
        vItem = pPlayer.getItem(vSearch);
        if (vItem != null) {
            pInterface.println("Vous possédez cet objet.");
            pInterface.println(vItem.getLongDescription());
            return;
        }

        Room vRoom = pPlayer.getRoom();

        // if it is an item in the current pkg_room
        vItem = vRoom.getItem(vSearch);
        if (vItem != null) {
            pInterface.println("Cet objet est dans la pièce.");
            pInterface.println(vItem.getLongDescription());
            return;
        }

        // if it is a character in the current room
        Personage vCharacter = vRoom.getPersonage(vSearch);
        if (vCharacter != null) {
            pInterface.println("Le personnage est dans la pièce.");
            pInterface.println(vCharacter.getLongDescription());
            return;
        }

        pInterface.println("Aucun élément trouvé pour votre demande.");
    }
}
