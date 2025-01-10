package org.jhotdraw.draw.BDD;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.*;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.figure.LineFigure;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class GivenDrawingEditor extends Stage<GivenDrawingEditor> {

    @ExpectedScenarioState
    protected DrawingEditor editor;

    @ExpectedScenarioState
    protected DrawingView view;

    @ExpectedScenarioState
    protected List<Figure> figures;

    public GivenDrawingEditor the_drawing_editor_contains_items() {

        editor = new DefaultDrawingEditor();
        view = new DefaultDrawingView();
        view.setDrawing(new DefaultDrawing());
        editor.add(view);


        figures = new ArrayList<>();


        LineFigure lineFigure = new LineFigure();
        lineFigure.setBounds(new Point2D.Double(40, 40), new Point2D.Double(200, 40));

        view.getDrawing().add(lineFigure);


        figures.add(lineFigure);

        return this;
    }

    public GivenDrawingEditor the_user_has_selected_one_or_more_items_using_the_selection_tool() {

        if (editor == null || view == null) {
            the_drawing_editor_contains_items();
        }
        if (figures == null || figures.isEmpty()) {
            the_drawing_editor_contains_items();
        }

        view.addToSelection(figures.get(0));

        return this;
    }
}