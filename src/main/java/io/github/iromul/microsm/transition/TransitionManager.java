package io.github.iromul.microsm.transition;

import io.github.iromul.microsm.IllegalTransitionException;
import io.github.iromul.microsm.Lifecycle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class TransitionManager<S> {

    private final Lifecycle<S> lifecycle;

    private boolean strict = false;
    private Supplier<? extends RuntimeException> throwableSupplier = null;

    private final List<Transition<S>> transitions = new ArrayList<>();

    public TransitionManager(Lifecycle<S> lifecycle) {
        this.lifecycle = lifecycle;
    }

    public TransitionManager<S> strict() {
        strict = true;

        return this;
    }

    public TransitionManager<S> strict(RuntimeException throwable) {
        return strict(() -> throwable);
    }

    public TransitionManager<S> strict(Supplier<? extends RuntimeException> throwableSupplier) {
        strict = true;
        this.throwableSupplier = throwableSupplier;

        return this;
    }

    public TransitionManager<S> transition(Function<S, Boolean> sourceFunction, S target) {
        return transition(new SimpleTransition<>(sourceFunction, target));
    }

    public TransitionManager<S> transition(S source, S target) {
        return transition(new SimpleTransition<>(source, target));
    }

    public TransitionManager<S> transition(S source, Choice<S> choice) {
        return transition(new ChoiceTransition<>(source, choice));
    }

    public TransitionManager<S> transition(Function<S, Boolean> sourceFunction, Choice<S> choice) {
        return transition(new ChoiceTransition<>(sourceFunction, choice));
    }

    public TransitionManager<S> transition(Transition<S> transition) {
        transitions.add(transition);

        return this;
    }

    public boolean transit() {
        return findFirstAllowedTransition()
                .map(Transition::getTarget)
                .map(target -> {
                    lifecycle.transit(target);

                    return true;
                })
                .orElseGet(() -> {
                    if (strict) {
                        if (throwableSupplier != null) {
                            throw throwableSupplier.get();
                        } else {
                            throw new IllegalTransitionException("No transition was found from " +
                                    lifecycle.getState());
                        }
                    } else {
                        return false;
                    }
                });
    }

    private Optional<Transition<S>> findFirstAllowedTransition() {
        S currentState = lifecycle.getState();

        return transitions.stream()
                .filter(transition -> transition.isTransitionAllowed(currentState))
                .findFirst();
    }
}
