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

/**
 * Skeleton class for AlphaStone test cases
 *
 *    This source code is from the book
 *      "Flexible, Reliable Software:
 *        Using Patterns and Agile Development"
 *      2nd Edition
 *    Author:
 *      Henrik Bærbak Christensen
 *      Department of Computer Science
 *      Aarhus University
 */

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.Status;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import hotstone.framework.Game;

/** Template for your own ongoing TDD process.
 * Fill it out until you have covered all
 * requirements for the alpha stone game.
 */
public class TestAlphaStone {
  private Game game;
  private Player playerInTurn;

  /** Fixture for AlphaStone testing. */
  @BeforeEach
  public void setUp() {
    game = new StandardHotStoneGame();
    playerInTurn = game.getPlayerInTurn();
  }

  @Test
  public void shouldStartAtTurnOne() {
      // Given a new game,
      // Then the turn-counter should start at 1.
      assertThat(game.getTurnNumber(), is(0));
  }

  @Test
  public void shouldIncreaseTurnCounterWhenTurnEnds() {
      // Given a current turn number X,
      int turnNumber = game.getTurnNumber();
      // When the turn ends,
      game.endTurn();
      // X should increase by 1.
      assertThat(game.getTurnNumber(), is(turnNumber + 1));
  }

  @Test
  public void shouldStartFindusWithBabyHero() {
      // Given a new game,
      // When we check Findus' hero type,
      BabyHero hero = (BabyHero) game.getHero(Player.FINDUS);
      // Then we should get "Baby".
      assertThat(hero, notNullValue());
      assertThat(hero.getType(), is("Baby"));
  }

  @Test
  public void shouldStartPeddersenWithBabyHero() {
      // Given a new game,
      // When we check Peddersen's hero type,
      BabyHero hero = (BabyHero) game.getHero(Player.PEDDERSEN);
      // Then we should get "Baby".
      assertThat(hero, notNullValue());
      assertThat(hero.getType(), is("Baby"));
  }

  @Test
  public void shouldHaveFindusAsFirstPlayer() {
    // Given a new game,
    // When I ask for which player's turn it is,
    // Then it should be Findus.
    assertThat(playerInTurn, is(Player.FINDUS));

    // Later on, the 'printGameState' can be very handy during development
    // as you can inspect the state of the game (the Given in GWT) and
    // ensure your assumptions are correct before modifying it (When)
    // to test a new feature (Then)

    // TestHelper.printGameState(game);
  }

  // Example of a later, more complex, test case:
  // Card handling.

  // The HotStone specs are quite insisting on how
  // the cards are drawn from the deck and organized
  // in the hand. So when drawing the top three cards
  // from the deck (uno, dos, tres) they have to
  // be organized in the hand as
  // index 0 = tres; index 1 = dos; index 2 = uno.
  // That is, a newly drawn card is 'at the top'
  // of the hand - always entered at position 0
  // and pushing the rest of the cards 1 position
  // 'down'
  @Test
  public void shouldHaveThreeCardsInitially() {
    // Given a game, Findus has 3 cards in hand
    int count = game.getHandSize(Player.FINDUS);
    assertThat(count, is(3));
  }

  @Test
  public void shouldHaveUnoDosTresCardsInitially() {
    // Given a game, Findus has 3 cards in hand.
    // When I check the cards in hand,
    Card cardUno = game.getCardInHand(Player.FINDUS, 2);
    Card cardDos = game.getCardInHand(Player.FINDUS, 1);
    Card cardTres = game.getCardInHand(Player.FINDUS, 0);
    // Then it should be Uno, Dos, Tres
    assertThat(cardUno.getName(), is(GameConstants.UNO_CARD));
    assertThat(cardDos.getName(), is(GameConstants.DOS_CARD));
    assertThat(cardTres.getName(), is(GameConstants.TRES_CARD));
  }

  @Test
  public void shouldBePeddersenTurnAfterFindusTurn() {
    // Given it's Findus' turn
    assertThat(playerInTurn, is(Player.FINDUS));
    // When Findus' turn ends
    game.endTurn();
    // Then it should be Peddersen's turn
    playerInTurn = game.getPlayerInTurn();
    assertThat(playerInTurn, is(Player.PEDDERSEN));
  }

  @Test
  public void shouldBeFindusTurnAfterPeddersenTurn() {
      // Given it's Peddersen's turn
      if (playerInTurn == Player.FINDUS) {
          game.endTurn();
      }
      // When Peddersen's turn ends
      game.endTurn();
      // Then it should be Findus' turn
      playerInTurn = game.getPlayerInTurn();
      assertThat(playerInTurn, is(Player.FINDUS));
  }

