package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.UIGame;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.standard.VariantFactories.EpsilonFactory;
import hotstone.standard.utilities.FixedGeneratorStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestEpsilonStone {
    private UIGame game;

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new EpsilonFactory(new FixedGeneratorStrategy(0)));
    }

    @Test
    public void shouldRedwineDoNothingIfNoOpponentMinion() {
        // Given a game where opponent field is empty,

        // When FrenchChef uses redwine power,
        Status status = game.usePower();
        // Then it should do nothing (doesn't give error).
        assertThat(status, is(Status.OK));
    }

    @Test
    public void shouldPastaDoNothingIfNoFriendlyMinion() {
        // Given a game where opponent field is empty,
        game.endTurn();
        // When ItalianChef uses pasta power,
        Status status = game.usePower();
        // Then it should do nothing (doesn't give error).
        assertThat(status, is(Status.OK));
    }

    @Test
    public void shouldRedWinePowerRemove2HealthFromRandomOpponentMinion() {
        // Given a game where the opponent has minions on field
        game.endTurn();
        game.playCard(0, 0);
        game.endTurn();
        // When redwine power is used
        Card card = game.getCardInField(Player.computeOpponent(game.getPlayerInTurn()),0);
        int healthBefore = card.getHealth();
        game.usePower();
        int healthAfter = card.getHealth();
        // Then 2 health is removed from random minion of opponent field
        assertThat(healthAfter, is(healthBefore-2));
    }

    @Test
    public void shouldPastaPowerAdd2AttackPowerToRandomFriendlyMinion() {
        // Given a game where the player has minions on field
        game.endTurn();
        game.playCard(0, 0);
        game.endTurn();
        game.endTurn();
        // When pasta power is used
        Card card = game.getCardInField(game.getPlayerInTurn(),0);
        int attackBefore = card.getAttack();
        game.usePower();
        int attackAfter = card.getAttack();

        // Then 2 attack is added to random friendly minion
        assertThat(attackAfter, is(attackBefore+2));
    }
}


