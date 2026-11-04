package hotstone.framework.Strategies;

import hotstone.framework.MutableGame;

/** Strategy for executing a Hero's power and storing associated state. */
public interface PowerStrategy {

    /** Execute the power.
     *
     * @param game the game state to apply the power on.
     */
    void usePower(MutableGame game);

    /** Get the power's name.
     *
     * @return the power's name.
     */
    String getName();

    /** Get the power's description.
     *
     * @return the power's description.
     */
    String getDescription();

    /** Get the power's mana-cost.
     *
     * @return the power's mana-cost.
     */
    int getPowerCost();
}
