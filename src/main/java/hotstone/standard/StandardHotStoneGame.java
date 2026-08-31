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

/** This is the 'temporary test stub' in TDD
 * terms: the initial empty but compilable implementation
 * of the game interface.
 *
 * It already includes a bit of FAKE-IT code for the first
 * test case about hand management and player in turn.
 *
 * Start solving the AlphaStone exercise by
 * following the TDD rhythm: pick a one-step-test
 * from your test list, quickly add a test,
 * run it to see it fail, and then modify this
 * implementing class (and supporting classes)
 * to make your test case run. Refactor and repeat.
 *
 * While this is the implementation of Game for
 * the AlphaStone game, you will constantly
 * refactor it over the course of the exercises
 * to become the 'core implementation' which will
 * enable a lot of game variants. This is also
 * why it is not called 'AlphaGame'.
 */

public class StandardHotStoneGame implements Game {

  private boolean isPeddersenTurn = false;
  private int turnNumber = 1;
  private Hero findus;
  private Hero peddersen;

  StandardHotStoneGame() {
     findus = new StandardHero();
     peddersen = new StandardHero();
  }

  @Override
  public Player getPlayerInTurn() {
    if (!isPeddersenTurn) {
      return Player.FINDUS;
    } else {
      return Player.PEDDERSEN;
    }
  }

  @Override
  public Hero getHero(Player who) {
    if(who == Player.PEDDERSEN)
      return peddersen;
    else
      return findus;
  }

  @Override
  public Player getWinner() {
    return null;
  }

  @Override
  public int getTurnNumber() {
    return turnNumber;
  }

  @Override
  public int getDeckSize(Player who) {
    return 0;
  }

  @Override
  public StandardCard getCardInHand(Player who, int indexInHand) {
    switch (indexInHand) {
      case 0:
        return new StandardCard(GameConstants.UNO_CARD);
      case 1:
        return new StandardCard(GameConstants.DOS_CARD);
      case 2:
        return new StandardCard(GameConstants.TRES_CARD);
    }
    return null;
  }


  @Override
  public Iterable<? extends StandardCard> getHand(Player who) {
    return null;
  }

  @Override
  public int getHandSize(Player who) {
    return 3;
  } // FAKE-IT

  @Override
  public StandardCard getCardInField(Player who, int indexInField) {
    return null;
  }

  @Override
  public Iterable<? extends StandardCard> getField(Player who) {
    return null;
  }

  @Override
  public int getFieldSize(Player who) {
    return 0;
  }

  @Override
  public void endTurn() {
    isPeddersenTurn = !isPeddersenTurn;
    turnNumber++;
  }

  @Override
  public Status playCard(Player who, hotstone.framework.Card card, int atIndex) {
    return null;
  }

  @Override
  public Status attackCard(Player playerAttacking, hotstone.framework.Card attackingCard, hotstone.framework.Card defendingCard) {
    return null;
  }

  @Override
  public Status attackHero(Player playerAttacking, hotstone.framework.Card attackingCard) {
    return null;
  }

  @Override
  public Status usePower(Player who) {
    if(who == Player.PEDDERSEN)
      return peddersen.usePower();
    return findus.usePower();
  }
}
