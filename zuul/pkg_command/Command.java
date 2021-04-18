package zuul.pkg_command;

import zuul.pkg_game.GameEngine;
import zuul.pkg_game.Player;
import zuul.pkg_ui.UserInterface;

/**
 * A command represents a particular instruction asked by the user.
 * This takes the form of two separate words, one for the command
 * and a second one for the argument.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public abstract class Command {
    /**
     * Name of the command.
     */
    private final String aName;

    /**
     * Indicate if the command is only intended to be used in test.
     */
    private final boolean aIsTestCommand;

    /**
     * The second word provided with the command.
     */
    private String aSecondWord;

    /**
     * Creates a new command object.
     *
     * @param pName          Name of the command.
     * @param pIsTestCommand true if the command is only intended to be used in test context, false otherwise.
     */
    public Command(final String pName, final boolean pIsTestCommand) {
        this.aName = pName;
        this.aIsTestCommand = pIsTestCommand;

        this.aSecondWord = null;
    }

    /**
     * Creates a new command object.
     *
     * @param pName Name of the command.
     */
    public Command(final String pName) {
        this(pName, false);
    }

    /**
     * Gets the second word.
     *
     * @return the second word.
     */
    public String getSecondWord() {
        return this.aSecondWord;
    }

    /**
     * Define the second word of this command (the word
     * entered after the command word). Null indicates that
     * there was no second word.
     *
     * @param pSecondWord The second word.
     */
    public void setSecondWord(final String pSecondWord) {
        this.aSecondWord = pSecondWord;
    }

    /**
     * Checks if a command has a second word
     *
     * @return true if the command has a second word, false otherwise.
     */
    public boolean hasSecondWord() {
        return this.aSecondWord != null;
    }

    /**
     * Execute the command.
     *
     * @param pGameEngine    The game engine.
     * @param pPlayer    The player using the command.
     * @param pInterface The user interface used by the game.
     * @throws Exception If the command execution fail.
     */
    public abstract void execute(final GameEngine pGameEngine, final Player pPlayer, final UserInterface pInterface) throws Exception;

    /**
     * Gets the command name.
     *
     * @return The command name.
     */
    public String getName() {
        return this.aName;
    }

    /**
     * Checks if the command can be executed in the current mode.
     *
     * @param pTestMode true if the test environment is enabled, false otherwise.
     * @return true if the command can be used, false otherwise.
     */
    public boolean isExecutable(boolean pTestMode) {
        // the truth table is the following
        //
        // | isTestCommand | testMode | can be executed |
        // |---------------|----------|-----------------|
        // | true          | true     | true            |
        // | false         | true     | true            |
        // | true          | false    | false           |
        // | false         | false    | true            |
        //
        // It is the truth table of P -> Q <=> P && ~Q

        return !this.aIsTestCommand || pTestMode;
    }
}