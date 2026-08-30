package hotstone.standard;

import hotstone.framework.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBabyHero {
    private Hero baby;

    @BeforeEach
    public void setup() {
        baby = new BabyHero();
    }

    @Test
    public void shouldBeTypeBaby() {
        // Given a Baby hero,
        // When we check its type,
        // Then it should be "Baby".
        assertThat(baby.getType(), is("Baby"));
    }

    @Test
    public void shouldHaveHealth21() {
        // Given a Baby hero,
        // When we check its initial health,
        // Then it should be 21.
        assertThat(baby.getHealth(), is(21));
    }

    @Test
    public void shouldStartWith3Mana() {
        // Given a Baby hero,
        // When we check its initial mana,
        // Then it should be 3.
        assertThat(baby.getMana(), is(3));
    }

    @Test
    public void shouldHavePowerThatCosts2Mana() {
        // Given a Baby hero
        // When we check how much mana the power costs
        // Then the power should cost 2.
        assertThat(baby.getPowerManaCost(), is(2));
    }

    @Test
    public void shouldHavePowerThatIsCalledCute() {
        // Given a Baby hero,
        // When we check its power's name,
        // Then it should be "Cute".
        assertThat(baby.getPowerName(), is("Cute"));
    }

    @Test
    public void shouldHavePowerEffectDescribedAsDoingNothing() {
        // Given a Baby hero,
        // When we check its power's effect description,
        // Then it should be "Does nothing".
        assertThat(baby.getEffectDescription(), is("Does nothing"));
    }

    @Test
    public void shouldBeAbleToUsePowerIfHerosManaIsMoreOrEqualToPowerCost() {
        // Given a BabyHero2 with mana equal to the cost, and a BabyHero1 with mana more than the cost (initial 3),
        // When we use the power with cost 2,
        // Then both should use the power and return true.
        BabyHero baby2 = new BabyHero();
        baby2.setMana(2);
        assertThat(baby.usePower(), is(true));
        assertThat(baby2.usePower(), is(true));
    }

    @Test
    public void shouldDeductHerosManaToUsePower() {
        // Given a Baby hero, with initial mana 3,
        // When we use the power with cost 2,
        // Then Baby Hero should have 1 mana.
        boolean result = baby.usePower();
        assertThat(baby.getMana(), is(1));
        assertThat(result, is(true));
    }

    @Test
    public void shouldNotUsePowerIfHeroDoesNotHaveEnoughMana() {
        // Given a Baby hero, with mana 1,
        // When we use the power with cost 2,
        // Then Power should not work.
        baby.setMana(1);
        assertThat(baby.usePower(), is(false));
    }
}
