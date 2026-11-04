package hotstone.framework;

/** Interface for mutating Hero state. */
public interface MutableHero extends SecureHero {

    /** Set the mana to some value.
     *
     * @param amount the new mana amount.
     */
    void setMana(int amount);

    /** Restore the Hero's ability to use their power. */
    void resetUsedPower();

    /** Subtract some amount of the Hero's health.
     *
     * @param amount the amount of health to subtract.
     */
    void hurt(int amount);

    /**
     * If Hero has enough mana to use the power, it gets used.
     *
     * @return OK if it was used, NOT_ENOUGH_MANA if there wasn't enough mana to use the power.
     */
    Status usePower(MutableGame game);

}
