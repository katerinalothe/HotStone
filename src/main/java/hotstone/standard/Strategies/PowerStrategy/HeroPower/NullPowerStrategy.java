package hotstone.standard.Strategies.PowerStrategy.HeroPower;

import hotstone.framework.MutableGame;
import hotstone.framework.Strategies.PowerStrategy;

public class NullPowerStrategy implements PowerStrategy {

    @Override
    public void usePower(MutableGame game) {

    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public int getPowerCost() {
        return 0;
    }
}
