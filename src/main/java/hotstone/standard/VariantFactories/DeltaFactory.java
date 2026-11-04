package hotstone.standard.VariantFactories;

import hotstone.framework.Strategies.DeckStrategy;
import hotstone.framework.Strategies.ManaStrategy;
import hotstone.standard.Strategies.DeckStrategy.DeltaDeckStrategy;
import hotstone.standard.Strategies.ManaStrategy.DeltaManaStrategy;

public class DeltaFactory extends AlphaFactory {

    @Override
    public DeckStrategy makeDeckStrategy() {
        return new DeltaDeckStrategy();
    }

    @Override
    public ManaStrategy makeManaStrategy() {
        return new DeltaManaStrategy();
    }
}
