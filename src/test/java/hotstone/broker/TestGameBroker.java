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

package hotstone.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.broker.client.GameClientProxy;
import hotstone.broker.doubles.StubGameForBroker;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.server.HotStoneGameInvoker;

import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.UIGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


/** Skeleton test case for a 'depth-first' test-driven
 * development process to develop the Broker implementation
 * of client proxies and invokers, for all 'primitive' methods
 * in Game, that is, methods that do NOT take objects as
 * parameters, only primitive types and Strings.
 */
public class TestGameBroker {
  // The client side's game client proxy
  private UIGame gameClientProxy;

  @BeforeEach
  public void setup() {
    // === We start at the server side of the Broker pattern:
    // define the servant, next the invoker

    // Given a Servant game, here a test stub with canned output
    UIGame servant = new StubGameForBroker();
    // Which is injected into the dedicated Invoker which you must
    // develop
    Invoker invoker = new HotStoneGameInvoker(servant);

    // === Next define the client side of the pattern:
    // the client request handler, the requestor, and the client proxy

    // Instead of a network-based client- and server request handler
    // we make a fake object CRH that talks directly with the injected
    // invoker
    ClientRequestHandler crh =
            new LocalMethodClientRequestHandler(invoker);

    // Which is injected into the standard JSON requestor of the
    // FRDS.Broker library
    Requestor requestor = new StandardJSONRequestor(crh);

    // Which is finally injected into the GameClientProxy that
    // you must develop...
    gameClientProxy = new GameClientProxy(requestor);
  }

  // TODO: TDD the 'clientproxy' and 'invoker' implementation
  // to support remote method call of getTurnNumber()
  @Test
  public void shouldHaveTurnNumber111() {
    // Given a stub game which is hard coded to
    // return 312 as turn number

    // When I ask for that turn number on the client side
    int turnNumber = gameClientProxy.getTurnNumber();

    // Then the broker chain (clientProxy -> requestor ->
    // client request handler -> invoker -> servant) will
    // return the stub's 312 reply.
    assertThat(turnNumber, is(111));
  }

  @Test
  public void shouldHaveDeckSize222() {
      // Given a stub game,
      // When I request the deck size of some player,
      // Then I should receive 222.
      assertThat(
              gameClientProxy.getDeckSize(Player.FINDUS),
              is(222)
      );
  }

  @Test
  public void shouldHaveHandSize333() {
    // Hand size should be 333.
      assertThat(
              gameClientProxy.getHandSize(Player.FINDUS),
              is(333)
      );
  }

  @Test
  public void shouldHaveFieldSize444() {
    // Given a stub game hardcoded to return a field size of 444,
    // When I request the field size for a player,
    // Then I should receive 444
    assertThat(
            gameClientProxy.getFieldSize(Player.FINDUS),
            is(444)
    );
  }

  @Test
  public void shouldHaveRoundCount555() {
    // Given a stub game hardcoded to return a round count of 555,
    // When I request the current round count,
    // Then I should receive 555
    assertThat(
            gameClientProxy.getRoundCount(),
            is(555)
    );
  }

  @Test
  public void shouldHavePlayerInTurnFindus() {
    // Given a stub game hardcoded to set FINDUS as the player in turn,
    // When I request the player in turn,
    // Then I should receive FINDUS
    assertThat(
            gameClientProxy.getPlayerInTurn(),
            is(Player.FINDUS)
    );
  }

  @Test
  public void shouldHaveWinnerPeddersen() {
    // Given a stub game hardcoded for PEDDERSEN as the winner,
    // When I request the winner,
    // Then I should receive PEDDERSEN
    assertThat(
            gameClientProxy.getWinner(),
            is(Player.PEDDERSEN)
    );
  }

  @Test
  public void shouldReturnOkWhenPlayingCard() {
    // Given a stub game where playCard executions return Status.OK,
    // When I invoke playCard,
    // Then I should receive Status.OK
    assertThat(
            gameClientProxy.playCard(0, 0),
            is(Status.OK)
    );
  }

  @Test
  public void shouldReturnOkWhenAttackingCard() {
    // Given a stub game where attackCard return Status.OK,
    // When I invoke attackCard,
    // Then I should receive Status.OK
    assertThat(
            gameClientProxy.attackCard(0, 0),
            is(Status.OK)
    );
  }

  @Test
  public void shouldReturnOkWhenAttackingHero() {
    // Given a stub game where attackHero returns Status.OK,
    // When I invoke attackHero,
    // Then I should receive Status.OK
    assertThat(
            gameClientProxy.attackHero(0),
            is(Status.OK)
    );
  }

  @Test
  public void shouldReturnOkWhenUsingPower() {
    // Given a stub game where UsePower returns Status.OK,
    // When I invoke usePower,
    // Then I should receive Status.OK
    assertThat(
            gameClientProxy.usePower(),
            is(Status.OK)
    );
  }
}
