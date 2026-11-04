package hotstone.framework;

import java.util.List;

/** Interface for directly mutating Game state. */
public interface MutableGame extends SecureGame {

    /** Add some card to some index in the Player's field.
     *
     * @param p the Player receiving the Card.
     * @param c the Card to add.
     * @param index the field-index the Card will be placed at.
     */
    void addToField(Player p, MutableCard c, int index);

    void drawCard();

    /** Hurt a Card by some amount.
     *
     * @param c the Card to hurt.
     * @param amount the amount of damage to inflict.
     */
    void hurtCard(MutableCard c, int amount);

    /** Hurt a Hero by some amount.
     *
     * @param p the owner of the target Hero.
     * @param amount the amount of damage to inflict.
     */
    void hurtHero(Player p, int amount);

    @Override
    List<MutableCard> getField(Player p);

    @Override
    MutableHero getHero(Player p);

    @Override
    MutableCard getCardInDeck(Player p, int indexInDeck);

    @Override
    MutableCard getCardInHand(Player p, int indexInHand);

    @Override
    MutableCard getCardInField(Player p, int indexInField);



}
