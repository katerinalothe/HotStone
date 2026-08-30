package hotstone.standard;

import hotstone.framework.Player;

public class StandardCard implements hotstone.framework.Card {

    private final String name;
    private int mana;
    private int attack;
    private int health;

    public StandardCard(String name) {
        this.name = name;
        switch (name) {
            case GameConstants.UNO_CARD:
               mana = 1;
               attack = 1;
               health = 1;
               break;
            case GameConstants.DOS_CARD:
                mana = 2;
                attack = 2;
                health = 2;
                break;
            case GameConstants.TRES_CARD:
                mana = 3;
                attack = 3;
                health = 3;
                break;
            case GameConstants.CUATRO_CARD:
                mana = 2;
                attack = 3;
                health = 1;
                break;
            case GameConstants.CINCO_CARD:
                mana = 3;
                attack = 5;
                health = 1;
                break;
            case GameConstants.SEIS_CARD:
                mana = 2;
                attack = 1;
                health = 3;
                break;
            case GameConstants.SIETE_CARD:
                mana = 3;
                attack = 2;
                health = 4;
                break;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getManaCost() {
        return mana;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public String getEffectDescription() {
        return "";
    }
}
