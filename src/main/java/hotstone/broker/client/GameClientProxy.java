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

import java.util.List;

public class GameClientProxy implements UIGame, ClientProxy {
  public static final String GAME_OBJECT_ID = "singleton";

  private final Requestor requestor;

  public GameClientProxy(Requestor requestor) { this.requestor = requestor;}

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
            String.class
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
    return null;
  }

  @Override
  public Player getWinner() {
    try {
      return requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID,
              OperationNames.GAME_GET_WINNER,
              String.class
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
    return null;
  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    return null;
  }

  @Override
  public List<? extends Card> getHand(Player who) {
    return null;
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
    return null;
  }

  @Override
  public List<? extends Card> getField(Player who) {
    return null;
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
    requestor.sendRequestAndAwaitReply(
            GAME_OBJECT_ID,
            OperationNames.GAME_END_OF_TURN,
            Void.class
    );
  }

  @Override
  public Status playCard(int handIndex, int fieldIndex) {
    try {
      return requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID,
              OperationNames.GAME_PLAY_CARD,
              String.class,
              handIndex,
              fieldIndex
      );
    } catch(IPCException e) {
    if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
      throw e;
    }
  }
    return null;
}

  @Override
  public Status attackCard(int ownFieldIndex, int oppFieldIndex) {
    try {
      return requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID,
              OperationNames.GAME_ATTACK_CARD,
              String.class,
              ownFieldIndex,
              oppFieldIndex
      );
    } catch(IPCException e) {
      if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
        throw e;
      }
    }
    return null;
  }

  @Override
  public Status attackHero(int fieldIndex) {
    try {
      return requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID,
              OperationNames.GAME_ATTACK_HERO,
              String.class,
              fieldIndex
      );
    } catch(IPCException e) {
      if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
        throw e;
      }
    }
    return null;
  }

  @Override
  public Status usePower() {
    try {
      return requestor.sendRequestAndAwaitReply(
              GAME_OBJECT_ID,
              OperationNames.GAME_USE_POWER,
              String.class
      );
    } catch(IPCException e) {
      if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
        throw e;
      }
    }
    return null;
  }

  @Override
  public void addObserver(GameObserver observer) {}
}
