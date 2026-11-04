package hotstone.standard.VariantFactories;

import hotstone.framework.Strategies.ManaStrategy;
import hotstone.framework.Strategies.VictoryStrategy;
import hotstone.standard.Strategies.ManaStrategy.BetaManaStrategy;
import hotstone.standard.Strategies.VictoryStrategy.BetaVictoryStrategy;

public class BetaFactory extends AlphaFactory {

    @Override
    public ManaStrategy makeManaStrategy() {
        return new BetaManaStrategy();
    }

    @Override
    public VictoryStrategy makeVictoryStrategy() {
        return new BetaVictoryStrategy();
    }
}
