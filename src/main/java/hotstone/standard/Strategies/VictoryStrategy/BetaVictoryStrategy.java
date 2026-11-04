package hotstone.standard.Strategies.VictoryStrategy;

import hotstone.framework.SecureGame;
import hotstone.framework.Player;
import hotstone.framework.Strategies.VictoryStrategy;

public class BetaVictoryStrategy implements VictoryStrategy {

    public Player getWinner(SecureGame game) {
        if (game.getHero(Player.FINDUS).getHealth() <= 0) {
            return Player.PEDDERSEN;
        } else if (game.getHero(Player.PEDDERSEN).getHealth() <= 0) {
            return Player.FINDUS;
        } else {
            return null;
        }
    }

}
