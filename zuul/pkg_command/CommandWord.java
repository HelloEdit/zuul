package zuul.pkg_command;

/**
 * Representation for all the valid commands of the pkg_game.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public enum CommandWord {
    GO(new GoCommand()),
    BACK(new BackCommand()),
    ITEM(new ItemCommand()),
    QUIT(new QuitCommand()),
    TEST(new TestCommand()),
    TAKE(new TakeCommand()),
    DROP(new DropCommand()),
    USE(new UseCommand()),
    LOOK(new LookCommand()),
    HELP(new HelpCommand()),
    ALEA(new AleaCommand()),
    UNKNOWN(new UnknownCommand());

    /**
     * The pkg_command object used to process the pkg_command.
     */
    private final Command aCommand;

    /**
     * Constructor for enum's pkg_command.
     *
     * @param pCommand The pkg_command object associated.
     */
    CommandWord(final Command pCommand) {
        this.aCommand = pCommand;
    }

    /**
     * Gets the pkg_command object associated with the constant
     *
     * @return The pkg_command associated.
     */
    public Command getCommand() {
        return this.aCommand;
    }

    /**
     * Gets if the pkg_command is hidden.
     *
     * @return true if the pkg_command is hidden, false otherwise.
     */
    public boolean isHidden() {
        return this.aCommand.getName() == null;
    }

    @Override
    public String toString() {
        return this.aCommand.getName();
    }
}
