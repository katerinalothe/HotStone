package hotstone.broker.server;

import hotstone.framework.Card;
import hotstone.framework.SecureHero;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HotStoneNameService {

    private final Map<String, Object> objects = new HashMap<>();

    public HotStoneNameService() {}

    // useful for testing
    public void addCard(String id, Card card) {
        objects.put(id, card);
    }

    public void addHero(String id, SecureHero hero) {
        objects.put(id, hero);
    }

    // Register an object and return its broker ID
    public String idFor(Object object) {
        for (Map.Entry<String, Object> entry : objects.entrySet()) {
            if (entry.getValue() == object) {
                return entry.getKey();
            }
        }
        String prefix = object instanceof SecureHero ? "hero-" : "card-";
        String id = prefix + UUID.randomUUID();
        objects.put(id, object);
        return id;
    }

    public Card getCard(String id){
        return (Card) objects.get(id);
    }

    public SecureHero getHero(String id) {
        return (SecureHero) objects.get(id);
    }

}
