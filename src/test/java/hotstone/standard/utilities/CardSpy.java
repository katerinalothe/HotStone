package hotstone.standard.utilities;

import hotstone.framework.MutableCard;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;

public class CardSpy implements Spy, MutableCard {

    private GameAction lastAction;
    private int lastParams;

    private String name;
    private int mana;
    private int attack;
    private int health;

    public CardSpy(String name, int mana, int attack, int health) {
        this.name = name;
        this.mana = mana;
        this.attack = attack;
        this.health = health;
    }

    @Override
    public GameAction getLastActionType() {
        return lastAction;
    }

    @Override
    public int getLastActionParams() {
        return lastParams;
    }

    @Override
    public void setHealth(int amount) {
        health = amount;

        lastAction = GameAction.SET_CARD_HEALTH;
        lastParams = amount;
    }

    @Override
    public void usePower(MutableGame game) {

    }

    @Override
    public void setAttack(int value) {
        attack = value;

        lastAction = GameAction.SET_ATTACK;
        lastParams = value;
    }

    @Override
    public void setActive(boolean state) {

    }

    @Override
    public void setOwner(Player p) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getManaCost() {
        return mana;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public String getEffectDescription() {
        return "";
    }
}
