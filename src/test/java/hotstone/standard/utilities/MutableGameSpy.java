package hotstone.standard.utilities;

import hotstone.framework.*;

import java.util.List;

public class MutableGameSpy implements Spy, UIGame, MutableGame {

    private final CardSpy cardSpy;

    private GameAction lastActionType;
    private int lastParams;

    public MutableGameSpy(CardSpy cardSpy) {
        this.cardSpy = cardSpy;
    }

    public CardSpy getCardSpy() {
        return cardSpy;
    }

    public GameAction getLastActionType() {
        return lastActionType;
    }

    public int getLastActionParams() {
        return lastParams;
    }

    @Override
    public void addToField(Player p, MutableCard c, int index) {

    }

    @Override
    public void drawCard() {
        lastActionType = GameAction.DRAW_CARD;
    }

    @Override
    public void hurtCard(MutableCard c, int amount) {
        c.setHealth(c.getHealth() - amount);
    }

    @Override
    public void hurtHero(Player p, int amount) {
        lastActionType = GameAction.CHANGE_HERO_HEALTH;
        lastParams = -amount;
    }

    @Override
    public List<MutableCard> getField(Player p) {
        return List.of();
    }

    @Override
    public int getFieldSize(Player who) {
        return 1;
    }

    @Override
    public int getRoundCount() {
        return 0;
    }

    @Override
    public Player getPlayerInTurn() {
        return null;
    }

    @Override
    public MutableHero getHero(Player p) {
        return null;
    }

    @Override
    public Player getWinner() {
        return null;
    }

    @Override
    public int getTurnNumber() {
        return 0;
    }

    @Override
    public int getDeckSize(Player who) {
        return 0;
    }

    @Override
    public MutableCard getCardInDeck(Player p, int indexInDeck) {
        return null;
    }

    @Override
    public MutableCard getCardInHand(Player p, int indexInHand) {
        return null;
    }

    @Override
    public Iterable<? extends Card> getHand(Player who) {
        return null;
    }

    @Override
    public int getHandSize(Player who) {
        return 0;
    }

    @Override
    public MutableCard getCardInField(Player p, int indexInField) {
        return cardSpy;
    }

    @Override
    public void endTurn() {

    }

    @Override
    public Status playCard(int handIndex, int fieldIndex) {
        return null;
    }

    @Override
    public Status attackCard(int ownFieldIndex, int oppFieldIndex) {
        return null;
    }

    @Override
    public Status attackHero(int fieldIndex) {
        return null;
    }

    @Override
    public Status usePower() {
        return null;
    }

    @Override
    public void addObserver(GameObserver observer) {

    }
}
