package zuul.pkg_ui.pkg_console;

import zuul.pkg_ui.UserInterface;

/**
 * Uses the terminal as the primary interface of the pkg_game.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Console implements UserInterface {
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
    public void disable() {}
}
