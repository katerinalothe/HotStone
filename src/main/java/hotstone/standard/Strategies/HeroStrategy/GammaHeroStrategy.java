package hotstone.standard.Strategies.HeroStrategy;

import hotstone.framework.*;
import hotstone.framework.Strategies.HeroStrategy;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.Strategies.PowerStrategy.HeroPower.DanishChefPowerStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHero;
import hotstone.standard.Strategies.PowerStrategy.HeroPower.ThaiChefPowerStrategy;

public class GammaHeroStrategy implements HeroStrategy {
    @Override
    public MutableHero generate(Player p) {
        String heroType;
        PowerStrategy powerStrategy;

        if(p == Player.FINDUS) {
            heroType = GameConstants.THAI_CHEF_HERO_TYPE;
            powerStrategy = new ThaiChefPowerStrategy();
        } else {
            heroType = GameConstants.DANISH_CHEF_HERO_TYPE;
            powerStrategy = new DanishChefPowerStrategy();
        }

        return new StandardHero(
                GameConstants.HERO_INITIAL_MANA,
                GameConstants.HERO_MAX_HEALTH,
                heroType,
                powerStrategy
        );
    }
}
