package hotstone.standard.Strategies.PowerStrategy.HeroPower;

import hotstone.framework.MutableCard;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Strategies.NumberGeneratorStrategy;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.GameConstants;

public class FrenchChefPowerStrategy implements PowerStrategy {
    NumberGeneratorStrategy generatorStrategy;

    public FrenchChefPowerStrategy(NumberGeneratorStrategy generatorStrategy) {
        this.generatorStrategy = generatorStrategy;
    }

    @Override
    public void usePower(MutableGame game) {
        Player opponent = Player.computeOpponent(game.getPlayerInTurn());
        int fieldSizeOpponent = game.getFieldSize(opponent);

        boolean fieldNotEmpty = (fieldSizeOpponent > 0);

        if(fieldNotEmpty) {
            MutableCard card = game.getCardInField(opponent, generatorStrategy.generate(0, fieldSizeOpponent - 1));
            game.hurtCard(card, GameConstants.FRENCH_CHEF_HERO_POWER_CARD_ATTACK_OPPONENT_CARD);
        }
    }

    @Override
    public String getName() {
        return GameConstants.FRENCH_CHEF_HERO_POWER_NAME;
    }

    @Override
    public String getDescription() {
        return GameConstants.FRENCH_CHEF_HERO_POWER_DESCRIPTION;
    }

    @Override
    public int getPowerCost() {
        return GameConstants.HERO_POWER_COST;
    }
}
