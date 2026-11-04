package hotstone.framework.Strategies;

import hotstone.framework.Deck;

/** Strategy for generating a deck. */
public interface DeckStrategy {

    /**
     * Generate a deck.
     *
     * @return a container with some Cards.
     * */
    Deck generate();
}
