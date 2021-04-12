package zuul.pkg_ui;

/**
 * This interface represents all the constraints that a class must
 * integrate to be the Graphical User Interface (GUI).
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public interface UserInterface {
    /**
     * Prints a string.
     *
     * @param s The String to be printed
     */
    void print(String s);

    /**
     * Terminates the current line by writing the line separator string. The line
     * separator string is defined by the system property line.separator, and
     * is not necessarily a single newline character ('\n').
     */
    void println();

    /**
     * Prints a String and then terminate the line.
     *
     * @param s The text to be printed.
     */
    void println(String s);

    /**
     * A convenience method to write a formatted string to the interface
     * using the specified format string and arguments.
     *
     * @param format A format string as described in Format string syntax
     * @param args   Arguments referenced by the format specifiers in the format string.
     */
    void printf(String format, Object... args);

    /**
     * Causes the interface to terminate.
     */
    void close();

    /**
     * Disables the GUI.
     */
    void disable();
}
