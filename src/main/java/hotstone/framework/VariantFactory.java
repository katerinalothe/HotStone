package hotstone.framework;

import hotstone.framework.Strategies.DeckStrategy;
import hotstone.framework.Strategies.HeroStrategy;
import hotstone.framework.Strategies.ManaStrategy;
import hotstone.framework.Strategies.VictoryStrategy;

/** Factory for generating Hotstone variant-specific components. */
public interface VariantFactory {

    DeckStrategy makeDeckStrategy();
    HeroStrategy makeHeroStrategy();
    ManaStrategy makeManaStrategy();
    VictoryStrategy makeVictoryStrategy();

}
