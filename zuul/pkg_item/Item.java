package zuul.pkg_item;

import zuul.pkg_game.Engine;
import zuul.pkg_game.Player;
import zuul.pkg_ui.UserInterface;

/**
 * This class defines the representation of an object in the Zuul project.
 * Items can be picked up and dropped in rooms by the player.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Item {
    /**
     * The pkg_item's name.
     */
    private final String aName;

    /**
     * The pkg_item's description.
     */
    private final String aDescription;

    /**
     * The pkg_item's weight.
     */
    private final int aWeight;

    /**
     * Creates a new pkg_item.
     *
     * @param pName        Name of the pkg_item.
     * @param pDescription Description of the pkg_item.
     * @param pWeight      Weight of the pkg_item.
     */
    public Item(String pName, String pDescription, int pWeight) {
        this.aName = pName;
        this.aDescription = pDescription;
        this.aWeight = pWeight;
    }

    /**
     * Uses the current pkg_item.
     *
     * @param pEngine    The pkg_game engine.
     * @param pPlayer    The player using the pkg_command.
     * @param pInterface The user interface used by the pkg_game.
     */
    public void use(final Engine pEngine, Player pPlayer, UserInterface pInterface) {
        throw new UnsupportedOperationException("Cet pkg_item ne peut pas être utilisé.");
    }

    /**
     * Gets pkg_item's name.
     *
     * @return The pkg_item name.
     */
    public String getName() {
        return this.aName;
    }

    /**
     * Gets the weight of the pkg_item.
     *
     * @return The weight.
     */
    public int getWeight() {
        return this.aWeight;
    }

    /**
     * Gets a long description of the pkg_item.
     *
     * @return The pkg_item long description.
     */
    public String getLongDescription() {
        return String.format("%s, %s (poids : %d)",
                this.aName,
                this.aDescription,
                this.aWeight);
    }

    /**
     * This error is related to the fact that an action requested in
     * the pkg_item management is not possible.
     */
    public static class CannotManageItemException extends Exception {
        public CannotManageItemException(final String pMessage) {
            super(pMessage);
        }
    }
}
