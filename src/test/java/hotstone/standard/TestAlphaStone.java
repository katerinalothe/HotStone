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
import hotstone.utility.TestHelper;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import hotstone.framework.Game;

/** Template for your own ongoing TDD process.
 * Fill it out until you have covered all
 * requirements for the alpha stone game.
 */
public class TestAlphaStone {
  private Game game;

  /** Fixture for AlphaStone testing. */
  @BeforeEach
  public void setUp() {
    game = new StandardHotStoneGame();
  }

  @Test
  public void shouldStartAtTurnOne() {
      // Given a new game,
      // Then the turn-counter should start at 1.
      assertThat(game.getTurnNumber(), is(1));
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
      hotstone.framework.Hero hero = game.getHero(Player.FINDUS);
      // Then we should get "Baby".
      assertThat(hero, notNullValue());
      assertThat(hero.getType(), is("Baby"));
  }

  @Test
  public void shouldStartPeddersenWithBabyHero() {
      // Given a new game,
      // When we check Peddersen's hero type,
      hotstone.framework.Hero hero = game.getHero(Player.PEDDERSEN);
      // Then we should get "Baby".
      assertThat(hero, notNullValue());
      assertThat(hero.getType(), is("Baby"));
  }

  @Test
  public void shouldHaveFindusAsFirstPlayer() {
    // Given a new game,
    // When I ask for which player's turn it is,
    Player player = game.getPlayerInTurn();
    // Then it should be Findus.
    assertThat(player, is(Player.FINDUS));

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
    Card cardUno = game.getCardInHand(Player.FINDUS, 0);
    Card cardDos = game.getCardInHand(Player.FINDUS, 1);
    Card cardTres = game.getCardInHand(Player.FINDUS, 2);
    // Then it should be Uno, Dos, Tres
    assertThat(cardUno.getName(), is(GameConstants.UNO_CARD));
    assertThat(cardDos.getName(), is(GameConstants.DOS_CARD));
    assertThat(cardTres.getName(), is(GameConstants.TRES_CARD));
  }

  @Test
  public void shouldBePeddersenTurnAfterFindusTurn() {
    // Given it's Findus' turn
    Player player = game.getPlayerInTurn();
    assertThat(player, is(Player.FINDUS));
    // When Findus' turn ends
    game.endTurn();
    // Then it should be Peddersen's turn
    player = game.getPlayerInTurn();
    assertThat(player, is(Player.PEDDERSEN));
  }

  @Test
  public void shouldBeFindusTurnAfterPeddersenTurn() {
      // Given it's Peddersen's turn
      Player player = game.getPlayerInTurn();
      if (player == Player.FINDUS) {
          game.endTurn();
      }
      // When Peddersen's turn ends
      game.endTurn();
      // Then it should be Findus' turn
      player = game.getPlayerInTurn();
      assertThat(player, is(Player.FINDUS));
  }

  @Test
  public void cardDosHasAttributes222() {
    // Given Card Dos
    Card cardDos = new StandardCard(GameConstants.DOS_CARD);
    // Then it has attributes (2,2,2)
    assertThat(cardDos.getManaCost(), is(2));
    assertThat(cardDos.getHealth(), is(2));
    assertThat(cardDos.getAttack(), is(2));
  }



}
