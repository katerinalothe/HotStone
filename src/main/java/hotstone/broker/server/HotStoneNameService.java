package hotstone.broker.server;

import hotstone.framework.Card;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HotStoneNameService {

    private final Map<String, Card> cards = new HashMap<>();

    public HotStoneNameService() {}

    // useful for testing
    public void addCard(String id, Card card) {
        cards.put(id, card);
    }

    // makes card id
    public String idFor(Card card) {
        for (Map.Entry<String, Card> entry : cards.entrySet()) {
            if (entry.getValue() == card) {
                return entry.getKey();
            }
        }
        String id = "card-" + UUID.randomUUID();
        cards.put(id, card);
        return id;
    }

    public Card getCard(String id){
        return cards.get(id);
    }

}
