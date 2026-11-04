package hotstone.standard.Strategies.DeckStrategy;

import hotstone.framework.Card;
import hotstone.framework.Deck;
import hotstone.framework.Strategies.DeckStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;
import hotstone.standard.StandardDeck;

import java.util.ArrayList;
import java.util.List;

public class AlphaDeckStrategy implements DeckStrategy {
    @Override
    public Deck generate() {
        List <StandardCard> deck = new ArrayList<>();
        deck.add(new StandardCard(GameConstants.UNO_CARD,1,1,1));
        deck.add(new StandardCard(GameConstants.DOS_CARD,2,2,2));
        deck.add(new StandardCard(GameConstants.TRES_CARD,3,3,3));
        deck.add(new StandardCard(GameConstants.CUATRO_CARD,2,3,1));
        deck.add(new StandardCard(GameConstants.CINCO_CARD,3,5,1));
        deck.add(new StandardCard(GameConstants.SEIS_CARD,2,1,3));
        deck.add(new StandardCard(GameConstants.SIETE_CARD,3,2,4));
        return new StandardDeck(deck);
    }
}
