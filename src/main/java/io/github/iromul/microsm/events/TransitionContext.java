package io.github.iromul.microsm.events;

public class TransitionContext<S> {

    private final S source;
    private final S target;

    private final Object payload;

    public TransitionContext(S source, S target, Object payload) {
        this.source = source;
        this.target = target;
        this.payload = payload;
    }

    public S getSource() {
        return source;
    }

    public S getTarget() {
        return target;
    }

    public Object getPayload() {
        return payload;
    }
}
