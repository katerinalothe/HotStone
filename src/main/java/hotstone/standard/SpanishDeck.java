package hotstone.standard;

import java.util.*;
import hotstone.framework.Card;

public class SpanishDeck {
    private final List < Card > deck = new ArrayList <> ();

    public SpanishDeck() {
        deck.add(new StandardCard(GameConstants.UNO_CARD));
        deck.add(new StandardCard(GameConstants.DOS_CARD));
        deck.add(new StandardCard(GameConstants.TRES_CARD));
        deck.add(new StandardCard(GameConstants.CUATRO_CARD));
        deck.add(new StandardCard(GameConstants.CINCO_CARD));
        deck.add(new StandardCard(GameConstants.SEIS_CARD));
        deck.add(new StandardCard(GameConstants.SIETE_CARD));
    }
    public int getSize() {
        return deck.size();
    }
    public Card getCard(int indexInDeck) {
        return deck.get(indexInDeck);
    }
    public void removeCard() {
        deck.removeFirst();
    }





}
