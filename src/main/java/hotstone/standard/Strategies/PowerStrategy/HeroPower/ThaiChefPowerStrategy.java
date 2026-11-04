package hotstone.standard.Strategies.PowerStrategy.HeroPower;

import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.GameConstants;

public class ThaiChefPowerStrategy implements PowerStrategy {
    @Override
    public void usePower(MutableGame g) {
        g.hurtHero(
                Player.computeOpponent(g.getPlayerInTurn()),
                GameConstants.THAI_CHEF_HERO_POWER_ATTACK_OPPONENT_HERO_VALUE
        );
    }

    @Override
    public String getName() {
        return GameConstants.THAI_CHEF_HERO_POWER_NAME;
    }

    @Override
    public String getDescription() {
        return GameConstants.THAI_CHEF_HERO_POWER_DESCRIPTION;
    }

    @Override
    public int getPowerCost() {
        return GameConstants.HERO_POWER_COST;
    }
}
