package io.github.iromul.microsm.transition;

import io.github.iromul.microsm.IllFormedStateMachine;
import org.junit.jupiter.api.Test;

import static io.github.iromul.microsm.transition.TestStates.STATE_1;
import static io.github.iromul.microsm.transition.TestStates.STATE_2;
import static io.github.iromul.microsm.transition.TestStates.STATE_3;
import static io.github.iromul.microsm.transition.TestStates.STATE_4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChoiceTest {

    @Test
    void findFirstAllowedTarget_should_return_state_of_first_path_if_first_guard_is_true_for_1_choice() {
        Choice<TestStates> choice = new Choice<TestStates>()
                .when(() -> true, STATE_1)
                .otherwise(STATE_2);

        assertThat(choice.findFirstAllowedTarget()).isEqualTo(STATE_1);
    }

    @Test
    void findFirstAllowedTarget_should_return_state_of_otherwise_path_if_first_guard_is_false_for_1_choice() {
        Choice<TestStates> choice = new Choice<TestStates>()
                .when(() -> false, STATE_1)
                .otherwise(STATE_2);

        assertThat(choice.findFirstAllowedTarget()).isEqualTo(STATE_2);
    }

    @Test
    void findFirstAllowedTarget_should_return_state_of_first_path_when_2_choices_with_all_true_guards() {
        Choice<TestStates> choice = new Choice<TestStates>()
                .when(() -> true, STATE_1)
                .when(() -> true, STATE_2)
                .otherwise(STATE_3);

        assertThat(choice.findFirstAllowedTarget()).isEqualTo(STATE_1);
    }

    @Test
    void findFirstAllowedTarget_should_return_state_of_first_path_when_2_choices_with_first_true_guards() {
        Choice<TestStates> choice = new Choice<TestStates>()
                .when(() -> true, STATE_1)
                .when(() -> false, STATE_2)
                .otherwise(STATE_3);

        assertThat(choice.findFirstAllowedTarget()).isEqualTo(STATE_1);
    }

    @Test
    void findFirstAllowedTarget_should_return_state_of_second_path_when_2_choices_with_second_true_guards() {
        Choice<TestStates> choice = new Choice<TestStates>()
                .when(() -> false, STATE_1)
                .when(() -> true, STATE_2)
                .otherwise(STATE_3);

        assertThat(choice.findFirstAllowedTarget()).isEqualTo(STATE_2);
    }

    @Test
    void findFirstAllowedTarget_should_return_otherwise_path_when_2_choices_with_all_false_guards() {
        Choice<TestStates> choice = new Choice<TestStates>()
                .when(() -> false, STATE_1)
                .when(() -> false, STATE_2)
                .otherwise(STATE_3);

        assertThat(choice.findFirstAllowedTarget()).isEqualTo(STATE_3);
    }

    @Test
    void findFirstAllowedTarget_should_return_state_of_first_paths_nested_choice_state_when_1_choice_with_first_guard_true() {
        Choice<TestStates> choice = new Choice<TestStates>()
                .when(() -> true, new Choice<TestStates>()
                        .when(() -> true, STATE_3)
                        .otherwise(STATE_4))
                .otherwise(STATE_2);

        assertThat(choice.findFirstAllowedTarget()).isEqualTo(STATE_3);
    }

    @Test
    void findFirstAllowedTarget_should_fail_if_choice_has_no_otherwise_state() {
        Choice<TestStates> choice = new Choice<TestStates>().when(() -> true, STATE_1);

        assertThatThrownBy(choice::findFirstAllowedTarget).isInstanceOf(IllFormedStateMachine.class);
    }
}
