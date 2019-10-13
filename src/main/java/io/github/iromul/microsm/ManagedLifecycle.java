package io.github.iromul.microsm;

import io.github.iromul.microsm.transition.Choice;
import io.github.iromul.microsm.transition.TransitionManager;

import java.util.function.Function;

/**
 * Interface indicates that stateful object can be transited to another state. This interface add ability to configure
 * transitions constraints within lifecycle methods with transition manager object.
 *
 * @param <S> state type
 */
public interface ManagedLifecycle<S> extends Lifecycle<S> {

    /**
     * Create new transition manager for this lifecycle object
     *
     * @return new transition manager
     */
    default TransitionManager<S> withTransitions() {
        return new TransitionManager<>(this);
    }

    /**
     * Create new choice transition
     *
     * @return new choice transition
     */
    default Choice<S> choice() {
        return new Choice<>();
    }

    /**
     * Method can be used as source state predicate and indicate that transition can be performed from any state.
     *
     * @return transition predicate
     */
    default Function<S, Boolean> anyState() {
        return s -> true;
    }
}
