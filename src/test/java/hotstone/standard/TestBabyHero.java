
package hotstone.standard;

import hotstone.framework.Hero;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
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
    public void shouldHavePowerNamedCute() {
        // Given a Baby hero,
        // When we check its initial health,
        // Then it should be 21.
        assertThat(baby.getHealth(), is(21));
    }

}
