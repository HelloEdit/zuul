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
     * The item's name.
     */
    private final String aName;

    /**
     * The item's description.
     */
    private final String aDescription;

    /**
     * The item's weight.
     */
    private final int aWeight;

    /**
     * Creates a new item.
     *
     * @param pName        Name of the item.
     * @param pDescription Description of the item.
     * @param pWeight      Weight of the item.
     */
    public Item(String pName, String pDescription, int pWeight) {
        this.aName = pName;
        this.aDescription = pDescription;
        this.aWeight = pWeight;
    }

    /**
     * Uses the current item.
     *
     * @param pEngine    The game engine.
     * @param pPlayer    The player using the command.
     * @param pInterface The user interface used by the game.
     */
    public void use(final Engine pEngine, Player pPlayer, UserInterface pInterface) {
        throw new UnsupportedOperationException("Cet objet ne peut pas être utilisé.");
    }

    /**
     * Gets item's name.
     *
     * @return The item name.
     */
    public String getName() {
        return this.aName;
    }

    /**
     * Gets the weight of the item.
     *
     * @return The weight.
     */
    public int getWeight() {
        return this.aWeight;
    }

    /**
     * Gets a long description of the item.
     *
     * @return The item long description.
     */
    public String getLongDescription() {
        return String.format("%s, %s (poids : %d)",
                this.aName,
                this.aDescription,
                this.aWeight);
    }

    /**
     * This error is related to the fact that an action requested in
     * the item management is not possible.
     */
    public static class CannotManageItemException extends Exception {
        public CannotManageItemException(final String pMessage) {
            super(pMessage);
        }
    }
}
