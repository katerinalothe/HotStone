/*
 * Copyright (C) 2022 - 2026. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hotstone.standard;

import hotstone.framework.*;
import hotstone.framework.Strategies.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is the 'temporary test stub' in TDD
 * terms: the initial empty but compilable implementation
 * of the game interface.
 * <p>
 * It already includes a bit of FAKE-IT code for the first
 * test case about hand management and player in turn.
 * <p>
 * Start solving the AlphaStone exercise by
 * following the TDD rhythm: pick a one-step-test
 * from your test list, quickly add a test,
 * run it to see it fail, and then modify this
 * implementing class (and supporting classes)
 * to make your test case run. Refactor and repeat.
 * <p>
 * While this is the implementation of Game for
 * the AlphaStone game, you will constantly
 * refactor it over the course of the exercises
 * to become the 'core implementation' which will
 * enable a lot of game variants. This is also
 * why it is not called 'AlphaGame'.
 */

public class StandardHotStoneGame implements UIGame,MutableGame {

    private final MutableHero findus;
    private final MutableHero peddersen;
    private final HashMap<Player, StandardDeck> deck = new HashMap<>();
    private final HashMap<Player, ArrayList<MutableCard>> hand = new HashMap<>();
    private final HashMap<Player, ArrayList<MutableCard>> field = new HashMap<>();
    private final ObserverHandler observerHandler = new ObserverHandler();
    private int turnNumber = 0;

    private final ManaStrategy manaStrategy;
    private final VictoryStrategy victoryStrategy;



    public StandardHotStoneGame(VariantFactory variant) {

        manaStrategy = variant.makeManaStrategy();
        victoryStrategy = variant.makeVictoryStrategy();

        DeckStrategy deckStrategy = variant.makeDeckStrategy();
        HeroStrategy heroStrategy = variant.makeHeroStrategy();

        deck.put(Player.FINDUS, (StandardDeck) deckStrategy.generate());
        deck.put(Player.PEDDERSEN, (StandardDeck) deckStrategy.generate());
        hand.put(Player.FINDUS, new ArrayList<>());
        hand.put(Player.PEDDERSEN, new ArrayList<>());
        field.put(Player.FINDUS, new ArrayList<>());
        field.put(Player.PEDDERSEN, new ArrayList<>());

        for (int i = 0; i < deck.get(Player.FINDUS).getSize(); i++) {
            deck.get(Player.FINDUS).getCard(i).setOwner(Player.FINDUS);
        }
        for (int i = 0; i < deck.get(Player.PEDDERSEN).getSize(); i++) {
            deck.get(Player.PEDDERSEN).getCard(i).setOwner(Player.PEDDERSEN);
        }

        findus = heroStrategy.generate(Player.FINDUS);
        peddersen = heroStrategy.generate(Player.PEDDERSEN);

        // draws for findus
        for (int i = 0; i < 3; i++) {
            drawCard();
        }
    }

    @Override
    public Player getPlayerInTurn() {
        if(turnNumber%2 == 0) return Player.FINDUS;
        return Player.PEDDERSEN;
    }

    @Override
    public MutableHero getHero(Player who) {
        if (who == Player.PEDDERSEN) return peddersen;
        else return findus;
    }

    @Override
    public Player getWinner() {
        Player player = victoryStrategy.getWinner(this);
        if (player != null) {
            observerHandler.notifyGameWon(player);
        }
        return player;
    }

    @Override
    public int getTurnNumber() {
        return turnNumber;
    }

    @Override
    public int getRoundCount() {
        return (int) Math.floor((float) turnNumber / 2) + 1;
    }

    @Override
    public int getDeckSize(Player who) {
        return deck.get(who).getSize();
    }

    @Override
    public MutableCard getCardInDeck(Player who, int indexInDeck) {
        return deck.get(who).getCard(indexInDeck);
    }

    @Override
    public MutableCard getCardInHand(Player who, int indexInHand) {
        return hand.get(who).get(indexInHand);
    }

    @Override
    public MutableCard getCardInField(Player who, int indexInField) {
        return field.get(who).get(indexInField);
    }

    @Override
    public List<MutableCard> getHand(Player who) {
        return hand.get(who);
    }

    @Override
    public int getHandSize(Player who) {
        return hand.get(who).size();
    }

    @Override
    public List<MutableCard> getField(Player who) {
        return field.get(who);
    }

