package hotstone.standard.Strategies.VictoryStrategy;

import hotstone.framework.SecureGame;
import hotstone.framework.Player;
import hotstone.framework.Strategies.VictoryStrategy;

public class GammaVictoryStrategy implements VictoryStrategy {
    @Override
    public Player getWinner(SecureGame game) {
        if(game.getRoundCount() < 3)
            return null;
        if(game.getFieldSize(Player.FINDUS) == 0)
            return Player.PEDDERSEN;
        if(game.getFieldSize(Player.PEDDERSEN) == 0)
            return Player.FINDUS;
        return null;
    }
}
