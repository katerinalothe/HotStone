package hotstone.standard;

import hotstone.framework.Deck;
import hotstone.framework.Strategies.DeckStrategy;
import hotstone.standard.Strategies.DeckStrategy.AlphaDeckStrategy;
import hotstone.standard.Strategies.DeckStrategy.DeltaDeckStrategy;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestDeckStrategy {
    private DeckStrategy alphaStrategy;
    private DeckStrategy deltaStrategy;
    @BeforeEach
    public void setUp() {
        alphaStrategy = new AlphaDeckStrategy();
        deltaStrategy = new DeltaDeckStrategy();
    }

    @Test
    public void alphaShouldStratWithLegalDeck() {
        Deck deck = alphaStrategy.generate();

        assertThat(deck.getCard(0).getManaCost() == 1, CoreMatchers.is(true));
        assertThat(deck.getCard(1).getManaCost() == 2, CoreMatchers.is(true));
        assertThat(deck.getCard(2).getManaCost() == 3, CoreMatchers.is(true));
    }

    @Test
    public void deltaShouldStartWithLegalDeck() {
        // Given a deck,
        Deck deck = deltaStrategy.generate();
        // When we check the top three cards,
        // Then they should adhere to the mana-cost specifications.
        assertThat(deck.getCard(0).getManaCost() == 1, CoreMatchers.is(true));
        assertThat(deck.getCard(1).getManaCost() <= 2, CoreMatchers.is(true));
        assertThat(deck.getCard(2).getManaCost() <= 4, CoreMatchers.is(true));
    }
}
