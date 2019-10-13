package io.github.iromul.microsm.transition;

import java.util.function.Function;

public class SimpleTransition<S> extends BaseTransition<S> {

    private S target;

    public SimpleTransition(S source, S target) {
        super(source);
        this.target = target;
    }

    public SimpleTransition(Function<S, Boolean> sourceFunction, S target) {
        super(sourceFunction);
        this.target = target;
    }

    @Override
    public S getTarget() {
        return target;
    }

    @Override
    public String toString() {
        String sourceStr = getSourceString();
        String targetStr = target.toString();

        return String.format("%s(%s -> %s)", SimpleTransition.class.getName(), sourceStr, targetStr);
    }

}
