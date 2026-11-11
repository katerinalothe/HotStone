package hotstone.view.tool;

import hotstone.framework.Card;
import hotstone.framework.Status;
import hotstone.framework.UIGame;
import hotstone.view.GfxConstants;
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

public class HeroPowerTool extends NullTool{

    protected final HotStoneDrawing hotStoneDrawing;
    private final UIGame game;
    private final DrawingEditor editor;
    private HeroFigure draggedActor;

    public HeroPowerTool(DrawingEditor editor, UIGame game) {
        // Note - we need access to the specialized 'hotseat'
        // behaviour, to swap UI in the model, alas a hard
        // coupling, made by casting...
        this.editor = editor;
        hotStoneDrawing = (HotStoneDrawing) editor.drawing();
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {}

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        Drawing model = editor.drawing();
        Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
        draggedActor = (HeroFigure) figureAtPosition;
        if(((HeroFigure) figureAtPosition).getAssociatedHero().getOwner() != game.getPlayerInTurn()) {
            return;
        }

        Status status = game.usePower();
        editor.showStatus("Use hero power. Result = " + status);
        draggedActor = null;
    }
}
