package hotstone.standard;

import hotstone.framework.*;
import hotstone.framework.Strategies.PowerStrategy;

public class StandardHero implements MutableHero {

    private int mana; // Initial mana.
    private int health;
    private boolean usedPower = false;
    private final String heroType;
    private final PowerStrategy powerStrategy;
    private final Player owner;

    public StandardHero(
            Player owner,
            int mana,
            int health,
            String heroType,
            PowerStrategy powerStrategy
    ) {
       this.mana = mana;
       this.health = health;
       this.heroType = heroType;
       this.powerStrategy = powerStrategy;
       this.owner = owner;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public String getPowerName() {
        return powerStrategy.getName();
    }

    @Override
    public int getPowerManaCost() {
        return powerStrategy.getPowerCost();
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public String getType() {
        return heroType;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public String getEffectDescription() {
        return powerStrategy.getDescription();
    }

    @Override
    public Status usePower(MutableGame game) {
        if(!usedPower) {
            if (mana >= powerStrategy.getPowerCost()) {
                usedPower = true;
                mana -= powerStrategy.getPowerCost();
                powerStrategy.usePower(game);
                return Status.OK;
            }
            return Status.NOT_ENOUGH_MANA;
        }
        return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;
    }

    @Override
    public void hurt(int amount) {
        health -= amount;
    }

    @Override
    public boolean isDefeated() {
        return getHealth() <= 0;
    }

    @Override
    public void resetUsedPower() {
        usedPower = false;
    }

    @Override
    public boolean canUsePower() {return usedPower;}

}
