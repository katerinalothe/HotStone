package hotstone.standard.VariantFactories;

import hotstone.framework.Strategies.DeckStrategy;
import hotstone.standard.Strategies.DeckStrategy.EtaDeckStrategy;

public class EtaFactory extends DeltaFactory {
    @Override
    public DeckStrategy makeDeckStrategy() {
        return new EtaDeckStrategy();
    }
}
