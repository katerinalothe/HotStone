package hotstone.standard.Strategies.HeroStrategy;

import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.framework.Strategies.HeroStrategy;
import hotstone.framework.Strategies.NumberGeneratorStrategy;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHero;
import hotstone.standard.Strategies.NumberGeneratorStrategy.RandomNumberGeneratorStrategy;
import hotstone.standard.Strategies.PowerStrategy.HeroPower.DanishChefPowerStrategy;
import hotstone.standard.Strategies.PowerStrategy.HeroPower.FrenchChefPowerStrategy;
import hotstone.standard.Strategies.PowerStrategy.HeroPower.ItalianChefPowerStrategy;
import hotstone.standard.Strategies.PowerStrategy.HeroPower.ThaiChefPowerStrategy;

public class SemiHeroStrategy implements HeroStrategy {
    private final NumberGeneratorStrategy generator;

    public SemiHeroStrategy(NumberGeneratorStrategy generator) {
        this.generator = generator;
    }

    @Override
    public MutableHero generate(Player p) {
        final String heroType;
        final PowerStrategy power;

        switch (generator.generate(0,3)) {
            case 0 -> {
                heroType = GameConstants.THAI_CHEF_HERO_TYPE;
                power = new ThaiChefPowerStrategy();
            }
            case 1 -> {
                heroType = GameConstants.DANISH_CHEF_HERO_TYPE;
                power = new DanishChefPowerStrategy();
            }
            case 2 -> {
                heroType = GameConstants.FRENCH_CHEF_HERO_TYPE;
                power = new FrenchChefPowerStrategy(
                        new RandomNumberGeneratorStrategy()
                );
            }
            case 3 -> {
                heroType = GameConstants.ITALIAN_CHEF_HERO_TYPE;
                power = new ItalianChefPowerStrategy(
                        new RandomNumberGeneratorStrategy()
                );
            }
            default -> throw(
                    new RuntimeException("Generated number out of range.")
            );
        }

        return new StandardHero(
                GameConstants.HERO_INITIAL_MANA,
                GameConstants.HERO_MAX_HEALTH,
                heroType,
                power
        );
    }
}
