package zuul.pkg_personage;

/**
 * This class is intended to represent a pkg_personage of the pkg_game.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Personage {
    /**
     * Name of the pkg_personage.
     */
    private final String aName;

    /**
     * Description of the pkg_personage.
     */
    private final String aDescription;

    /**
     * Creates a new pkg_personage.
     *
     * @param aName        Name of the pkg_personage.
     * @param aDescription Description of the pkg_personage.
     */
    public Personage(final String aName, final String aDescription) {
        this.aName = aName;
        this.aDescription = aDescription;
    }

    /**
     * Gets a long description about the pkg_personage.
     *
     * @return A description of the pkg_personage.
     */
    public String getLongDescription() {
        return String.format("%s est %s.", this.aName, this.aDescription);
    }
}
