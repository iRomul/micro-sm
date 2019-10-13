package io.github.iromul.microsm.transition;

import java.util.function.Function;

public class ChoiceTransition<S> extends BaseTransition<S> {

    private Choice<S> choice;

    public ChoiceTransition(S source, Choice<S> choice) {
        super(source);
        this.choice = choice;
    }

    public ChoiceTransition(Function<S, Boolean> sourceFunction, Choice<S> choice) {
        super(sourceFunction);
        this.choice = choice;
    }

    @Override
    public S getTarget() {
        return choice.findFirstAllowedTarget();
    }

    @Override
    public String toString() {
        String sourceStr = getSourceString();

        return String.format("%s(%s -> %s)", SimpleTransition.class.getName(), sourceStr, "[...]");
    }
}
