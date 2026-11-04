package hotstone.standard;

import hotstone.framework.Player;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.framework.UIGame;
import hotstone.standard.Strategies.PowerStrategy.CardPower.*;
import hotstone.standard.VariantFactories.EtaFactory;
import hotstone.standard.utilities.CardSpy;
import hotstone.standard.utilities.FixedGeneratorStrategy;
import hotstone.standard.utilities.GameAction;
import hotstone.standard.utilities.MutableGameSpy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestEtaStone {

    private MutableGameSpy gameSpy;

    @BeforeEach
    public void setUp() {
        gameSpy = new MutableGameSpy(new CardSpy("Spy", 1,1,1));
    }

    // UNIT TESTS
    @Test
    public void shouldBrownRicePowerHurtHero() {
        // Given the BrownRicePowerStrategy,
        PowerStrategy strat = new BrownRicePowerStrategy();
        // When the power is invoked,
        strat.usePower(gameSpy);
        // Then the Strategy hurts the opp. hero by 1 point.
        assertThat(gameSpy.getLastActionType(), is(GameAction.CHANGE_HERO_HEALTH));
        assertThat(gameSpy.getLastActionParams(), is(-1));
    }

    @Test
    public void shouldTomatoSaladAddAtkToRandomMinion() {
        // Given the TomatoSoupPowerStrategy,
        CardSpy cardSpy = gameSpy.getCardSpy();
        PowerStrategy strat = new TomatoSaladPowerStrategy(new FixedGeneratorStrategy(0));
        // When the power is invoked,
        int initialAttack = cardSpy.getAttack();
        strat.usePower(gameSpy);
        // Then the Strategy adds +1 atk. to a random minion.
        assertThat(cardSpy.getLastActionType(), is(GameAction.SET_ATTACK));
        assertThat(cardSpy.getLastActionParams(), is(
                initialAttack + 1
        ));
    }

    @Test
    public void shouldPokeBowlAdd2healthToHero() {
        // Given the PokeBowlPowerStrategy,
        PowerStrategy strat = new PokeBowlPowerStrategy();
        // When the power is invoked,
        strat.usePower(gameSpy);
        // Then the strategy adds 2 health to hero.
        assertThat(gameSpy.getLastActionType(), is(GameAction.CHANGE_HERO_HEALTH));
        assertThat(gameSpy.getLastActionParams(), is(2));
    }

    @Test
    public void shouldNoodleSoupDrawCard() {
        // Given the Noodle soup strategy,
        PowerStrategy strat = new NoodleSoupPowerStrategy();
        // When the power is invoked,
        strat.usePower(gameSpy);
        // then a card is drawn for the player in turn.
        assertThat(gameSpy.getLastActionType(), is(GameAction.DRAW_CARD));
    }

    @Test
    public void shouldSpringRollsDestroyRandomMinion() {
        // Given a SpringRollsPowerStrategy object,
        CardSpy cardSpy = gameSpy.getCardSpy();
        PowerStrategy strat = new SpringRollsPowerStrategy(
                new FixedGeneratorStrategy(0)
        );
        // When the power is invoked,
        strat.usePower(gameSpy);
        // Then a random opponent minion is destroyed, if any.
        assertThat(cardSpy.getLastActionType(), is(GameAction.SET_CARD_HEALTH));
        assertThat(cardSpy.getLastActionParams(), is(0));
    }

    @Test
    public void shouldBakedSalmonAdd2AtkToRandOppMinion() {
        // Given a BakedSalmonPowerStrategy object,
        CardSpy cardSpy = gameSpy.getCardSpy();
        PowerStrategy strat = new BakedSalmonPowerStrategy(
                new FixedGeneratorStrategy(0)
        );
        // When the power is invoked,
        final int initialAttack = cardSpy.getAttack();
        strat.usePower(gameSpy);
        // Then a random opponent minion's attack is increased by 2.
        assertThat(cardSpy.getLastActionType(), is(GameAction.SET_ATTACK));
        assertThat(cardSpy.getLastActionParams(), is(initialAttack + 2));
    }

    // One integration test
    @Test
    public void shouldExecuteCardEffectsWithEtaVariant() {
        // Given a game with etafactory,
        UIGame game = new StandardHotStoneGame(new EtaFactory());
        game.endTurn();
        game.endTurn();
        // When we play brown rice card,
        int healthBefore = game.getHero(Player.computeOpponent(game.getPlayerInTurn())).getHealth();
        game.playCard(3,0);
        // then should deal one damage to opponent
        int healthAfter = game.getHero(Player.computeOpponent(game.getPlayerInTurn())).getHealth();
        assertThat(healthAfter, is(healthBefore-1));
    }
}

