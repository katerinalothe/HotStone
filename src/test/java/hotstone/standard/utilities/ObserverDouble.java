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

package hotstone.standard.utilities;

import hotstone.framework.Card;
import hotstone.framework.GameObserver;
import hotstone.framework.Player;

import java.util.ArrayList;

/**
 * No longer a TO-DO: Reflect - is this double a spy or a stub?
 *  Answer: It's a spy since we're recording indirect outputs.
 */
public class ObserverDouble implements GameObserver {

    private final ArrayList<String> receipts = new ArrayList<>();

    public int receiptCount() {
        return receipts.size();
    }

    public String getReceipt(int index) {
        return receipts.get(index);
    }

  @Override
  public void onPlayCard(Player who, Card card, int atIndex) {
        receipts.add(
                String.format(
                        "onPlayCard_%s_%s_%d",
                        who.toString(), card.getName(), atIndex
                        )
        );
  }

  @Override
  public void onChangeTurnTo(Player playerBecomingActive) {}

  @Override
  public void onAttackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {}

  @Override
  public void onAttackHero(Player playerAttacking, Card attackingCard) {}

  @Override
  public void onUsePower(Player who) {
      receipts.add("onUsePower");
  }

  @Override
  public void onCardDraw(Player who, Card drawnCard) {}

  @Override
  public void onCardUpdate(Card card) {}

  @Override
  public void onCardRemove(Player who, Card card) {}

  @Override
  public void onHeroUpdate(Player who) {
        receipts.add("onHeroUpdate_" + who.toString());
  }

  @Override
  public void onGameWon(Player playerWinning) {}
}
