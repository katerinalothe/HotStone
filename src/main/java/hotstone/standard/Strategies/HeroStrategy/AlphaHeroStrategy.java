package hotstone.standard.Strategies.HeroStrategy;

import hotstone.framework.MutableHero;
import hotstone.framework.Strategies.HeroStrategy;
import hotstone.framework.Player;
import hotstone.standard.Strategies.PowerStrategy.HeroPower.BabyPowerStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.Strategies.ManaStrategy.AlphaManaStrategy;
import hotstone.standard.StandardHero;

public class AlphaHeroStrategy implements HeroStrategy {

    @Override
    public MutableHero generate(Player p) {
        AlphaManaStrategy manaStrategy = new AlphaManaStrategy();
        return new StandardHero(
                p,
                manaStrategy.calculate(0),
                GameConstants.HERO_MAX_HEALTH,
                GameConstants.BABY_HERO_TYPE,
                new BabyPowerStrategy()
        );
    }

}
