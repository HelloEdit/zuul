package zuul.pkg_item;

import zuul.pkg_game.Engine;
import zuul.pkg_game.Player;
import zuul.pkg_ui.UserInterface;

/**
 * The cookie is an item that increase the maximum weight that can be carried by the player.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Cookie extends Item {
    public final static int WEIGHT_ADDED = 10;

    /**
     * Creates the cookie.
     */
    public Cookie() {
        super("cookie", "Un cookie qui semble... particulier.", 2);
    }

    /**
     * Uses the cookie and modify the maximum weight that the player can carry.
     *
     * @param pEngine    The game engine.
     * @param pPlayer    The player using the command.
     * @param pInterface The user interface used by the game.
     */
    @Override
    public void use(final Engine pEngine, Player pPlayer, UserInterface pInterface) {
        Player vPlayer = pEngine.getPlayer();

        int vNewMax = vPlayer.getMaxWeight() + WEIGHT_ADDED;

        vPlayer.setMaxWeight(vNewMax);
        vPlayer.deleteItem(this.getName());
    }
}