    @Override
    public int getFieldSize(Player who) {
        return getField(who).size();
    }

    @Override
    public void endTurn() {
        // check if winning condtition has happend
        turnNumber++;

        MutableHero currentHero = getHero(getPlayerInTurn());
        // Ensure Heroes start with initial mana.
        if (getTurnNumber() < 2) {
            currentHero.setMana(GameConstants.HERO_INITIAL_MANA);
        } else {
            currentHero.setMana(manaStrategy.calculate(getRoundCount()));
        }
        currentHero.resetUsedPower();
        for(MutableCard c: getField(getPlayerInTurn())) {
            c.setActive(true);
        }
        // Second player draws three cards on their first turn.
        if (getTurnNumber() <= 1) {
            for (int i = 0; i < 3; i++) {
                drawCard();
            }

        } else drawCard();

        observerHandler.notifyChangeTurnTo(getPlayerInTurn());
        getWinner();
    }

    @Override
    public void drawCard() {
        Player who = getPlayerInTurn();
        boolean isDeckEmpty = this.getDeckSize(who) <= 0;
        if (isDeckEmpty) {
            MutableHero hero = getHero(who);
            hero.hurt(GameConstants.HERO_HEALTH_PENALTY_ON_EMPTY_DECK);
            return;
        }
        MutableCard card = getCardInDeck(who, 0);
        hand.get(who).addFirst(card);
        deck.get(who).removeCard();
        observerHandler.notifyCardDraw(who, card);
    }

    @Override
    public Status attackCard(int ownFieldIndex, int oppFieldIndex) {
        MutableCard attackingCard = getCardInField(getPlayerInTurn(), ownFieldIndex);
        MutableCard defendingCard = getCardInField(Player.computeOpponent(getPlayerInTurn()), oppFieldIndex);

        if (!attackingCard.isActive()) { return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION; }
        attackingCard.setActive(false);
        hurtCard(attackingCard, defendingCard.getAttack());
        hurtCard(defendingCard, attackingCard.getAttack());
        observerHandler.notifyAttackCard(getPlayerInTurn(), attackingCard, defendingCard);
        return Status.OK;
    }

    @Override
    public void hurtCard(MutableCard card, int amount) {
        card.setHealth(card.getHealth() - amount);
        observerHandler.notifyCardUpdate(card);
        if (card.getHealth() <= 0) {
            removeFromField(card.getOwner(), card);
        }
    }

    private void removeFromField(Player who, MutableCard card) {
        field.get(who).remove(card);
        observerHandler.notifyCardRemove(who, card);
    }

    @Override
    public Status attackHero(int fieldIndex) {
        Player playerInTurn = getPlayerInTurn();
        MutableCard attackingCard = getCardInField(getPlayerInTurn(), fieldIndex);

        if (!attackingCard.isActive()) { return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
        }
        attackingCard.setActive(false);
        hurtHero(Player.computeOpponent(playerInTurn), attackingCard.getAttack());
        return Status.OK;
    }

    @Override
    public void hurtHero(Player p, int amount) {
        getHero(p).hurt(amount);
        observerHandler.notifyHeroUpdate(p);
    }

    @Override
    public Status usePower() {
        Player player = getPlayerInTurn();
        MutableHero currentHero = getHero(player);
        Status status = currentHero.usePower(this);
        if (status == Status.OK) {
            observerHandler.notifyUsePower(player);
            observerHandler.notifyHeroUpdate(player);
        }
        return status;
    }

    @Override
    public Status playCard(int handIndex, int fieldIndex) {
        Player who = getPlayerInTurn();
        MutableHero hero = getHero(who);
        MutableCard card = getCardInHand(who, handIndex);

        if (hero.getMana() < card.getManaCost()) {
            return Status.NOT_ENOUGH_MANA;
        }
        observerHandler.notifyPlayCard(getPlayerInTurn(), card, fieldIndex);
        card.usePower(this);
        addToField(who, hand.get(who).remove(handIndex), fieldIndex);
        hero.setMana(hero.getMana() - card.getManaCost());
        observerHandler.notifyHeroUpdate(getPlayerInTurn());
        return Status.OK;
    }

    @Override
    public void addToField(Player who, MutableCard card, int index) {
        field.get(who).add(index, card);
        observerHandler.notifyPlayCard(who, card, index);
    }

    @Override
    public void addObserver(GameObserver observer) {
        observerHandler.addObserver(observer);
    }
}

