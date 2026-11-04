package hotstone.standard.Strategies.PowerStrategy.HeroPower;

import hotstone.framework.MutableGame;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

public class DanishChefPowerStrategy implements PowerStrategy {
    @Override
    public void usePower(MutableGame game) {
        StandardCard card = new StandardCard(
                GameConstants.SOVS_CARD,
                1,1,1
        );

        game.addToField(
                game.getPlayerInTurn(),
                card,
                0
        );

    }

    @Override
    public String getName() {
        return GameConstants.SOVS_CARD;
    }

    @Override
    public String getDescription() {
        return GameConstants.DANISH_CHEF_HERO_POWER_DESCRIPTION;
    }

    @Override
    public int getPowerCost() {
        return GameConstants.HERO_POWER_COST;
    }
}
