package org.jhotdraw.draw.BDD;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import static org.assertj.core.api.Assertions.assertThat;

import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;

import java.util.List;

public class ThenExpectations extends Stage<ThenExpectations> {

    @ExpectedScenarioState
    protected DrawingView view;

    @ExpectedScenarioState
    protected List<Figure> figures;

    public ThenExpectations the_item_should_be_selected() {

        assertThat(view.getSelectedFigures())
                .as("Expected at least one figure to be selected")
                .isNotEmpty();

        Figure expected = figures.get(0);
        assertThat(view.getSelectedFigures())
                .as("Expected the first figure to be selected")
                .contains(expected);

        return this;
    }

    public ThenExpectations the_edit_operation_should_apply_to_all_selected_items() {

        for (Figure f : figures) {

            if (view.getSelectedFigures().contains(f)) {

                assertThat(view.getDrawing().getChildren())
                        .doesNotContain(f);
            }
        }
        return this;
    }
}