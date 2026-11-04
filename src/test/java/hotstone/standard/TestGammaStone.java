package hotstone.standard;

import hotstone.framework.*;
import hotstone.standard.VariantFactories.GammaFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestGammaStone {
    private UIGame game;

    /** Fixture for AlphaStone testing. */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new GammaFactory());
    }

    @Test
    public void findusShouldHaveHeroTypeThaiChef() {
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));
    }
    @Test
    public void peddersonShouldHaveHeroTypeDanishChef() {
        assertThat(game.getHero(Player.PEDDERSEN).getType(), is(GameConstants.DANISH_CHEF_HERO_TYPE));
    }

    @Test
    public void chiliPowerShouldHurtHero2Health() {
        // Given thaiChef hero,
        int beforeDamage = game.getHero(Player.PEDDERSEN).getHealth();
        // When uses power chili,
        game.usePower();
        int afterDamage = game.getHero(Player.PEDDERSEN).getHealth();
        // Then opponent loses 2 health
        assertThat(afterDamage, is(beforeDamage-2));
    }

    @Test
    public void shouldAddMinionForSovsPower() {
        // Given some field for Peddersen,
        game.endTurn();
        Player player = game.getPlayerInTurn();
        int fieldSize = game.getFieldSize(player);
        // When the Sovs power is invoked,
        game.endTurn();
        game.endTurn();
        game.usePower();
        // Then the Sovs card is added to the field.
        // (Assume that the Sovs card is added to the 0th index of the field.)
        List<? extends Card> field = game.getField(player);
        assertThat(field.getFirst().getName(), is(GameConstants.SOVS_CARD));
    }

    @Test
    public void winnerIsFirstPersonThatClearsOpponentFieldAfter3rounds() {
        // Given a game,

        // When Findus has cards on field and clears the other persons field after 3 rounds,
        game.playCard(2, 0);
        for(int i = 0; i < 6; i++)
            game.endTurn();
        // Then Findus is the winner.
        assertThat(game.getWinner(), is(Player.FINDUS));
    }

}
