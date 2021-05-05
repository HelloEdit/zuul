package zuul.pkg_personage;

import zuul.Utils;
import zuul.pkg_game.GameEngine;
import zuul.pkg_game.Player;
import zuul.pkg_ui.UserInterface;

/**
 * Represents a non-player character.
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
     * Basics dialogs
     */
    private final static String[] dialogs = {
            "Je n'ai rien à vous dire...",
            "Pardon vous-disiez ?",
            "Bonjour!",
            "Qu'est ce que vous dites ?",
            "J'ai du travail laissez moi."
    };

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
     * @param pEngine    Game engine.
     * @param pInterface GUI of the game.
     * @param pPlayer    Player that want to talk with the personage.
     */
    public void dialog(GameEngine pEngine, final UserInterface pInterface, final Player pPlayer) {
        pInterface.printf("[%s] %s", this.aName, Utils.randomElement(dialogs));
        pInterface.println();
    }

    /**
     * Gets a long description about the personage.
     *
     * @return A description of the personage.
     */
    public String getLongDescription() {
        return String.format("%s est %s.", this.aName, this.aDescription);
    }

    /**
     * Gets the personage's name.
     *
     * @return The personage's name.
     */
    public String getName() {
        return this.aName;
    }
}
