package hotstone.standard.Strategies.PowerStrategy.CardPower;

import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Strategies.NumberGeneratorStrategy;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.WizardHubAdapter;
import wizardhub.EffectWizard;

public class TomatoSaladPowerStrategy implements PowerStrategy {

    private final NumberGeneratorStrategy generator;

    public TomatoSaladPowerStrategy(NumberGeneratorStrategy generator) {
        this.generator = generator;
    }

    @Override
    public void usePower(MutableGame game) {
        int fieldFindus = game.getFieldSize(Player.FINDUS);
        int fieldPeddersen = game.getFieldSize(Player.PEDDERSEN);

        if (fieldFindus + fieldPeddersen < 1) {
            return;
        }
        int index = generator.generate(0, fieldFindus + fieldPeddersen - 1);

        Player player;
        if (index < fieldFindus) {
            player = Player.FINDUS;
        } else {
            player = Player.PEDDERSEN;
            index = index - fieldFindus;
        }

        EffectWizard effectWizard = new EffectWizard(new WizardHubAdapter(game));
        if (player == game.getPlayerInTurn()) {
            effectWizard.forMe().forMinionAt(index).doChangeAttack(1);
        } else {
            effectWizard.forOpponent().forMinionAt(index).doChangeAttack(1);
        }
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
