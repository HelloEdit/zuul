import java.util.StringTokenizer;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 * <p>
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 *
 * @author Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2013.09.15
 */
public class Parser {
    /**
     * The Command words validator
     */
    private final CommandWords aValidCommands;

    /**
     * Create a new parser instance
     */
    public Parser() {
        this.aValidCommands = new CommandWords();
    }

    /**
     * Get a command from stdin.
     *
     * @param pInput the text of the command.
     * @return The next command from the user.
     */
    public Command getCommand(final String pInput)
    {
        String vWord1;
        String vWord2;

        StringTokenizer tokenizer = new StringTokenizer(pInput);

        if (tokenizer.hasMoreTokens()) {
            vWord1 = tokenizer.nextToken();
        } else {
            vWord1 = null;
        }

        if ( tokenizer.hasMoreTokens() ) {
            vWord2 = tokenizer.nextToken();
        } else {
            vWord2 = null;
        }

        // note: we just ignore the rest of the input line.

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).

        if (this.aValidCommands.isCommand(vWord1)) {
            return new Command(vWord1, vWord2);
        } else {
            return new Command(null, vWord2);
        }
    }

    /**
     * Gets all the valid command words.
     */
    public String getCommands() {
        return this.aValidCommands.getCommandList();
    }
} // Parser
