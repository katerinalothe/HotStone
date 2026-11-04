package hotstone.standard;

import hotstone.framework.MutableCard;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;

public class WizardHubAdapter implements wizardhub.HotStoneGameTarget {
    MutableGame game;

    public WizardHubAdapter(MutableGame game) {
        this.game = game;
    }

    @Override
    public int getThePlayerInTurn() {
        return getCodeOfPlayer(game.getPlayerInTurn());
    }

    @Override
    public int getSizeOfField(int i) {
        return game.getFieldSize(getPlayerOfCode(i));
    }

    @Override
    public void deltaHealthOrRemoveMinion(int who, int fieldIndex, int amount) {
        MutableCard card = game.getCardInField(getPlayerOfCode(who), fieldIndex);
        game.hurtCard(card, -amount);
    }

    @Override
    public void deltaAttackMinion(int who, int fieldIndex, int amount) {
        MutableCard card = game.getCardInField(getPlayerOfCode(who), fieldIndex);
        card.setAttack(card.getAttack()+amount);
    }

    @Override
    public void setTauntCategoryMinion(int i, int i1, boolean b) {

    }

    @Override
    public void deltaHealthHero(int who, int amount) {
        game.hurtHero(getPlayerOfCode(who), -amount);
    }

    @Override
    public void drawCard(int times) {
        for(int i = 0; i<times; i++)
            game.drawCard();
    }

    private int getCodeOfPlayer(Player player) {
        if(player == Player.FINDUS) return 0;
        return 1;
    }

    private Player getPlayerOfCode(int i) {
        if(i == 0) return Player.FINDUS;
        return Player.PEDDERSEN;
    }
}
