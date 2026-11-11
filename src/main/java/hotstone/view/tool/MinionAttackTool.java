package hotstone.view.tool;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.UIGame;
import hotstone.view.core.HotStoneDrawing;
import hotstone.view.figure.CardFigure;
import hotstone.view.figure.HeroFigure;
import hotstone.view.figure.HotStoneFigure;
import hotstone.view.figure.HotStoneFigureType;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.ZOrder;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class MinionAttackTool extends NullTool {

    private final UIGame game;
    private final DrawingEditor editor;
    private CardFigure draggedActor;

    private int lastX;
    private int lastY;
    private int orgX;
    private int orgY;

    public MinionAttackTool(DrawingEditor editor, UIGame game) {
        this.editor = editor;
        this.game = game;
    }

    // From HotSeatStateTool, we can assume the mouse has clicked down on a minion.

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        // Get minion.
        // Verify the minion belongs to the active player.
        Drawing model = editor.drawing();
        draggedActor = (CardFigure) model.findFigure(
                e.getX(), e.getY()
        );
        if (draggedActor.getAssociatedCard().getOwner() != game.getPlayerInTurn()) {
            draggedActor = null;
            return;
        }
        model.zOrder(draggedActor, ZOrder.TO_TOP);
        lastX = x; lastY = y;
        orgX = x; orgY = y;
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        // Drag selected minion.
        draggedActor.moveBy(x - lastX, y - lastY);
        lastX = x; lastY = y;
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        if (draggedActor == null) {return;}

        Player activePlayer = game.getPlayerInTurn();
        Player oppPlayer = Player.computeOpponent(activePlayer);
        // Release minion.
        draggedActor.moveBy(orgX - x, orgY - y);
        // Check whether the mouse released over opp. hero or minion.
        Drawing model = editor.drawing();
        Figure target = model.findFigure(e.getX(), e.getY());

        if (target instanceof HotStoneFigure) {
            HotStoneFigureType figType = ((HotStoneFigure) target).getType();
            int ownFieldIndex = getFieldIndex(activePlayer, draggedActor.getAssociatedCard());

            // If it did, attempt the attack.
            // Print the outcome if it was a success.
            if (isValidTargetHero((HotStoneFigure) target)) {
                Status s = game.attackHero(ownFieldIndex);
                if (s == Status.OK) {
                    printHeroAttackMessage();
                }

            } else if (isValidTargetMinion((HotStoneFigure) target)) {
                Status s = game.attackCard(
                        ownFieldIndex,
                        getFieldIndex(
                                oppPlayer,
                                ((CardFigure) target).getAssociatedCard()
                        )
                );
                if (s == Status.OK) {
                    printMinionAttackMessage((CardFigure) target);
                }
            }

        }
        draggedActor = null;
    }

    private void printHeroAttackMessage() {
        Player p = game.getPlayerInTurn();
        editor.showStatus(String.format(
                "%s used %s to attack %s's hero.",
                p,
                draggedActor.getAssociatedCard().getName(),
                Player.computeOpponent(p)
        ));
    }

    private void printMinionAttackMessage(CardFigure minion) {
        Player p = game.getPlayerInTurn();
        editor.showStatus(String.format(
                "%s used %s to attack %s's %s.",
                p,
                draggedActor.getAssociatedCard().getName(),
                Player.computeOpponent(p),
                minion.getAssociatedCard().getName()
        ));
    }

    private boolean isValidTargetHero(HotStoneFigure fig) {
        return fig.getType() == HotStoneFigureType.HERO_FIGURE &&
                ((HeroFigure) fig).getAssociatedHero().getOwner() ==
                        Player.computeOpponent(game.getPlayerInTurn());
    }

    private boolean isValidTargetMinion(HotStoneFigure fig) {
        return fig.getType() == HotStoneFigureType.MINION_FIGURE &&
                ((CardFigure) fig).getAssociatedCard().getOwner() ==
                        Player.computeOpponent(game.getPlayerInTurn());
    }

    private int getFieldIndex(Player p, Card c) {
        for (int i = 0; i < game.getFieldSize(p); i++) {
            if (game.getCardInField(p, i) == c) {
                return i;
            }
        }
        throw new RuntimeException("Could not find card in own field.");
    }

}
