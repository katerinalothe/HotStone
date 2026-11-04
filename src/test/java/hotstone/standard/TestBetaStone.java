package hotstone.standard;
import hotstone.framework.*;
import hotstone.framework.Strategies.ManaStrategy;
import hotstone.standard.VariantFactories.BetaFactory;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class TestBetaStone {
    private UIGame game;
    private ManaStrategy manaStrategy;

    /** Fixture for BetaStone testing. */
    @BeforeEach
    public void setUp() {
        BetaFactory factory = new BetaFactory();
        game = new StandardHotStoneGame(factory);
        manaStrategy = factory.makeManaStrategy();
    }

    @Test
    public void shouldStartWithManaNumberOfRounds(){
        // Given a game
        // When turn ends
        // Then mana sets to number of rounds
        assertThat(game.getRoundCount(), is(manaStrategy.calculate(game.getRoundCount())));
    }

    @Test
    public void shouldNotExceed7Mana() {
        // Given a game
        // When round is equal or more to 7
        for(int i = 0; i < 8*2; i++)
            game.endTurn();
        // Then mana defaults to 7.
        assertThat(7, is(manaStrategy.calculate(game.getRoundCount())));
    }

    @Test
    public void shouldWinIfOppHeroDead() {
        // Given a game,
        // When the opponent's hero's health >= 0,
        while (game.getHero(Player.FINDUS).getHealth() > 0) {
            game.endTurn();
        }
        // Then the other player wins.
        assertThat(game.getWinner(), is(Player.PEDDERSEN));

    }

}
