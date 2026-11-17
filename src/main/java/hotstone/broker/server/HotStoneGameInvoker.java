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

package hotstone.broker.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.UIGame;
import jakarta.servlet.http.HttpServletResponse;

/** TODO: Template code for solving the Broker exercises */
public class HotStoneGameInvoker implements Invoker {
  private final UIGame servant;
  private final Gson gson;

  public HotStoneGameInvoker(UIGame servant) {
    this.servant = servant;
    gson = new Gson();
  }

  @Override
  public String handleRequest(String request) {
    // Do the demarshalling
    RequestObject requestObject =
            gson.fromJson(request, RequestObject.class);
    JsonArray array =
            JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

    ReplyObject reply;

    /* As there is only one Game instance (a singleton)
       the objectId is not used for anything in our case.
       Rewritten Nov 2024 to use Java 13 switch+yield.
     */
      // Dispatching on all known operations
      // Each dispatch follows the same algorithm
      // a) retrieve parameters from json array (if any)
      // b) invoke servant method
      // c) populate a reply object with return values

    reply = switch (requestObject.getOperationName()) {

      case OperationNames.GAME_GET_ROUND_COUNT -> {
        int roundCount = servant.getRoundCount();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(roundCount));
      }

      case OperationNames.GAME_GET_PLAYER_IN_TURN -> {
        Player player = servant.getPlayerInTurn();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(player));
      }

      case OperationNames.GAME_GET_WINNER -> {
        Player winner = servant.getWinner();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(winner));
      }

      case OperationNames.GAME_GET_TURN_NUMBER -> {
        int turnNumber = servant.getTurnNumber();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(turnNumber));
      }

      case OperationNames.GAME_GET_DECK_SIZE -> {
        Player who = gson.fromJson(array.get(0), Player.class);
        int deckSize = servant.getDeckSize(who);
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(deckSize));
      }

      case OperationNames.GAME_GET_HAND_SIZE -> {
        Player who = gson.fromJson(array.get(0), Player.class);
        int handSize = servant.getHandSize(who);
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(handSize));
      }

      case OperationNames.GAME_GET_FIELD_SIZE -> {
        Player who = gson.fromJson(array.get(0), Player.class);
        int fieldSize = servant.getFieldSize(who);
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(fieldSize));
      }

      case OperationNames.GAME_END_OF_TURN -> {
        servant.endTurn();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(null));
      }

      case OperationNames.GAME_PLAY_CARD -> {
        int handIndex = array.get(0).getAsInt();
        int fieldIndex = array.get(1).getAsInt();
        Status status = servant.playCard(handIndex, fieldIndex);
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(status));
      }

      case OperationNames.GAME_ATTACK_CARD -> {
        int ownFieldIndex = array.get(0).getAsInt();
        int oppFieldIndex = array.get(1).getAsInt();
        Status status = servant.attackCard(ownFieldIndex, oppFieldIndex);
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(status));
      }

      case OperationNames.GAME_ATTACK_HERO -> {
        int fieldIndex = array.get(0).getAsInt();
        Status status = servant.attackHero(fieldIndex);
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(status));
      }

      case OperationNames.GAME_USE_POWER -> {
        Status status = servant.usePower();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(status));
      }

      default -> {
        // Unknown operation
        yield new ReplyObject(HttpServletResponse.
                SC_NOT_IMPLEMENTED,
                "Server received unknown operation name: '"
                        + requestObject.getOperationName() + "'.");
      }
    };

      // And marshall the reply
    return gson.toJson(reply);
  }
}
