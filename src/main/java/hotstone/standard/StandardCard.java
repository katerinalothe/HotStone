package hotstone.standard;

import hotstone.framework.Player;

public class StandardCard implements hotstone.framework.Card {

    private String name;

    public StandardCard(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getManaCost() {
        return 2;
    }

    @Override
    public int getAttack() {
        return 2;
    }

    @Override
    public int getHealth() {
        return 2;
    }

    @Override
    public boolean isActive() {
        return false;
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
