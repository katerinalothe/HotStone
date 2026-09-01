package hotstone.standard;

import hotstone.framework.Player;
import hotstone.framework.Status;

public class BabyHero implements hotstone.framework.Hero {

    private final int powerManaCost = 2;
    private int mana = 3; // initial mana
    private int health = 21;
    private boolean usedPower = false;

    @Override
    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public String getPowerName() {
        return "Cute";
    }

    @Override
    public int getPowerManaCost() {
        return powerManaCost;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public String getType() {
        return "Baby";
    }

    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public String getEffectDescription() {
        return "Does nothing";
    }

    @Override
    public Status usePower() {
        if(!usedPower) {
            if (mana >= powerManaCost) {
                usedPower = true;
                mana -= powerManaCost;
                return Status.OK;
            }
            return Status.NOT_ENOUGH_MANA;
        }
        return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;
    }

    public void hurt(int amount) {
        health -= amount;
    }

    public boolean isDefeated() {
        return getHealth() <= 0;
    }

    public void resetUsedPower() {
        usedPower = false;
    }

}
