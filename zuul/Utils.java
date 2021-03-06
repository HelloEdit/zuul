package zuul;

import java.util.Random;

/**
 * This class is meant to provide simple utilities functions for the Zuul game.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Utils {
    /**
     * Capitalizes the first letter of the input.
     *
     * @param pInput String to be capitalized.
     * @return capitalized string.
     */
    public static String capitalize(final String pInput) {
        return pInput.substring(0, 1).toUpperCase() + pInput.substring(1);
    }

    /**
     * Checks if a string is usable for the program.
     *
     * @param pInput String to be checked.
     * @return true if the string is usable, false otherwise.
     */
    public static boolean isCorrectString(final String pInput) {
        return pInput != null && !pInput.isEmpty() && !pInput.isBlank();
    }

    /**
     * Returns a random element from the array.
     *
     * @param pArray The array from which an element will be chosen randomly.
     * @param <T>    The type of the elements of the array.
     * @return The random element chosen.
     */
    public static <T> T randomElement(final T[] pArray) {
        int index = new Random().nextInt(pArray.length);
        return pArray[index];
    }
}
