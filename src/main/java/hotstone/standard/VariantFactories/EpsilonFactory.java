package hotstone.standard.VariantFactories;

import hotstone.framework.Strategies.*;
import hotstone.standard.Strategies.HeroStrategy.EpsilonHeroStrategy;

public class EpsilonFactory extends AlphaFactory {
    private NumberGeneratorStrategy generatorStrategy;

    public EpsilonFactory(NumberGeneratorStrategy generatorStrategy) {
        this.generatorStrategy = generatorStrategy;
    }

    @Override
    public HeroStrategy makeHeroStrategy() {
        return new EpsilonHeroStrategy(generatorStrategy);
    }

}

