package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.IPCException;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Player;
import hotstone.framework.SecureHero;
import jakarta.servlet.http.HttpServletResponse;

public class HeroClientProxy implements SecureHero, ClientProxy {

    public final String HERO_OBJECT_ID;

    private final Requestor requestor;

    public HeroClientProxy(String heroObjectId, Requestor requestor) {
        this.HERO_OBJECT_ID = heroObjectId;
        this.requestor = requestor;
    }

    @Override
    public int getMana() {
        try {
            return requestor.sendRequestAndAwaitReply(
                    HERO_OBJECT_ID,
                    OperationNames.HERO_GET_MANA,
                    Integer.class);
        } catch(IPCException e) {
            if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
                throw e;
            }
        }
        return -1;
    }

    @Override
    public String getPowerName() {
        try {
            return requestor.sendRequestAndAwaitReply(
                    HERO_OBJECT_ID,
                    OperationNames.HERO_GET_POWER_NAME,
                    String.class
            );
        } catch(IPCException e) {
            if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
                throw e;
            }
        }
        return null;
    }

    @Override
    public int getPowerManaCost() {
        try {
            return requestor.sendRequestAndAwaitReply(
                    HERO_OBJECT_ID,
                    OperationNames.HERO_GET_POWER_COST,
                    Integer.class
            );
        } catch(IPCException e) {
            if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
                throw e;
            }
        }
        return -1;
    }

    @Override
    public int getHealth() {
        try {
            return requestor.sendRequestAndAwaitReply(
                    HERO_OBJECT_ID,
                    OperationNames.HERO_GET_HEALTH,
                    Integer.class
            );
        } catch(IPCException e) {
            if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
                throw e;
            }
        }
        return -1;
    }

    @Override
    public String getType() {
        try {
            return requestor.sendRequestAndAwaitReply(
                    HERO_OBJECT_ID,
                    OperationNames.HERO_GET_TYPE,
                    String.class
            );
        } catch(IPCException e) {
            if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
                throw e;
            }
        }
        return null;
    }

    @Override
    public Player getOwner() {
        try {
            return requestor.sendRequestAndAwaitReply(
                    HERO_OBJECT_ID,
                    OperationNames.HERO_GET_OWNER,
                    Player.class
            );
        } catch(IPCException e) {
            if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
                throw e;
            }
        }
        return null;
    }

    @Override
    public String getEffectDescription() {
        try {
            return requestor.sendRequestAndAwaitReply(
                    HERO_OBJECT_ID,
                    OperationNames.HERO_GET_EFFECT_DESCRIPTION,
                    String.class
            );
        } catch(IPCException e) {
            if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
                throw e;
            }
        }
        return null;
    }

    @Override
    public boolean isDefeated() {
        try {
            return requestor.sendRequestAndAwaitReply(
                    HERO_OBJECT_ID,
                    OperationNames.HERO_IS_DEFEATED,
                    Boolean.class
            );
        } catch(IPCException e) {
            if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
                throw e;
            }
        }
        return false;
    }

    @Override
    public boolean canUsePower() {
        try {
            return requestor.sendRequestAndAwaitReply(
                    HERO_OBJECT_ID,
                    OperationNames.HERO_CAN_USE_POWER,
                    Boolean.class
            );
        } catch(IPCException e) {
            if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
                throw e;
            }
        }
        return false;
    }
}
