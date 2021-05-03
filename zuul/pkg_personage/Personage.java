package zuul.pkg_personage;

import zuul.pkg_game.Player;
import zuul.pkg_ui.UserInterface;

/**
 * This class is intended to represent a personage of the game.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Personage {
    /**
     * Name of the personage.
     */
    private final String aName;

    /**
     * Description of the personage.
     */
    private final String aDescription;

    /**
     * Creates a new personage.
     *
     * @param aName        Name of the personage.
     * @param aDescription Description of the personage.
     */
    public Personage(final String aName, final String aDescription) {
        this.aName = aName;
        this.aDescription = aDescription;
    }

    /**
     * Allows you to talk with a personage.
     *
     * @param pInterface GUI of the game.
     * @param pPlayer    Player that want to talk with the personage.
     */
    public void dialogWith(final UserInterface pInterface, final Player pPlayer) {
        pInterface.println("Excusez-moi mais je n'ai rien à vous dire...");
    }

    /**
     * Gets a long description about the personage.
     *
     * @return A description of the personage.
     */
    public String getLongDescription() {
        return String.format("%s est %s.", this.aName, this.aDescription);
    }
}
