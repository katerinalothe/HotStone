package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.IPCException;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Card;
import hotstone.framework.Player;
import jakarta.servlet.http.HttpServletResponse;

public class CardClientProxy implements Card, ClientProxy {

    public final String CARD_OBJECT_ID;

    private final Requestor requestor;

    public CardClientProxy(String cardObjectId, Requestor requestor) {
        CARD_OBJECT_ID = cardObjectId;
        this.requestor = requestor;
    }

    @Override
    public String getName() {
        try {
            return requestor.sendRequestAndAwaitReply(
                    CARD_OBJECT_ID,
                    OperationNames.CARD_GET_NAME,
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
    public int getManaCost() {
        try {
            return requestor.sendRequestAndAwaitReply(
                    CARD_OBJECT_ID,
                    OperationNames.CARD_GET_MANA_COST,
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
    public int getAttack() {
        try {
            return requestor.sendRequestAndAwaitReply(
                    CARD_OBJECT_ID,
                    OperationNames.CARD_GET_ATTACK,
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
                    CARD_OBJECT_ID,
                    OperationNames.CARD_GET_HEALTH,
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
    public boolean isActive() {
        try {
            return requestor.sendRequestAndAwaitReply(
                    CARD_OBJECT_ID,
                    OperationNames.CARD_IS_ACTIVE,
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
    public Player getOwner() {
        try {
            return requestor.sendRequestAndAwaitReply(
                    CARD_OBJECT_ID,
                    OperationNames.CARD_GET_OWNER,
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
                    CARD_OBJECT_ID,
                    OperationNames.CARD_GET_EFFECT_DESCRIPTION,
                    String.class
            );
        } catch(IPCException e) {
            if (e.getStatusCode() != HttpServletResponse.SC_NOT_FOUND) {
                throw e;
            }
        }
        return null;
    }
}
