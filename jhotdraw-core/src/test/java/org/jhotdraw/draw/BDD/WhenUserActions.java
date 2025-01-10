package org.jhotdraw.draw.BDD;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.tool.SelectionTool;
import org.jhotdraw.draw.tool.Tool;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class WhenUserActions extends Stage<WhenUserActions> {

    @ExpectedScenarioState
    protected DrawingEditor editor;

    @ExpectedScenarioState
    protected DrawingView view;

    @ExpectedScenarioState
    protected List<Figure> figures;

    @ExpectedScenarioState
    protected Tool selectionTool;

    public WhenUserActions the_user_selects_an_item_using_the_selection_tool() {
        // Initialize a SelectionTool if needed
        if (selectionTool == null) {
            selectionTool = new SelectionTool();
            editor.setTool(selectionTool);
        }

        // Only attempt selection if we have at least one figure
        if (!figures.isEmpty()) {
            Figure firstFigure = figures.get(0);

            // Obtain the bounding box of the figure
            Rectangle2D bounds = firstFigure.getBounds();

            // Compute the center of the bounding box
            Point2D.Double center = new Point2D.Double(bounds.getCenterX(), bounds.getCenterY());

            // Simulate a mouse pressed & released at that center point
            MouseEvent pressEvent = new MouseEvent(
                    view.getComponent(),
                    MouseEvent.MOUSE_PRESSED,
                    System.currentTimeMillis(),
                    0,
                    (int) center.x,
                    (int) center.y,
                    1,
                    false
            );
            MouseEvent releaseEvent = new MouseEvent(
                    view.getComponent(),
                    MouseEvent.MOUSE_RELEASED,
                    System.currentTimeMillis(),
                    0,
                    (int) center.x,
                    (int) center.y,
                    1,
                    false
            );

            // Pass these events to the SelectionTool
            selectionTool.mousePressed(pressEvent);
            selectionTool.mouseReleased(releaseEvent);
        }
        return this;
    }

    public WhenUserActions the_user_performs_an_edit_operation(String operation) {
        if ("delete".equalsIgnoreCase(operation)) {
            view.getDrawing().removeAll(view.getSelectedFigures());
        }
        return this;
    }
}