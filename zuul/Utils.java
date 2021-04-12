package zuul;

/**
 * This class is meant to provide simple utilities functions for the Zuul pkg_game.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Utils {
    /**
     * Capitalizes the first letter of the input.
     *
     * @param pInput string to be capitalized.
     * @return capitalized string.
     */
    public static String capitalize(final String pInput) {
        return pInput.substring(0, 1).toUpperCase() + pInput.substring(1);
    }
}
