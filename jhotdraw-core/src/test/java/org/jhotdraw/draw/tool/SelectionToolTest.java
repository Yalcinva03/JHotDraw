package org.jhotdraw.draw.tool;

import org.jhotdraw.draw.*;
import org.jhotdraw.draw.figure.LineFigure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import static org.testng.Assert.*;

public class SelectionToolTest {

    private DrawingEditor editor;
    private DrawingView view;
    private SelectionTool selectionTool;
    private LineFigure lineFigure;

    @BeforeMethod
    public void setUp() {
        editor = new DefaultDrawingEditor();
        view = new DefaultDrawingView();
        view.setDrawing(new DefaultDrawing());
        editor.add(view);
        selectionTool = new SelectionTool();
        editor.setTool(selectionTool);

        lineFigure = new LineFigure();
        lineFigure.setBounds(new Point2D.Double(40, 40), new Point2D.Double(200, 40));
        view.getDrawing().add(lineFigure);
    }

    @Test
    public void testSelectFigure() {
        view.clearSelection();
        assertTrue(view.getSelectedFigures().isEmpty());

        view.addToSelection(lineFigure);
        assertFalse(view.getSelectedFigures().isEmpty());
        assertTrue(view.getSelectedFigures().contains(lineFigure));
    }

    @Test
    public void testClearSelection() {
        view.addToSelection(lineFigure);
        assertFalse(view.getSelectedFigures().isEmpty());

        view.clearSelection();
        assertTrue(view.getSelectedFigures().isEmpty());
    }

    @Test
    public void testMousePressedSelectFigure() {
        view.clearSelection();
        assertTrue(view.getSelectedFigures().isEmpty());

        simulateMousePress(100, 40);

        assertTrue(view.getSelectedFigures().contains(lineFigure));
        assertFalse(view.getSelectedFigures().isEmpty());
    }

    @Test
    public void testMultipleFigureSelection() {
        LineFigure anotherFigure = new LineFigure();
        anotherFigure.setBounds(new Point2D.Double(50, 50), new Point2D.Double(150, 50));
        view.getDrawing().add(anotherFigure);

        view.clearSelection();
        assertTrue(view.getSelectedFigures().isEmpty());

        view.addToSelection(lineFigure);
        view.addToSelection(anotherFigure);

        assertTrue(view.getSelectedFigures().contains(lineFigure));
        assertTrue(view.getSelectedFigures().contains(anotherFigure));
        assertEquals(view.getSelectedFigures().size(), 2);
    }

    @Test
    public void testDeselectFigure() {
        view.addToSelection(lineFigure);
        assertFalse(view.getSelectedFigures().isEmpty());

        view.removeFromSelection(lineFigure);
        assertTrue(view.getSelectedFigures().isEmpty());
    }

    private void simulateMousePress(int x, int y) {
        MouseEvent pressEvent = createMouseEvent(view, x, y);
        selectionTool.mousePressed(pressEvent);
        selectionTool.mouseReleased(pressEvent);
    }

    private MouseEvent createMouseEvent(DrawingView view, int x, int y) {
        return new MouseEvent(view.getComponent(), MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, x, y, 1, false, 0);
    }
}