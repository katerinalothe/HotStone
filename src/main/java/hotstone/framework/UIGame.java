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

package hotstone.framework;

/** The game object's private interface for the UI. */

public interface UIGame extends SecureGame,Observable {

  /**
   * Perform end of turn for current player, to prepare for the
   * opponent's turn.  PRECONDITION: The client MUST ensure that
   * endTurn() is never called by the player which is NOT in turn.
   */
  void endTurn();

  /** Play a card from the hand of the player-in-turn to the field.
   *
   * PRECONDITION: handIndex in range [0, getHandSize(who)]
   * PRECONDITION: fieldIndex in range [0, getFieldSize(who)]
   *
   * @param handIndex the index of the card to be played
   * @param fieldIndex index at which the card should be
   *                entered on the field. 0 = left of
   *                leftmost position, 1 = between first
   *                and second position, etc.
   *
   * @return the status of the play
   */
   Status playCard(int handIndex, int fieldIndex);

  /** The player-in-turn attacks a defending card with one of their cards.
   *
   * PRECONDITION: the indices are within range of their fields
   *
   * @param ownFieldIndex the index of the card in the player-in-turn's field
   * @param oppFieldIndex the index of the card in the opposing field
   * @return a status identifying the outcome of the attack, which is
   *    either OK or some value explaining why the action was invalid.
   */
  Status attackCard(int ownFieldIndex, int oppFieldIndex);

  /** Attack the opposing hero with a card in the field.
   *
   * PRECONDITION: fieldIndex is within range of the field
   *
   * @param fieldIndex index of the attacking card
   */
  Status attackHero(int fieldIndex);

  /** Use the player-in-turn hero's special power/effect.
   *
   * @return a status identifying the outcome of the power use, which
   *    is either OK or some value explaining why the action was
   *    invalid.
   */
  Status usePower();

}
