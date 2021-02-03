/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2019.09.25
 */
public class CommandWords {
    // a static constant array that will hold the valid commands words
    private final static String[] aValidCommands =
            { "go", "quit", "help", "look", "inspect" };

    /**
     * Shows all possible command word on System.out.
     */
    public void showAll() {
        System.out.print("\t");
        for (String command: CommandWords.aValidCommands) {
            System.out.print(command + " ");
        }
        System.out.println();
    }

    /**
     * Check whether a given String is a valid command word.
     *
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(final String pString) {
        for (int i = 0; i < CommandWords.aValidCommands.length; i++) {
            if (CommandWords.aValidCommands[i].equals(pString))
                return true;
        } // for
        // if we get here, the string was not found in the commands
        return false;
    } // isCommand()
} // CommandWords