package hotstone.framework.Strategies;

/** Strategy for calculating the initial mana at the beginning of a turn. */
public interface ManaStrategy {

    /** Calculate the initial mana for this turn.
     *
     * @param roundCount the round-count.
     * @return the amount of mana to start with.
     */
    int calculate(int roundCount);
}