  @Test
  public void shouldHaveDeckSizeAs7() {
      // Given Spanish Deck
      SpanishDeck deck = new SpanishDeck();
      // When we check how many cards are in the deck
      // Then there should be 7 cards
      assertThat(deck.getSize(), is(7));
  }

  @Test
  public void shouldBeOrderedUnoDosTresCuatroCincoSeisSiete() {
      // Given the Spanish Deck
      SpanishDeck deck = new SpanishDeck();
      // When we check the order of the cards
      // Then the order, from top to bottom, should be:
      // Uno, Dos, Tres...
      assertThat(deck.getCard(0).getName(), is(GameConstants.UNO_CARD));
      assertThat(deck.getCard(1).getName(), is(GameConstants.DOS_CARD));
      assertThat(deck.getCard(2).getName(), is(GameConstants.TRES_CARD));
      assertThat(deck.getCard(3).getName(), is(GameConstants.CUATRO_CARD));
      assertThat(deck.getCard(4).getName(), is(GameConstants.CINCO_CARD));
      assertThat(deck.getCard(5).getName(), is(GameConstants.SEIS_CARD));
      assertThat(deck.getCard(6).getName(), is(GameConstants.SIETE_CARD));
  }

  @Test
  public void shouldStartWithADeckForEachPlayer() {
      // Given start of game
      // When we check the size of each player's deck
      // Then the size of Peddersen and Findus' should not be 0
      assertThat(game.getDeckSize(Player.PEDDERSEN), not(0));
      assertThat(game.getDeckSize(Player.FINDUS), not(0));
  }

  @Test
  public void shouldAddToTopOfHandWhenDrawingFromDeck() {
      // Given a player's turn
      // When a card is drawn (happens in turn 3)
      Card cardInDeck = game.getCardInDeck(playerInTurn, 0);
      game.drawCard(playerInTurn);
      Card cardInHand = game.getCardInHand(playerInTurn, 0);
      // Then it is added to the hand of the player
      assertThat(cardInDeck, is(cardInHand));
  }

  @Test
  public void shouldRemoveCardFromTopOfDeckWhenDrawn() {
      // Given a player's turn
      // When a card is drawn from the deck (happens in turn 3)
      Card cardTopOfDeck = game.getCardInDeck(playerInTurn, 0);
      game.drawCard(playerInTurn);
      // Then it is removed from the top of the deck
      assertThat(cardTopOfDeck, not(sameInstance(game.getCardInDeck(playerInTurn,0))));
  }

  @Test
  public void shouldNotBeAbleToDrawIfDeckEmpty() {
      // Given an empty deck,
      while (game.getDeckSize(playerInTurn) > 0) {
          game.drawCard(playerInTurn);
      }
      // When a player draws,
      boolean isOK = game.drawCard(playerInTurn);
      // Then they are denied the draw.
      assertThat(isOK, is(false));
  }

  @Test
  public void shouldTakeTwoHeroDamageIfCannotDraw() {
      // Given a player cannot draw
      while (game.getDeckSize(playerInTurn) > 0) {
          game.drawCard(playerInTurn);
      }
      // When a player tries to draw a card
      int healthBefore = game.getHero(playerInTurn).getHealth();
      game.drawCard(playerInTurn);
      int healthAfter = game.getHero(playerInTurn).getHealth();
      // Then hero loses two health
      assertThat(healthAfter, is(healthBefore-2));
  }

  @Test
  public void shouldRemoveCardFromHandWhenPlayed() {
      // Given a player,
      // When they play the first card in hand,
      Card beforeCardInHand = game.getCardInHand(playerInTurn, 0);
      game.playCard(playerInTurn, 0);
      // Then the card is removed from the hand.
      Card afterCardInHand = game.getCardInHand(playerInTurn, 0);
      assertThat(afterCardInHand, not(sameInstance(beforeCardInHand)));
  }

  @Test
  public void shouldHaveFindusAsWinnerOnTurn8() {
      // Given Findus,
      // When its turn 8,
      for(int i= 0; i < 8; i++)
          game.endTurn();

      // Then they win.
      assertThat(game.getTurnNumber(), is(8));
      assertThat(game.getWinner(), is(Player.FINDUS));
  }

  @Test
  public void shouldHaveManaSetTo3AtStartOfEachTurn() {
      // Given a player uses a power and loses mana,
      game.usePower(playerInTurn); // has now 1 mana
      // When it's their turn again,
      game.endTurn();
      game.endTurn();
      // Then their hero's mana is reset to 3
      BabyHero hero = (BabyHero) game.getHero(playerInTurn);
      assertThat(hero.getMana(), is(3));
  }

}
