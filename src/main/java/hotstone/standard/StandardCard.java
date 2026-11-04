package hotstone.standard;

import hotstone.framework.*;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.Strategies.PowerStrategy.HeroPower.NullPowerStrategy;

public class StandardCard implements MutableCard {

    private Player owner;
    private final String name;
    private final PowerStrategy power;
    private int mana;
    private int attack;
    private int health;
    private boolean isActive = false;

    private void init(int mana, int attack, int health) {
        this.mana = mana;
        this.attack = attack;
        this.health = health;
    }

    public StandardCard(String name, int mana, int attack, int health) {
        this.owner = null;
        this.name = name;
        init(mana, attack, health);
        power = new NullPowerStrategy();
    }

    public StandardCard(
            String name, int mana, int attack, int health,
            PowerStrategy power) {
        this.owner = null;
        this.name = name;
        init(mana, attack, health);
        this.power = power;
    }

    @Override
    public void usePower(MutableGame game) {
        power.usePower(game);
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
    public void setAttack(int attack) {this.attack = attack;}

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setOwner(Player player) {
        owner = player;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public String getEffectDescription() {
        return "";
    }

    @Override
    public void setActive(boolean b) {
        isActive = b;
    }

    @Override
    public void setHealth(int amount) {
        health = amount;
    }

}
