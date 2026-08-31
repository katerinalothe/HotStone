package hotstone.standard;

import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.framework.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestHero {
    private Game game;
    private Hero heroFindus;

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame();
        heroFindus = game.getHero(Player.FINDUS); // Both players are type babyHero
    }

    @Test
    public void bothPlayersHaveHeroTypeBaby() {
        // Given both players,
        // When we check their hero types,
        Hero heroPeddersen = game.getHero(Player.PEDDERSEN);
        // Then it will be type "Baby"
        assertThat(heroFindus.getType(), is("Baby"));
        assertThat(heroPeddersen.getType(), is("Baby"));
    }

    @Test
    public void shouldHaveHealth21() {
        // Given a Baby hero,
        // When we check its initial health,
        // Then it should be 21.
        assertThat(heroFindus.getHealth(), is(21));
    }

    @Test
    public void shouldStartWith3Mana() {
        // Given a Baby hero,
        // When we check its initial mana,
        // Then it should be 3.
        assertThat(heroFindus.getMana(), is(3));
    }

    @Test
    public void shouldHavePowerThatCosts2Mana() {
        // Given a Baby hero
        // When we check how much mana the power costs
        // Then the power should cost 2.
        assertThat(heroFindus.getPowerManaCost(), is(2));
    }

    @Test
    public void shouldHavePowerThatIsCalledCute() {
        // Given a Baby hero,
        // When we check its power's name,
        // Then it should be "Cute".
        assertThat(heroFindus.getPowerName(), is("Cute"));
    }

    @Test
    public void shouldHavePowerEffectDescribedAsDoingNothing() {
        // Given a Baby hero,
        // When we check its power's effect description,
        // Then it should be "Does nothing".
        assertThat(heroFindus.getEffectDescription(), is("Does nothing"));
    }

    @Test
    public void shouldBeAbleToUsePowerIfHeroHasEnoughMana() {
        // Given a Hero with mana 3,
        // When they use their power,
        // Then the action will execute
        assertThat(game.usePower(Player.FINDUS), is(Status.OK));
    }

    @Test
    public void shouldDeductHerosManaToUsePower() {
        // Given a Baby hero, with initial mana 3,
        // When we use the power with cost 2,
        // Then Baby Hero should have 1 mana.
        Status status = game.usePower(Player.FINDUS);
        assertThat(heroFindus.getMana(), is(1));
        assertThat(status, is(Status.OK));
    }

    @Test
    public void shouldNotUsePowerIfHeroDoesNotHaveEnoughMana() {
        // Given a Baby hero, with mana 1 (first we call usePower once to deduct the mana from 3 to 1),
        // When we use the power with cost 2,
        // Then Power should not work.
        game.usePower(Player.FINDUS); // Findus should have 1 mana
        assertThat(game.usePower(Player.FINDUS), is(Status.NOT_ENOUGH_MANA));
    }
}
