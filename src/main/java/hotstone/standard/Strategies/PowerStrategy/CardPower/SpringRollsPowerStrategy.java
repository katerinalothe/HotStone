package hotstone.standard.Strategies.PowerStrategy.CardPower;

import hotstone.framework.MutableCard;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Strategies.NumberGeneratorStrategy;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.WizardHubAdapter;
import wizardhub.EffectWizard;

public class SpringRollsPowerStrategy implements PowerStrategy {

    private final NumberGeneratorStrategy generator;

    public SpringRollsPowerStrategy(NumberGeneratorStrategy generator) {
        this.generator = generator;
    }

    @Override
    public void usePower(MutableGame game) {
        final Player opp = Player.computeOpponent(game.getPlayerInTurn());
        final int fieldSize = game.getFieldSize(opp);

        if (fieldSize < 1) {return;}
        int index = generator.generate(0, fieldSize - 1);
        final MutableCard target = game.getCardInField(
                opp, index
                );

        new EffectWizard(new WizardHubAdapter(game))
                .forOpponent()
                .forMinionAt(index)
                .doChangeHealthOrRemove(-target.getHealth());
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
