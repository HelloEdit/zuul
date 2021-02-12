/**
 * A command represents a particular instruction asked by the user.
 * This takes the form of two separate words, one for the command and a second one for the argument.
 */
public class Command {
    private final String aCommandWord;
    private final String aSecondWord;

    /**
     * Creates a new Command.
     *
     * @param pFirst  Command word.
     * @param pSecond a second word.
     */
    public Command(final String pFirst, final String pSecond) {
        this.aCommandWord = pFirst;
        this.aSecondWord = pSecond;
    }

    /**
     * Checks if this command has a non null second word.
     *
     * @return true if this command has a second word. False if it doesn't.
     */
    public boolean hasSecondWord() {
        return this.aSecondWord != null;
    }

    /**
     * Checks if the command has a unknown command word.
     *
     * @return true if this command has a command word. False if it doesn't.
     */
    public boolean isUnknown() {
        return this.aCommandWord == null;
    }

    /**
     * Access the command word.
     *
     * @return the command word.
     */
    public String getCommandWord() {
        return this.aCommandWord;
    }

    /**
     * Access the second word.
     *
     * @return the second word.
     */
    public String getSecondWord() {
        return this.aSecondWord;
    }
} // Command
