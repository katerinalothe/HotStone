package hotstone.framework;

import java.util.List;

/** Accessor-only interface for the game object. */
public interface SecureGame {
    // === Accessors for Game state

    /** Get the current round-count.
     *
     * @return the current round-count.
     */
    int getRoundCount();

    /** Get the player who currently can make actions (call mutators) on
     * game.
     *
     * @return the player in turn.
     */
    Player getPlayerInTurn();

    /** Get the hero for the given player.
     * PRECONDITION: 'who' is never null.
     *
     * @param who the owning player
     * @return the hero
     */
    SecureHero getHero(Player who);

    /** Get who has won the game.
     *
     * @return null if game is still progressing, or the winning player
     * if the game has been won.
     */
    Player getWinner();

    /** Get the number of turns played. Starts at turn 0 for Findus,
     * then 1 for Peddersen, 2 for Findus, and so on.
     *
     * @return the turn number of the game progress
     */
    int getTurnNumber();

    /** Get the number of cards still in the drawing deck for a given
     * player.
     * PRECONDITION: 'who' is never null.
     *
     * @param who the player whose deck to inspect
     * @return number of cards left in the deck
     */
    int getDeckSize(Player who);

    /** Get the Card at some index in the Player's Deck.
     *
     * @param who the owner of the Deck.
     * @param indexInDeck the index in the Deck.
     * @return the Card at the given index of the Player's Deck.
     */
    Card getCardInDeck(Player who, int indexInDeck);

    // === Accessors for Hand

    /** Get the card at a given index in the hand.  Index goes from 0 up
     * till 'getHandSize()-1'. A card drawn from the deck to the hand
     * will always enter into the hand at index 0, pushing all other
     * cards to +1 index.
     *
     * PRECONDITION: 'who' is never null.
     * PRECONDITION: indexInHand MUST be in interval 0..handsize-1.
     *
     * @param who the player whose hand to inspect
     * @param indexInHand the index of the card to retrieve. MUST be
     *                    0..handsize-1.
     * @return the card in the hand at that position.
     */
    Card getCardInHand(Player who, int indexInHand);

    /** Get an iterable over the cards in the hand. Convenience method
     * to allow writing code ala
     * 'for (Card c: game.getHand(Player.FINDUS)) { ... }'.
     *
     * Alternatively, if you want streaming:
     * StreamSupport.stream(game.getHand(who).spliterator(), false).
     *
     *
     * PRECONDITION: 'who' is never null.
     *
     * @param who the player owning the hand
     * @return an iterable over the cards in the hand
     */
    Iterable<? extends Card> getHand(Player who);

    /** Get the number of cards in the hand.
     *
     * PRECONDITION: 'who' is never null.
     *
     * @param who the player owning the hand
     * @return the number of cards in hand
     */
    int getHandSize(Player who);

    // === Accessors for Field

    /** Get the card at a given index on the field.  Index goes from 0
     * up till 'getFieldSize()-1'.
     *
     * PRECONDITION: 'who' is never null.
     * PRECONDITION: indexInField MUST be in interval 0..fieldsize-1.
     *
     * @param who the player whose field to inspect
     * @param indexInField the index of the card to retrieve. MUST be
     *                    0..fieldsize-1.
     * @return the card on the field at that position.
     */
    Card getCardInField(Player who, int indexInField);

    /** Get an iterable over the cards on the field. Convenience method
     * to allow writing code ala
     * 'for (Card c: game.getField(Player.FINDUS)) { ... }'.
     *
     * Alternatively, if you want streaming:
     * StreamSupport.stream(game.getField(who).spliterator(), false).
     *
     * PRECONDITION: 'who' is never null.
     *
     * @param who the player owning the field
     * @return an iterable over the cards on the field
     */
    List<? extends Card> getField(Player who);

    /** Get the number of cards on the field.
     *
     * PRECONDITION: 'who' is never null.
     *
     * @param who the player owning the field
     * @return the number of cards on field
     */
    int getFieldSize(Player who);

}
