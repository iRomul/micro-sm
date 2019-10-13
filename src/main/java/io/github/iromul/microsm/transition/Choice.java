package io.github.iromul.microsm.transition;

import io.github.iromul.microsm.IllFormedStateMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class Choice<S> {

    private final List<Pair> guardedTargets = new ArrayList<>();
    private S otherwiseTarget;

    public Choice<S> when(BooleanSupplier guard, S target) {
        guardedTargets.add(new Pair(guard, new StateWrapper(target)));

        return this;
    }

    public Choice<S> when(BooleanSupplier guard, Choice<S> choice) {
        guardedTargets.add(new Pair(guard, new ChoiceWrapper(choice)));

        return this;
    }

    public Choice<S> otherwise(S target) {
        otherwiseTarget = target;

        return this;
    }

    public S findFirstAllowedTarget() {
        if (otherwiseTarget == null) {
            throw new IllFormedStateMachine("Choice has no otherwise state!");
        }

        return guardedTargets.stream()
                .filter(pair -> pair.getGuard().getAsBoolean())
                .findFirst()
                .map(Pair::getWrappedTarget)
                .map(Wrapper::getState)
                .orElse(otherwiseTarget);
    }

    private interface Wrapper<S> {

        S getState();
    }

    private class Pair {

        private final BooleanSupplier guard;
        private final Wrapper<S> wrappedTarget;

        public Pair(BooleanSupplier guard, Wrapper<S> wrappedTarget) {
            this.guard = guard;
            this.wrappedTarget = wrappedTarget;
        }

        public BooleanSupplier getGuard() {
            return guard;
        }

        public Wrapper<S> getWrappedTarget() {
            return wrappedTarget;
        }
    }

    private class StateWrapper implements Wrapper<S> {

        private final S state;

        public StateWrapper(S state) {
            this.state = state;
        }

        @Override
        public S getState() {
            return state;
        }
    }

    private class ChoiceWrapper implements Wrapper<S> {

        private final Choice<S> choice;

        public ChoiceWrapper(Choice<S> choice) {
            this.choice = choice;
        }

        @Override
        public S getState() {
            return choice.findFirstAllowedTarget();
        }
    }
}
