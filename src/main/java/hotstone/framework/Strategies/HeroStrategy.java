package hotstone.framework.Strategies;

import hotstone.framework.MutableHero;
import hotstone.framework.Player;

/** Strategy for creating Players' Heroes. */
public interface HeroStrategy {

    /** Generate a Hero for the given Player.
     *
     * @param p the Player to generate a Hero for.
     * @return a Hero with mutators available.
     * */
    MutableHero generate(Player p);

}
