package hotstone.standard.Strategies.PowerStrategy.CardPower;

import hotstone.framework.MutableGame;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.WizardHubAdapter;
import wizardhub.EffectWizard;

public class NoodleSoupPowerStrategy implements PowerStrategy {
    @Override
    public void usePower(MutableGame game) {
        new EffectWizard(new WizardHubAdapter(game)).forMe().doDrawCard();
    }

    @Override
    public String getName() {
        return "Noodle Soup";
    }

    @Override
    public String getDescription() {
        return "Draws card";
    }

    @Override
    public int getPowerCost() {
        return 0;
    }
}
