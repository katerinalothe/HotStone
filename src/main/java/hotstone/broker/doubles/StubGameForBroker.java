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

package hotstone.broker.doubles;

import hotstone.framework.*;

import java.util.List;

/** A Test Stub for game, to make easily recognizable output
 * to assert on in the Broker test cases. Some methods have
 * already been defined.
 */

/* TODO: Fill more stub behaviour for testing your GameClientProxy
 * and GameInvoker code.
 */

public class StubGameForBroker implements UIGame {
  @Override
  public int getTurnNumber() {
    return 312;
  }

  @Override
  public int getDeckSize(Player who) {
    return 0;
  }

  @Override
  public Card getCardInDeck(Player who, int indexInDeck) {
    return null;
  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    return null;
  }

  @Override
  public List<? extends Card> getHand(Player who) {
    return List.of();
  }

  @Override
  public int getHandSize(Player who) {
    return 0;
  }

  @Override
  public Card getCardInField(Player who, int indexInField) {
    return null;
  }

  @Override
  public List<? extends Card> getField(Player who) {
    return List.of();
  }

  @Override
  public int getFieldSize(Player who) {
    return 0;
  }

  @Override
  public int getRoundCount() {
    return 0;
  }

  @Override
  public Player getPlayerInTurn() {
    return Player.FINDUS;
  }

  @Override
  public SecureHero getHero(Player who) {
    return null;
  }

  @Override
  public Player getWinner() {
    return Player.PEDDERSEN;
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
