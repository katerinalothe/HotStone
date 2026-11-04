package hotstone.standard;

import hotstone.framework.SecureHero;
import hotstone.framework.Strategies.HeroStrategy;
import hotstone.framework.Player;
import hotstone.standard.Strategies.HeroStrategy.AlphaHeroStrategy;
import hotstone.standard.Strategies.HeroStrategy.GammaHeroStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestHeroAndPowerStrategy {
    private HeroStrategy alpha;
    private HeroStrategy gamma;

    @BeforeEach
    public void setUp() {
        alpha = new AlphaHeroStrategy();
        gamma = new GammaHeroStrategy();
    }

    @Test
    public void alphaShouldBeBabyHeroWithPowerCute() {
        SecureHero findus = alpha.generate(Player.FINDUS);
        assertThat(findus.getType(), is(GameConstants.BABY_HERO_TYPE));
        assertThat(findus.getPowerName(), is("Cute"));
    }

    @Test
    public void gammaShouldBeThaiChefForFindusAndDanishForPedderson() {
        SecureHero findus = gamma.generate(Player.FINDUS);
        SecureHero peddersen = gamma.generate(Player.PEDDERSEN);
        assertThat(findus.getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));
        assertThat(peddersen.getType(), is(GameConstants.DANISH_CHEF_HERO_TYPE));
    }

    @Test
    public void gammaShouldHavePowerChiliForThaiAndSovsForDanish() {
        SecureHero findus = gamma.generate(Player.FINDUS);
        SecureHero peddersen = gamma.generate(Player.PEDDERSEN);
        assertThat(findus.getPowerName(), is("Chili"));
        assertThat(peddersen.getPowerName(), is("Sovs"));
    }
}
