package org.jhotdraw.draw.BDD;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

public class BDDStoryTest extends ScenarioTest<GivenDrawingEditor, WhenUserActions, ThenExpectations> {

    @Test
    public void select_an_item() {
        given().the_drawing_editor_contains_items();
        when().the_user_selects_an_item_using_the_selection_tool();
        then().the_item_should_be_selected();
    }

    @Test
    public void apply_edit_operation_to_selected_items() {
        given().the_user_has_selected_one_or_more_items_using_the_selection_tool();
        when().the_user_performs_an_edit_operation("delete");
        then().the_edit_operation_should_apply_to_all_selected_items();
    }
}