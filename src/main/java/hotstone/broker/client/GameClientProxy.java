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

package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.IPCException;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.*;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

public class GameClientProxy implements UIGame, ClientProxy {

  public static final String GAME_OBJECT_ID = "singleton";

  private final Requestor requestor;
  private final List<GameObserver> observers = new ArrayList<>();

  public GameClientProxy(Requestor requestor) { this.requestor = requestor;}

  private class CardProxies {
    private List<CardClientProxy> proxies = new ArrayList<>();

    public CardClientProxy getProxyFor(String objectId) {
      for (CardClientProxy p : proxies) {
        if (p.CARD_OBJECT_ID.equals(objectId)) {
          return p;
        }
      }
      CardClientProxy newProxy = new CardClientProxy(objectId, requestor);
      proxies.add(newProxy);
      return newProxy;
    }

  }

  private class HeroProxies {
    private List<HeroClientProxy> proxies = new ArrayList<>();

    public HeroClientProxy getProxyFor(String objectId) {
      for (HeroClientProxy p : proxies) {
        if (p.HERO_OBJECT_ID.equals(objectId)) {
          return p;
        }
      }
      HeroClientProxy newProxy = new HeroClientProxy(objectId, requestor);
      proxies.add(newProxy);
      return newProxy;
    }

  }

  private CardProxies cardProxies = new CardProxies();
  private HeroProxies heroProxies = new HeroProxies();

  @Override
  public int getRoundCount() {
    try {
      return requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID,
              OperationNames.GAME_GET_ROUND_COUNT,
              Integer.class);
    } catch(IPCException e) {
      if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
        throw e;
      }
    }
    return -1;
  }

  @Override
  public Player getPlayerInTurn() {
    try {
    return requestor.sendRequestAndAwaitReply(
            GAME_OBJECT_ID,
            OperationNames.GAME_GET_PLAYER_IN_TURN,
            Player.class
    );
  } catch(IPCException e) {
    if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
      throw e;
    }
    return null;
  }
}

  @Override
  public SecureHero getHero(Player who) {
    try {
      String objectId = requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID, OperationNames.GAME_GET_HERO, String.class, who);
      return heroProxies.getProxyFor(objectId);
    } catch (IPCException e) {
      if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) throw e;
      return null;
    }
  }

  @Override
  public Player getWinner() {
    try {
      return requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID,
              OperationNames.GAME_GET_WINNER,
              Player.class
      );
    } catch(IPCException e) {
    if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
      throw e;
    }
  }
    return null;
}

  @Override
  public int getTurnNumber() {
    try {
      return requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID,
              OperationNames.GAME_GET_TURN_NUMBER,
              Integer.class);
    } catch(IPCException e) {
    if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
      throw e;
    }
  }
    return -1;
}

  @Override
  public int getDeckSize(Player who) {
    try {
      return requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID,
              OperationNames.GAME_GET_DECK_SIZE,
              Integer.class,
              who);
    } catch(IPCException e) {
    if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
      throw e;
    }
  }
    return -1;
}

  @Override
  public Card getCardInDeck(Player who, int indexInDeck) {
    return getCardReference(OperationNames.GAME_GET_CARD_IN_DECK, who, indexInDeck);
  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    return getCardReference(OperationNames.GAME_GET_CARD_IN_HAND, who, indexInHand);
  }

  @Override
  public List<? extends Card> getHand(Player who) {
    return getCardReferences(OperationNames.GAME_GET_HAND, who);
  }

  @Override
  public int getHandSize(Player who) {
    try {
      return requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID,
              OperationNames.GAME_GET_HAND_SIZE,
              Integer.class,
              who);
    } catch(IPCException e) {
    if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
      throw e;
    }
  }
    return -1;
}

  @Override
  public Card getCardInField(Player who, int indexInField) {
    return getCardReference(OperationNames.GAME_GET_CARD_IN_FIELD, who, indexInField);
  }

  @Override
  public List<? extends Card> getField(Player who) {
    return getCardReferences(OperationNames.GAME_GET_FIELD, who);
  }

  private Card getCardReference(String operation, Player who, int index) {
    try {
      String objectId = requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID, operation, String.class, who, index);
      return cardProxies.getProxyFor(objectId);
    } catch (IPCException e) {
      if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) throw e;
      return null;
    }
  }

  private List<? extends Card> getCardReferences(String operation, Player who) {
    try {
      List<String> objectIds = requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID, operation, List.class, who);
      return objectIds.stream()
              .map(id -> cardProxies.getProxyFor(id))
              .toList();
    } catch (IPCException e) {
      if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) throw e;
      return null;
    }
  }

  @Override
  public int getFieldSize(Player who) {
    try {
      return requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID,
              OperationNames.GAME_GET_FIELD_SIZE,
              Integer.class,
              who);
    } catch(IPCException e) {
    if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
      throw e;
    }
  }
    return -1;
}

  @Override
  public void endTurn() {
      try {
          requestor.sendRequestAndAwaitReply(
                  GAME_OBJECT_ID,
                  OperationNames.GAME_END_OF_TURN,
                  Void.class
          );
          Player nextPlayer = getPlayerInTurn();
          observers.forEach(observer -> observer.onChangeTurnTo(nextPlayer));
      } catch(IPCException e) {
          if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
              throw e;
          }
      }
  }

  @Override
  public Status playCard(int handIndex, int fieldIndex) {
    Player player = getPlayerInTurn();
    Card card = getCardInHand(player, handIndex);
    try {
      Status status = requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID,
              OperationNames.GAME_PLAY_CARD,
              Status.class,
              handIndex,
              fieldIndex
      );
      if (status == Status.OK) {
        observers.forEach(observer -> observer.onPlayCard(player, card, fieldIndex));
      }
      return status;
    } catch(IPCException e) {
    if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
      throw e;
    }
  }
    return null;
}

  @Override
  public Status attackCard(int ownFieldIndex, int oppFieldIndex) {
    Player player = getPlayerInTurn();
    Card attackingCard = getCardInField(player, ownFieldIndex);
    Card defendingCard = getCardInField(Player.computeOpponent(player), oppFieldIndex);
    try {
      Status status = requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID,
              OperationNames.GAME_ATTACK_CARD,
              Status.class,
              ownFieldIndex,
              oppFieldIndex
      );
      if (status == Status.OK) {
        observers.forEach(observer -> observer.onAttackCard(player, attackingCard, defendingCard));
      }
      return status;
    } catch(IPCException e) {
      if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
        throw e;
      }
    }
    return null;
  }

  @Override
  public Status attackHero(int fieldIndex) {
    Player player = getPlayerInTurn();
    Card attackingCard = getCardInField(player, fieldIndex);
    try {
      Status status = requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID,
              OperationNames.GAME_ATTACK_HERO,
              Status.class,
              fieldIndex
      );
      if (status == Status.OK) {
        observers.forEach(observer -> observer.onAttackHero(player, attackingCard));
      }
      return status;
    } catch(IPCException e) {
      if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
        throw e;
      }
    }
    return null;
  }

  @Override
  public Status usePower() {
    Player player = getPlayerInTurn();
    try {
      Status status = requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID,
              OperationNames.GAME_USE_POWER,
              Status.class
      );
      if (status == Status.OK) {
        observers.forEach(observer -> observer.onUsePower(player));
      }
      return status;
    } catch(IPCException e) {
      if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
        throw e;
      }
    }
    return null;
  }

  @Override
  public void addObserver(GameObserver observer) {
    observers.add(observer);
  }
}
