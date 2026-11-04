package hotstone.standard.Strategies.PowerStrategy.CardPower;

import hotstone.framework.MutableGame;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.WizardHubAdapter;
import wizardhub.EffectWizard;

public class PokeBowlPowerStrategy implements PowerStrategy {
    @Override
    public void usePower(MutableGame game) {
        new EffectWizard(new WizardHubAdapter(game)).forMe()
                .forHero()
                .doChangeHealthOrRemove(2);
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public int getPowerCost() {
        return 0;
    }
}
