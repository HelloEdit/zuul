package zuul.pkg_command;

/**
 * Representation for all the valid commands of the game.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public enum CommandWord {
    GO(new GoCommand()),
    BACK(new BackCommand()),
    ITEM(new ItemCommand()),
    LOAD(new LoadCommand()),
    QUIT(new QuitCommand()),
    TEST(new TestCommand()),
    TAKE(new TakeCommand()),
    TALK(new TalkCommand()),
    DROP(new DropCommand()),
    USE(new UseCommand()),
    LOOK(new LookCommand()),
    HELP(new HelpCommand()),
    ALEA(new AleaCommand()),
    UNKNOWN(new UnknownCommand());

    /**
     * The command object used to process the command.
     */
    private final Command aCommand;

    /**
     * Constructor for enum's command.
     *
     * @param pCommand The command object associated.
     */
    CommandWord(final Command pCommand) {
        this.aCommand = pCommand;
    }

    /**
     * Gets the command object associated with the constant
     *
     * @return The command associated.
     */
    public Command getCommand() {
        return this.aCommand;
    }

    /**
     * Gets if the command is hidden.
     *
     * @return true if the command is hidden, false otherwise.
     */
    public boolean isHidden() {
        return this.aCommand.getName() == null;
    }

    /**
     * Checks if the command can be executed in the current mode.
     *
     * @param pTestMode true if the test environment is enabled, false otherwise.
     * @return true if the command can be used, false otherwise.
     */
    public boolean isExecutableWhen(final boolean pTestMode) {
        return this.aCommand.isExecutableWhen(pTestMode);
    }

    @Override
    public String toString() {
        return this.aCommand.getName();
    }
}
