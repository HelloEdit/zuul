import zuul.pkg_ui.pkg_console.Console;
import zuul.pkg_ui.pkg_javafx.JavaFX;

import java.util.Scanner;

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
public class Game {
    public static void main(String[] args) {
        System.out.println("Which interface do you want to use?");
        System.out.println("[1] JavaFX");
        System.out.println("[2] Console");

        Scanner vScanner = new Scanner(System.in);

        int choice;
        try {
            choice = vScanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error during selection. Aborting.");
            return;
        }

        switch (choice) {
            case 1:
                JavaFX.play();
                break;

            case 2:
                Console.play();
                break;

            default:
                System.out.println("Incorrect choice. Try again.");
        }
    }

    /**
     * Counts from 1 to 100 and records system performance.
     * @hidden
     */
    public static void countFrom0to100() {
        long vStart = System.currentTimeMillis();

        //noinspection StatementWithEmptyBody
        for (int i = 1; i <= 100; i++) {
        }

        long vEnd = System.currentTimeMillis();
        System.out.printf("From 1 to 100 in %dms.", vEnd - vStart);
        System.out.println();
    }
}