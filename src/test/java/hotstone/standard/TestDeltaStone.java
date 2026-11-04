package hotstone.standard;
import hotstone.framework.*;
import hotstone.framework.Strategies.DeckStrategy;
import hotstone.standard.VariantFactories.DeltaFactory;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDeltaStone {
    private UIGame game;
    private DeckStrategy deckStrategy;

    @BeforeEach
    public void setUp() {
        DeltaFactory factory = new DeltaFactory();
        deckStrategy = factory.makeDeckStrategy();
        game = new StandardHotStoneGame(factory);
    }

    @Test
    public void shouldStartTurnWith5Mana() {
        game.endTurn();
        assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(5));
    }

}
