package io.github.iromul.microsm.transition;

import org.junit.jupiter.api.Test;

import static io.github.iromul.microsm.transition.TestStates.STATE_1;
import static io.github.iromul.microsm.transition.TestStates.STATE_2;
import static io.github.iromul.microsm.transition.TestStates.STATE_3;
import static org.assertj.core.api.Assertions.assertThat;

class ChoiceTransitionTest {

    @Test
    void getTarget_should_return_target_state_regarding_choices_guards() {
        Choice<TestStates> choice = new Choice<TestStates>()
                .when(() -> true, STATE_2)
                .otherwise(STATE_3);

        ChoiceTransition<TestStates> transition = new ChoiceTransition<TestStates>(STATE_1, choice);

        assertThat(transition.getTarget()).isEqualTo(STATE_2);
    }
}
