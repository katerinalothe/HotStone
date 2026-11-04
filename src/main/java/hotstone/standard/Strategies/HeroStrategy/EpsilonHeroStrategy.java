package hotstone.standard.Strategies.HeroStrategy;
import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.framework.Strategies.HeroStrategy;
import hotstone.framework.Strategies.NumberGeneratorStrategy;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHero;
import hotstone.standard.Strategies.PowerStrategy.HeroPower.FrenchChefPowerStrategy;
import hotstone.standard.Strategies.PowerStrategy.HeroPower.ItalianChefPowerStrategy;

public class EpsilonHeroStrategy implements HeroStrategy {
    NumberGeneratorStrategy generatorStrategy;

    public EpsilonHeroStrategy(NumberGeneratorStrategy generatorStrategy) {
        this.generatorStrategy = generatorStrategy;
    }

    @Override
    public MutableHero generate(Player p) {
        String heroType;
        PowerStrategy powerStrategy;

        if(p == Player.FINDUS) {
            heroType = GameConstants.FRENCH_CHEF_HERO_TYPE;
            powerStrategy = new FrenchChefPowerStrategy(generatorStrategy);
        } else {
            heroType = GameConstants.ITALIAN_CHEF_HERO_TYPE;
            powerStrategy = new ItalianChefPowerStrategy(generatorStrategy);
        }

        return new StandardHero(
                p,
                GameConstants.HERO_INITIAL_MANA,
                GameConstants.HERO_MAX_HEALTH,
                heroType,
                powerStrategy
        );
    }

}
