package hotstone.standard;

import hotstone.framework.Strategies.ManaStrategy;
import hotstone.standard.Strategies.ManaStrategy.AlphaManaStrategy;
import hotstone.standard.Strategies.ManaStrategy.BetaManaStrategy;
import hotstone.standard.Strategies.ManaStrategy.DeltaManaStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestManaStrategy {
    private ManaStrategy alphaManaStrategy;
    private ManaStrategy betaManaStrategy;
    private ManaStrategy deltaManaStrategy;
    @BeforeEach
    public void setUp() {
        alphaManaStrategy = new AlphaManaStrategy();
        betaManaStrategy = new BetaManaStrategy();
        deltaManaStrategy = new DeltaManaStrategy();
    }

    @Test
    public void alphaShouldReturn3ForEveryRound() {
        for(int i=0; i<5; i++) {
            assertThat(alphaManaStrategy.calculate(i), is(3));
        }
    }

    @Test
    public void betaShouldReturnNumberOfRoundsUntil7() {
        for(int i=1; i<9; i++) {
            assertThat(betaManaStrategy.calculate(i), is(Math.min(i, 7)));
        }
    }

    @Test
    public void deltaShouldReturn5ForEveryRound() {
        for(int i=0; i<5; i++) {
            assertThat(deltaManaStrategy.calculate(i), is(5));
        }
    }
}
