package hotstone.standard.Strategies.DeckStrategy;

import hotstone.framework.Card;
import hotstone.framework.Deck;
import hotstone.framework.Strategies.DeckStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;
import hotstone.standard.StandardDeck;
import hotstone.standard.Strategies.NumberGeneratorStrategy.RandomNumberGeneratorStrategy;
import hotstone.standard.Strategies.PowerStrategy.CardPower.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EtaDeckStrategy implements DeckStrategy {
    public Deck generate() {
        List<StandardCard> deck = new ArrayList<>();
        for(int i = 0; i < 2; i++) {
            deck.add(new StandardCard(GameConstants.BROWN_RICE_CARD, 1, 1, 1, new BrownRicePowerStrategy()));
            deck.add(new StandardCard(GameConstants.FRENCH_FRIES_CARD, 2, 2, 2));
            deck.add(new StandardCard(GameConstants.GREEN_SALAD_CARD, 3, 3, 3));
            deck.add(new StandardCard(GameConstants.TOMATO_SALAD_CARD, 2, 2, 2, new TomatoSaladPowerStrategy(new RandomNumberGeneratorStrategy())));
            deck.add(new StandardCard(GameConstants.POKE_BOWL_CARD, 3, 2, 3, new PokeBowlPowerStrategy()));
            deck.add(new StandardCard(GameConstants.PUMPKIN_SOUP_CARD, 2, 1, 3));
            deck.add(new StandardCard(GameConstants.NOODLE_SOUP_CARD, 4, 5, 3, new NoodleSoupPowerStrategy()));
            deck.add(new StandardCard(GameConstants.SPRING_ROLLS_CARD, 5, 3, 5, new SpringRollsPowerStrategy(new RandomNumberGeneratorStrategy())));
            deck.add(new StandardCard(GameConstants.BAKED_SALMON_CARD, 5, 7, 6, new BakedSalmonPowerStrategy(new RandomNumberGeneratorStrategy())));
        }
        Collections.shuffle(deck);
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
