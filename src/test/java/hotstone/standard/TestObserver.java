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

import hotstone.framework.Player;
import hotstone.framework.UIGame;
import hotstone.standard.VariantFactories.GammaFactory;
import hotstone.standard.utilities.ObserverDouble;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestObserver {
  @Test
  public void shouldFireEventsForUsePower() {
    // Given a test double for the game observer
    ObserverDouble observerDouble = new ObserverDouble();
    // Given a GammaStone game,
    UIGame game = new StandardHotStoneGame(new GammaFactory());
    // When Findus use the hero power,
    if (game.getPlayerInTurn() != Player.FINDUS) {
        game.endTurn();
    }
    // (Ensure that usePower is observer's first catch.)
    game.addObserver(observerDouble);
    game.usePower();

    // Then a sequence of events are triggered/fired
    // from the game.
    /*
      From FRS Chapter 37.6.1
      onUsePower(FINDUS) - due to the mutator being called
        onHeroUpdate(FINDUS) - due to hero spending mana
        onHeroUpdate(PEDDERSEN) - due to opponent health reduced
     */

      assertThat(observerDouble.getReceipt(0),
              is("onHeroUpdate_PEDDERSEN")
      );
    assertThat(observerDouble.getReceipt(1), is("onUsePower"));
    assertThat(observerDouble.getReceipt(2),
            is("onHeroUpdate_FINDUS")
    );

  }

    @Test
    public void shouldFireEventsForDanishChefPower() {
        // Given a GammaStone game with an ObserverDouble observer,
        UIGame game = new StandardHotStoneGame(new GammaFactory());
        ObserverDouble observer = new ObserverDouble();
        // When Peddersen successfully uses his DanishChef hero's power,
        if (game.getPlayerInTurn() != Player.PEDDERSEN) {
            game.endTurn();
        }
        game.addObserver(observer);
        game.usePower();
        // Then the observer should have observed the Sov card's creation.
        assertThat(observer.getReceipt(0),
                is("onPlayCard_PEDDERSEN_Sovs_0")
        );
        assertThat(observer.getReceipt(1),
                is("onUsePower")
        );
        assertThat(observer.getReceipt(2),
                is("onHeroUpdate_PEDDERSEN")
        );
    }

}

