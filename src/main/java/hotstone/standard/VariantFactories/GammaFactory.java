package hotstone.standard.VariantFactories;

import hotstone.framework.Strategies.HeroStrategy;
import hotstone.framework.Strategies.VictoryStrategy;
import hotstone.standard.Strategies.HeroStrategy.GammaHeroStrategy;
import hotstone.standard.Strategies.VictoryStrategy.GammaVictoryStrategy;

public class GammaFactory extends AlphaFactory {

    @Override
    public HeroStrategy makeHeroStrategy() {
        return new GammaHeroStrategy();
    }

    @Override
    public VictoryStrategy makeVictoryStrategy() {
        return new GammaVictoryStrategy();
    }
}
