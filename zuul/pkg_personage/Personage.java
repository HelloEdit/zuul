package zuul.pkg_personage;

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
     * Gets a long description about the personage.
     *
     * @return A description of the personage.
     */
    public String getLongDescription() {
        return String.format("%s est %s.", this.aName, this.aDescription);
    }
}
