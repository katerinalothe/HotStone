package hotstone.standard.Strategies.PowerStrategy.HeroPower;

import hotstone.framework.MutableGame;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.GameConstants;

public class BabyPowerStrategy implements PowerStrategy {
    @Override
    public void usePower(MutableGame game) {}

    @Override
    public String getName() {
        return GameConstants.BABY_HERO_POWER_NAME;
    }

    @Override
    public String getDescription() {
        return GameConstants.BABY_HERO_POWER_DESCRIPTION;
    }

    @Override
    public int getPowerCost() {
        return GameConstants.HERO_POWER_COST;
    }
}
