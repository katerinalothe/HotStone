package hotstone.framework;

/** Interface for mutating Card state. */
public interface MutableCard extends Card {

    /** Set the Card's health.
     *
     * @param amount the new health amount.
     */
    void setHealth(int amount);

    void usePower(MutableGame game);

    /** Set the Card's attack power.
     *
     * @param value the new attack power.
     */
    void setAttack(int value);

    /** Set the Card's Active state.
     *
     * @param state whether the Card is active.
     */
    void setActive(boolean state);

    /** Set the Card's owner Player.
     *
     * @param p the Card's new owner.
     */
    void setOwner(Player p);

}
