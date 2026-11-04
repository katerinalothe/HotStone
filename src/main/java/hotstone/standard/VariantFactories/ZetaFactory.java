package hotstone.standard.VariantFactories;
import hotstone.framework.Strategies.ManaStrategy;
import hotstone.framework.Strategies.VictoryStrategy;
import hotstone.standard.Strategies.ManaStrategy.BetaManaStrategy;
import hotstone.standard.Strategies.VictoryStrategy.ZetaVictoryStrategy;

public class ZetaFactory extends AlphaFactory {

    @Override
    public ManaStrategy makeManaStrategy() {
        return new BetaManaStrategy();
    }

    @Override
    public VictoryStrategy makeVictoryStrategy() {
        return new ZetaVictoryStrategy();
    }
}
