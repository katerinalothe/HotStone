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
import hotstone.framework.*;
import jakarta.servlet.http.HttpServletResponse;

public class HotStoneGameInvoker implements Invoker {
  // blob invoker.
  private final UIGame servantGame;
  private final HotStoneNameService nameService;
  private final Gson gson;

  public HotStoneGameInvoker(UIGame servantGame, HotStoneNameService nameService) {
    this.servantGame = servantGame;
    this.nameService = nameService;
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
     */
      // Dispatching on all known operations
      // Each dispatch follows the same algorithm
      // a) retrieve parameters from json array (if any)
      // b) invoke servant method
      // c) populate a reply object with return values

    reply = switch (requestObject.getOperationName()) {

      // ----------------- GAME METHODS --------------------
      case OperationNames.GAME_GET_ROUND_COUNT -> {
        int roundCount = servantGame.getRoundCount();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(roundCount));
      }

      case OperationNames.GAME_GET_PLAYER_IN_TURN -> {
        Player player = servantGame.getPlayerInTurn();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(player));
      }

      case OperationNames.GAME_GET_WINNER -> {
        Player winner = servantGame.getWinner();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(winner));
      }

      case OperationNames.GAME_GET_TURN_NUMBER -> {
        int turnNumber = servantGame.getTurnNumber();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(turnNumber));
      }

      case OperationNames.GAME_GET_DECK_SIZE -> {
        Player who = gson.fromJson(array.get(0), Player.class);
        int deckSize = servantGame.getDeckSize(who);
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(deckSize));
      }

      case OperationNames.GAME_GET_HAND_SIZE -> {
        Player who = gson.fromJson(array.get(0), Player.class);
        int handSize = servantGame.getHandSize(who);
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(handSize));
      }

      case OperationNames.GAME_GET_FIELD_SIZE -> {
        Player who = gson.fromJson(array.get(0), Player.class);
        int fieldSize = servantGame.getFieldSize(who);
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(fieldSize));
      }

      case OperationNames.GAME_END_OF_TURN -> {
        servantGame.endTurn();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(null));
      }

      case OperationNames.GAME_PLAY_CARD -> {
        int handIndex = array.get(0).getAsInt();
        int fieldIndex = array.get(1).getAsInt();
        Status status = servantGame.playCard(handIndex, fieldIndex);
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(status));
      }

      case OperationNames.GAME_ATTACK_CARD -> {
        int ownFieldIndex = array.get(0).getAsInt();
        int oppFieldIndex = array.get(1).getAsInt();
        Status status = servantGame.attackCard(ownFieldIndex, oppFieldIndex);
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(status));
      }

      case OperationNames.GAME_ATTACK_HERO -> {
        int fieldIndex = array.get(0).getAsInt();
        Status status = servantGame.attackHero(fieldIndex);
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(status));
      }

      case OperationNames.GAME_USE_POWER -> {
        Status status = servantGame.usePower();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(status));
      }

      case OperationNames.GAME_GET_CARD_IN_DECK -> {
        Player who = gson.fromJson(array.get(0), Player.class);
        int index = gson.fromJson(array.get(1), Integer.class);
        Card card = servantGame.getCardInDeck(who, index);
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(nameService.idFor(card)));
      }

      case OperationNames.GAME_GET_HERO -> {
        Player who = gson.fromJson(array.get(0), Player.class);
        SecureHero hero = servantGame.getHero(who);
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(nameService.idFor(hero)));
      }

      case OperationNames.GAME_GET_CARD_IN_HAND -> {
        Player who = gson.fromJson(array.get(0), Player.class);
        int index = array.get(1).getAsInt();
        Card card = servantGame.getCardInHand(who, index);
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(nameService.idFor(card)));
      }

      case OperationNames.GAME_GET_HAND -> {
        Player who = gson.fromJson(array.get(0), Player.class);
        var ids = servantGame.getHand(who).stream().map(nameService::idFor).toList();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(ids));
      }

      case OperationNames.GAME_GET_CARD_IN_FIELD -> {
        Player who = gson.fromJson(array.get(0), Player.class);
        int index = array.get(1).getAsInt();
        Card card = servantGame.getCardInField(who, index);
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(nameService.idFor(card)));
      }

      case OperationNames.GAME_GET_FIELD -> {
        Player who = gson.fromJson(array.get(0), Player.class);
        var ids = servantGame.getField(who).stream().map(nameService::idFor).toList();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(ids));
      }

      // ----------------- CARD METHODS ----------------------------
      case OperationNames.CARD_GET_NAME -> {
        Card servantCard = nameService.getCard(requestObject.getObjectId());
        String name = servantCard.getName();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(name));
      }

      case OperationNames.CARD_GET_MANA_COST -> {
        Card servantCard = nameService.getCard(requestObject.getObjectId());
        int manaCost = servantCard.getManaCost();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(manaCost));
      }

      case OperationNames.CARD_GET_ATTACK -> {
        Card servantCard = nameService.getCard(requestObject.getObjectId());
        int attack = servantCard.getAttack();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(attack));
      }

      case OperationNames.CARD_GET_HEALTH -> {
        Card servantCard = nameService.getCard(requestObject.getObjectId());
        int health = servantCard.getHealth();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(health));
      }

      case OperationNames.CARD_IS_ACTIVE -> {
        Card servantCard = nameService.getCard(requestObject.getObjectId());
        boolean active = servantCard.isActive();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(active));
      }

      case OperationNames.CARD_GET_OWNER -> {
        Card servantCard = nameService.getCard(requestObject.getObjectId());
        Player owner = servantCard.getOwner();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(owner));
      }

      case OperationNames.CARD_GET_EFFECT_DESCRIPTION -> {
        Card servantCard = nameService.getCard(requestObject.getObjectId());
        String effectDescription = servantCard.getEffectDescription();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(effectDescription));
      }

      // --------------------- HERO METHODS --------------------------------------

      case OperationNames.HERO_GET_MANA -> {
        SecureHero servantHero = nameService.getHero(requestObject.getObjectId());
        int mana = servantHero.getMana();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(mana));
      }

      case OperationNames.HERO_GET_POWER_NAME -> {
        SecureHero servantHero = nameService.getHero(requestObject.getObjectId());
        String powerName = servantHero.getPowerName();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(powerName));
      }

      case OperationNames.HERO_GET_POWER_COST -> {
        SecureHero servantHero = nameService.getHero(requestObject.getObjectId());
        int powerCost = servantHero.getPowerManaCost();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(powerCost));
      }

      case OperationNames.HERO_GET_HEALTH -> {
        SecureHero servantHero = nameService.getHero(requestObject.getObjectId());
        int health = servantHero.getHealth();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(health));
      }

      case OperationNames.HERO_GET_TYPE -> {
        SecureHero servantHero = nameService.getHero(requestObject.getObjectId());
        String type = servantHero.getType();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(type));
      }

      case OperationNames.HERO_GET_OWNER -> {
        SecureHero servantHero = nameService.getHero(requestObject.getObjectId());
        Player owner = servantHero.getOwner();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(owner));
      }

      case OperationNames.HERO_GET_EFFECT_DESCRIPTION -> {
        SecureHero servantHero = nameService.getHero(requestObject.getObjectId());
        String effectDescription = servantHero.getEffectDescription();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(effectDescription));
      }

      case OperationNames.HERO_IS_DEFEATED -> {
        SecureHero servantHero = nameService.getHero(requestObject.getObjectId());
        boolean isDefeated = servantHero.isDefeated();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(isDefeated));
      }

      case OperationNames.HERO_CAN_USE_POWER -> {
        SecureHero servantHero = nameService.getHero(requestObject.getObjectId());
        boolean canUsePower = servantHero.canUsePower();
        yield new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(canUsePower));
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
