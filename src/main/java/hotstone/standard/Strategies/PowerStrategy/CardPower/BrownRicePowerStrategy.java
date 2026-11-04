package hotstone.standard.Strategies.PowerStrategy.CardPower;

import hotstone.framework.MutableGame;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.WizardHubAdapter;
import wizardhub.EffectWizard;


public class BrownRicePowerStrategy implements PowerStrategy {

    @Override
    public void usePower(MutableGame game) {
        new EffectWizard(new WizardHubAdapter(game))
                .forOpponent().forHero()
                .doChangeHealthOrRemove(-1);
    }

    @Override
    public String getName() {
        return "Brown Rice name.";
    }

    @Override
    public String getDescription() {
        return "Brown Rice description.";
    }

    @Override
    public int getPowerCost() {
        return 0;
    }
}
