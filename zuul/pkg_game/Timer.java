package zuul.pkg_game;

/**
 * This class represents a movement constraint based on an internal timer.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Timer {
    /**
     * The remaining moves of timer.
     */
    private int aRemainingMoves;

    /**
     * If the instance is activated or not.
     */
    private boolean aDisabled;

    /**
     * Creates a new disabled Timer.
     */
    public Timer() {
        this(0, true);
    }

    /**
     * Creates a new timer.
     *
     * @param pRemainingMoves The remaining moves that can be made.
     * @param pDisabled       If the counter is disabled.
     */
    public Timer(final int pRemainingMoves, final boolean pDisabled) {
        this.aRemainingMoves = pRemainingMoves;
        this.aDisabled = pDisabled;
    }

    /**
     * Indicates that a new tick has taken place and decrement the internal timer.
     *
     * @throws TimerLimitException If the timer does not allow any more actions.
     */
    public void tick() throws TimerLimitException {
        if (this.aDisabled) return;

        this.aRemainingMoves -= 1;

        if (this.aRemainingMoves < 1) throw new TimerLimitException();
    }

    /**
     * Sets the new remaining moves for the timer.
     *
     * @param pRemainingMoves The new remaining of moves.
     */
    public void setRemainingMoves(final int pRemainingMoves) {
        this.aRemainingMoves = pRemainingMoves;
    }

    /**
     * Gets the remaining moves.
     *
     * @return The remaining moves description.
     */
    public String getRemainingDescription() {
        switch (this.aRemainingMoves) {
            case 0:
                return "Aucun mouvement restant.";

            case 1:
                return "Un seul mouvement restant.";

            default:
                return String.format("Il vous reste %d mouvements.", this.aRemainingMoves);
        }
    }

    /**
     * Gets the state of the timer.
     *
     * @return true if the timer is enabled.
     */
    public boolean isEnabled() {
        return !this.aDisabled;
    }

    /**
     * Sets the activation state of the timer.
     *
     * @param pDisabled true if the timer is disabled, false otherwise.
     */
    public void setDisabled(final boolean pDisabled) {
        this.aDisabled = pDisabled;
    }

    /**
     * Indicates that the timer has completed its countdown.
     */
    public static class TimerLimitException extends Exception {
        TimerLimitException() {
            super("La limite de mouvement est atteinte, vous avez perdu.");
        }
    }
}
