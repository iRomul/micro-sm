package io.github.iromul.microsm.transition;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static io.github.iromul.microsm.transition.TestStates.STATE_1;
import static io.github.iromul.microsm.transition.TestStates.STATE_2;
import static io.github.iromul.microsm.transition.TestStates.STATE_3;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleTransitionTest {

    @Test
    void getTarget_should_return_fixed_target_state() {
        SimpleTransition<TestStates> transition = new SimpleTransition<>(STATE_1, STATE_2);

        assertThat(transition.getTarget()).isEqualTo(STATE_2);
    }

    @Test
    void isTransitionAllowed_should_allow_transition_if_source_matches_fixed_source_state() {
        SimpleTransition<TestStates> transition = new SimpleTransition<>(STATE_1, STATE_2);

        assertThat(transition.isTransitionAllowed(STATE_1)).isTrue();
    }

    @Test
    void isTransitionAllowed_should_allow_transition_if_source_is_true_for_fixed_sourceFunction() {
        Function<TestStates, Boolean> f = s -> s == STATE_1;

        SimpleTransition<TestStates> transition = new SimpleTransition<>(f, STATE_2);

        assertThat(transition.isTransitionAllowed(STATE_1)).isTrue();
    }

    @Test
    void isTransitionAllowed_should_not_allow_transition_if_source_doesnt_matches_fixed_source_state() {
        SimpleTransition<TestStates> transition = new SimpleTransition<>(STATE_1, STATE_2);

        assertThat(transition.isTransitionAllowed(STATE_3)).isFalse();
    }

    @Test
    void isTransitionAllowed_should_not_allow_transition_if_source_is_false_for_fixed_sourceFunction() {
        Function<TestStates, Boolean> f = s -> s == STATE_1;

        SimpleTransition<TestStates> transition = new SimpleTransition<>(f, STATE_2);

        assertThat(transition.isTransitionAllowed(STATE_3)).isFalse();
    }
}
