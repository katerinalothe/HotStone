package hotstone.standard.Strategies.VictoryStrategy;

import hotstone.framework.SecureGame;
import hotstone.framework.Player;
import hotstone.framework.Strategies.VictoryStrategy;

public class AlphaVictoryStrategy implements VictoryStrategy {

    @Override
    public Player getWinner(SecureGame game) {
        if (game.getTurnNumber() == 8) {
            return Player.FINDUS;
        } else {
            return null;
        }
    }

}
