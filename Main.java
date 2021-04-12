import zuul.pkg_ui.pkg_javafx.JavaFX;

/**
 * This is the entry point for this Zuul project.
 * <p>
 * The purpose of this class is to correctly initialize the Zuul project
 * according to the GUI to be used. For the moment, 2 different interfaces
 * can be used to play the pkg_game:
 * <ul>
 *   <li>Console</li>
 *   <li>JavaFX</li>
 * </ul>
 *
 * And any others interfaces that implements UserInterface.
 *
 * @see zuul.pkg_ui.UserInterface
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Main {
    public static void main(String[] args) {
        JavaFX.play();
    }

    /**
     * Counts from 1 to 100 and records system performance.
     */
    public static void countFrom0to100() {
        long vStart = System.currentTimeMillis();

        for (int i = 1; i <= 100; i++) {
        }

        long vEnd = System.currentTimeMillis();
        System.out.printf("From 1 to 100 in %dms.", vEnd - vStart);
        System.out.println();
    }
}
