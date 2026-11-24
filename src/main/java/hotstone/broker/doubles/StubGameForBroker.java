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

import hotstone.doubles.StubCard;
import hotstone.doubles.StubHero;
import hotstone.framework.*;

import java.util.List;

/** A Test Stub for game, to make easily recognizable output
 * to assert on in the Broker test cases. Some methods have
 * already been defined.
 */

public class StubGameForBroker implements UIGame {
  private final Card deckCard;
  private final SecureHero hero;

  public StubGameForBroker() {
    this(new StubCard("StubCard", Player.FINDUS), new StubHero());
  }

  public StubGameForBroker(Card deckCard) {
    this(deckCard, new hotstone.doubles.StubHero());
  }

  public StubGameForBroker(Card deckCard, SecureHero hero) {
    this.deckCard = deckCard;
    this.hero = hero;
  }

  @Override
  public int getTurnNumber() {
    return 111;
  }

  @Override
  public int getDeckSize(Player who) {
    return 222;
  }

  @Override
  public Card getCardInDeck(Player who, int indexInDeck) {
    return deckCard;
  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    return deckCard;
  }

  @Override
  public List<? extends Card> getHand(Player who) {
    return List.of(deckCard);
  }

  @Override
  public int getHandSize(Player who) {
    return 333;
  }

  @Override
  public Card getCardInField(Player who, int indexInField) {
    return deckCard;
  }

  @Override
  public List<? extends Card> getField(Player who) {
    return List.of(deckCard);
  }

  @Override
  public int getFieldSize(Player who) {
    return 444;
  }

  @Override
  public int getRoundCount() {
    return 555;
  }

  @Override
  public Player getPlayerInTurn() {
    return Player.FINDUS;
  }

  @Override
  public SecureHero getHero(Player who) {
    return hero;
  }

  @Override
  public Player getWinner() {
    return Player.PEDDERSEN;
  }


  @Override
  public void endTurn() {}

  @Override
  public Status playCard(int handIndex, int fieldIndex) {
    return Status.OK;
  }

  @Override
  public Status attackCard(int ownFieldIndex, int oppFieldIndex) {
    return Status.OK;
  }

  @Override
  public Status attackHero(int fieldIndex) {
    return Status.OK;
  }

  @Override
  public Status usePower() { return Status.OK; }

  @Override
  public void addObserver(GameObserver observer) {

  }
}
