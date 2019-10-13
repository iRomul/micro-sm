package io.github.iromul.microsm.transition;

import java.util.function.Function;

public abstract class BaseTransition<S> implements Transition<S> {

    protected S source;
    protected Function<S, Boolean> sourceFunction;

    public BaseTransition(S source) {
        this.source = source;
    }

    public BaseTransition(Function<S, Boolean> sourceFunction) {
        this.sourceFunction = sourceFunction;
    }

    @Override
    public abstract S getTarget();

    @Override
    public boolean isTransitionAllowed(S from) {
        if (sourceFunction != null) {
            return sourceFunction.apply(from);
        } else {
            return source == from;
        }
    }

    protected String getSourceString() {
        return sourceFunction != null ? "f(S)" : source.toString();
    }
}
