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

package hotstone.doubles;

import hotstone.framework.MutableGame;
import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.standard.GameConstants;

/** A Test Double Stub implementation of Hero to facilitateUI testing
 *  and development.
 *
 *  Note that there is a bit of 'fake object' behaviour as mana
 *  and active status can be modified.
 */
public class StubHero implements MutableHero {
  private int mana = 1;
  private boolean active = false;

  @Override
  public int getMana() {
    return mana;
  }

  @Override
  public String getPowerName() {
    return "";
  }

  @Override
  public int getPowerManaCost() {
    return 0;
  }

  @Override
  public int getHealth() {
    return 7;
  }

  @Override
  public boolean canUsePower() {
    return active;
  }

  @Override
  public String getType() {
    return GameConstants.BABY_HERO_TYPE;
  }

  @Override
  public Player getOwner() {
    return Player.FINDUS;
  }

  @Override
  public String getEffectDescription() {
    return "+1 Fish";
  }

  @Override
  public boolean isDefeated() {
    return false;
  }

  public void deltaMana(int delta) {
    mana += delta;
  }

  public void setActive(boolean newValue) {
    active = newValue;
  }

  @Override
  public void setMana(int amount) {

  }

  @Override
  public void resetUsedPower() {

  }

  @Override
  public void hurt(int amount) {

  }

  @Override
  public Status usePower(MutableGame game) {
    return null;
  }
}
