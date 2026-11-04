package hotstone.framework.Strategies;

import hotstone.framework.SecureGame;
import hotstone.framework.Player;

/** Strategy for calculating the game's winner, if any. */
public interface VictoryStrategy {

    /** Calculate the winner.
     *
     * @param game the game state.
     * @return the winning Player, or null if there isn't a winner.
     */
    Player getWinner(SecureGame game);

}
