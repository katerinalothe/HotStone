package hotstone.standard.Strategies.PowerStrategy.CardPower;

import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Strategies.NumberGeneratorStrategy;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.WizardHubAdapter;
import wizardhub.EffectWizard;

public class BakedSalmonPowerStrategy implements PowerStrategy {

    private final NumberGeneratorStrategy generator;

    public BakedSalmonPowerStrategy(NumberGeneratorStrategy generator) {
        this.generator = generator;
    }

    @Override
    public void usePower(MutableGame game) {
        int fieldSize = game.getFieldSize(Player.computeOpponent(game.getPlayerInTurn()));

        if (fieldSize < 1) {return;}

        new EffectWizard(new WizardHubAdapter(game)).forOpponent()
                .forMinionAt(generator.generate(0, fieldSize - 1))
                .doChangeAttack(2);
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
