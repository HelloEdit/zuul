package zuul.pkg_item;

import zuul.pkg_game.GameEngine;
import zuul.pkg_game.Player;
import zuul.pkg_ui.UserInterface;

/**
 * The cookie is an item that increase the maximum weight that can be carried by the player.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Cookie extends Item {
    /**
     * Weight added by the cookie.
     */
    public final static int WEIGHT_ADDED = 10;

    /**
     * Create a magic cookie item.
     * @param pName Name of the magic cookie.
     * @param pDescription Description of the magic cookie.
     */
    public Cookie(final String pName, final String pDescription) {
        super(pName, pDescription, 2);
    }

    /**
     * Uses the cookie and modify the maximum weight that the player can carry.
     *
     * @param pGameEngine The game engine.
     * @param pPlayer     The player using the command.
     * @param pInterface  The user interface used by the game.
     */
    @Override
    public void use(final GameEngine pGameEngine, Player pPlayer, UserInterface pInterface) {
        Player vPlayer = pGameEngine.getPlayer();

        int vNewMax = vPlayer.getMaxWeight() + WEIGHT_ADDED;

        vPlayer.setMaxWeight(vNewMax);
        vPlayer.deleteItem(this.getName());

        pInterface.println("C'était délicieux ! Ca vous a donné de la force !");
    }
}
