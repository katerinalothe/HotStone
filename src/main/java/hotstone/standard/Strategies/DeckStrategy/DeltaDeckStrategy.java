package hotstone.standard.Strategies.DeckStrategy;

import hotstone.framework.Card;
import hotstone.framework.Deck;
import hotstone.framework.MutableCard;
import hotstone.framework.Strategies.DeckStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;
import hotstone.standard.StandardDeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DeltaDeckStrategy implements DeckStrategy {

    @Override
    public Deck generate() {
        final HashMap<String,int[]> cards = new HashMap<>();
        cards.put(GameConstants.BROWN_RICE_CARD, new int[]{1,1,2});
        cards.put(GameConstants.FRENCH_FRIES_CARD, new int[]{1,2,1});
        cards.put(GameConstants.GREEN_SALAD_CARD, new int[]{2,2,3});
        cards.put(GameConstants.TOMATO_SALAD_CARD, new int[]{2,3,2});
        cards.put(GameConstants.POKE_BOWL_CARD, new int[]{3,2,4});
        cards.put(GameConstants.PUMPKIN_SOUP_CARD, new int[]{4,2,7});
        cards.put(GameConstants.NOODLE_SOUP_CARD, new int[]{4,5,3});
        cards.put(GameConstants.SPRING_ROLLS_CARD, new int[]{5,3,7});
        cards.put(GameConstants.BAKED_SALMON_CARD, new int[]{5,8,2});
        final ArrayList<MutableCard> deck = new ArrayList<>();

        // Populate the deck with two of each card, then shuffle.
        cards.forEach((String name, int[] values) -> {
            for (int j = 0; j < 2; j++) {
                deck.add(new StandardCard(
                        name, values[0], values[1], values[2]
                ));
            }
        });
        Collections.shuffle(deck);

        // The top card (highest index) must have mana cost == 1.
        // Search for the cards, starting from the bottom. Swap to the correct place if found.
        int size = deck.size();
        for (int i = 0; i < size; i++) {
            Card card = deck.get(i);
            if (card.getManaCost() == 1) {
                Collections.swap(deck, i, 0);
            }
        }
        // Second card -> mana cost <= 2.
        for (int i = 1; i < size; i++) {
            Card card = deck.get(i);
            if (card.getManaCost() <= 2) {
                Collections.swap(deck, i, 1);
            }
        }
        // Third card -> mana cost <= 4.
        for (int i = 2; i < size; i++) {
            Card card = deck.get(i);
            if (card.getManaCost() <= 4) {
                Collections.swap(deck, i, 2);
            }
        }

        return new StandardDeck(deck);

    }

}
