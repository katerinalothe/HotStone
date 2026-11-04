package hotstone.standard.VariantFactories;

import hotstone.framework.Strategies.DeckStrategy;
import hotstone.framework.Strategies.HeroStrategy;
import hotstone.framework.Strategies.ManaStrategy;
import hotstone.framework.Strategies.VictoryStrategy;
import hotstone.framework.VariantFactory;
import hotstone.standard.Strategies.DeckStrategy.EtaDeckStrategy;
import hotstone.standard.Strategies.HeroStrategy.SemiHeroStrategy;
import hotstone.standard.Strategies.ManaStrategy.BetaManaStrategy;
import hotstone.standard.Strategies.NumberGeneratorStrategy.RandomNumberGeneratorStrategy;
import hotstone.standard.Strategies.VictoryStrategy.BetaVictoryStrategy;

public class SemiFactory implements VariantFactory {
    @Override
    public DeckStrategy makeDeckStrategy() {
        return new EtaDeckStrategy();
    }

    @Override
    public HeroStrategy makeHeroStrategy() {
        return new SemiHeroStrategy(new RandomNumberGeneratorStrategy());
    }

    @Override
    public ManaStrategy makeManaStrategy() {
        return new BetaManaStrategy();
    }

    @Override
    public VictoryStrategy makeVictoryStrategy() {
        return new BetaVictoryStrategy();
    }
}
