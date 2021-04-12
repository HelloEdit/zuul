package zuul.pkg_ui.pkg_console;

import zuul.pkg_game.Engine;
import zuul.pkg_ui.UserInterface;

import java.util.Scanner;

/**
 * Uses the terminal as the primary interface of the game.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Console implements UserInterface {
    public static void play() {
        Console vConsole = new Console();
        Engine vEngine = new Engine();

        vEngine.setInterface(vConsole);

        Scanner vScanner = new Scanner(System.in);

        do {
            vConsole.print("> ");
            vEngine.processCommand(vScanner.nextLine(), false);
        } while (true);
    }

    @Override
    public void print(String s) {
        System.out.print(s);
    }

    @Override
    public void println() {
        System.out.println();
    }

    @Override
    public void println(final String pInput) {
        System.out.println(pInput);
    }

    @Override
    public void printf(String pFormat, Object... pArgs) {
        System.out.printf(pFormat, pArgs);
    }

    @Override
    public void close() {
        System.exit(0);
    }

    @Override
    public void disable() {
    }
}
