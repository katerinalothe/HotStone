package hotstone.framework;

/** A container for Card objects. */
public interface Deck {

    /** Get the size of the Deck.
     *
     * @return the number of Cards in the Deck.
     */
    int getSize();

    /** Get the Card at the given index.
     *
     * @param i the index in range [0, deck_size-1].
     * @return the Card at the index.
     */
    Card getCard(int i);
}
