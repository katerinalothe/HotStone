package hotstone.standard;

import hotstone.framework.Player;
import hotstone.framework.Status;

public class StandardHero implements hotstone.framework.Hero {

    private final int powerManaCost = 2;
    private int mana = 3; // initial mana

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
        return 21;
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
        if (mana >= powerManaCost) {
            mana -= powerManaCost;
            return Status.OK;
        }
        return Status.NOT_ENOUGH_MANA;
    }
}
