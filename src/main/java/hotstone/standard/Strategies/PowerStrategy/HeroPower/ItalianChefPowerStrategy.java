package hotstone.standard.Strategies.PowerStrategy.HeroPower;

import hotstone.framework.MutableCard;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Strategies.NumberGeneratorStrategy;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.GameConstants;

public class ItalianChefPowerStrategy implements PowerStrategy {
    NumberGeneratorStrategy generatorStrategy;

    public ItalianChefPowerStrategy(NumberGeneratorStrategy generatorStrategy) {
        this.generatorStrategy = generatorStrategy;
    }

    @Override
    public void usePower(MutableGame game) {
        Player playerInTurn = game.getPlayerInTurn();
        int fieldSize = game.getFieldSize(playerInTurn);

        boolean fieldNotEmpty = (fieldSize > 0);

        if(fieldNotEmpty) {
            MutableCard card = game.getCardInField(
                            playerInTurn,
                            generatorStrategy.generate(0, fieldSize - 1)
                    );
            card.setAttack(card.getAttack() +
                    GameConstants.ITALIAN_CHEF_HERO_POWER_CARD_ADD_TO_ATTACK_VALUE
            );
        }
    }

    @Override
    public String getName() {
        return GameConstants.ITALIAN_CHEF_HERO_POWER_NAME;
    }

    @Override
    public String getDescription() {
        return GameConstants.ITALIAN_CHEF_HERO_POWER_DESCRIPTION;
    }

    @Override
    public int getPowerCost() {
        return GameConstants.HERO_POWER_COST;
    }
}
