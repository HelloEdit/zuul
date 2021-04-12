package zuul.pkg_command;

import java.util.StringTokenizer;

import static zuul.pkg_command.CommandWords.VALID_COMMANDS;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure pkg_game.
 * <p>
 * This parser reads user input and tries to interpret it as an "Adventure"
 * pkg_command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word pkg_command. It returns the pkg_command
 * as an object of class Command.
 * <p>
 * The parser has a set of known pkg_command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a pkg_command object that is marked as an unknown pkg_command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Parser {
    /**
     * Gets a pkg_command from stdin.
     *
     * @param pInput    Text of the pkg_command.
     * @param pTestMode true if the pkg_game is in test mode, false otherwise.
     * @return The pkg_command object to be used.
     */
    public static Command parseCommand(final String pInput, final boolean pTestMode) {
        String vFirstWord = null;
        String vSecondWord = null;

        StringTokenizer vTokenizer = new StringTokenizer(pInput);

        if (vTokenizer.hasMoreTokens())
            vFirstWord = vTokenizer.nextToken();

        if (vTokenizer.hasMoreTokens())
            vSecondWord = vTokenizer.nextToken();

        Command vCommand = CommandWords.getCommand(vFirstWord, pTestMode);
        vCommand.setSecondWord(vSecondWord);

        return vCommand;
    }

    /**
     * Gets the valid commands list string.
     *
     * @return valid commands string.
     */
    public static String getCommandList() {
        return String.join(", ", VALID_COMMANDS.keySet());
    }
}
