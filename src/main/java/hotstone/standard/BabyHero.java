package hotstone.standard;

import hotstone.framework.Player;

public class BabyHero implements hotstone.framework.Hero {
    @Override
    public int getMana() {
        return 0;
    }

    @Override
    public int getHealth() {
        return 21;
    }

    @Override
    public boolean canUsePower() {
        return false;
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
        return "";
    }
}
