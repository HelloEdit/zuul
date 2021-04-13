package zuul.pkg_game;

/**
 * This class represents a movement constraint based on an internal counter.
 */
public class Timer {
    /**
     * The internal counter.
     */
    private int aCounter;

    /**
     * If the instance is activated or not.
     */
    private boolean aDisabled;

    /**
     * Creates a new disabled Timer.
     */
    public Timer() {
        this.aCounter = 0;
        this.aDisabled = true;
    }

    /**
     * Indicates that a new action has taken place & decrement the internal counter.
     *
     * @throws TimerLimitException If the timer does not allow any more actions.
     */
    public void action() throws TimerLimitException {
        if (this.aDisabled) return;

        if (this.aCounter <= 0) throw new TimerLimitException();
    }

    /**
     * Sets the new internal counter.
     *
     * @param pCounter The new counter.
     */
    public void setCounter(final int pCounter) {
        this.aCounter = pCounter;
    }

    /**
     * Sets the activation state of the counter.
     *
     * @param pDisabled true if the counter is disabled, false otherwise.
     */
    public void setDisabled(final boolean pDisabled) {
        this.aDisabled = pDisabled;
    }

    /**
     *
     */
    public static class TimerLimitException extends Exception {
        TimerLimitException() {
            super("La limite de mouvement est atteinte, vous avez perdu.");
        }
    }
}
