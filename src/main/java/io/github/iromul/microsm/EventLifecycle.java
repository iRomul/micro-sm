package io.github.iromul.microsm;

import io.github.iromul.microsm.events.TransitionContext;
import io.github.iromul.microsm.events.TransitionEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract lifecycle with events sending. Events are emitting on every lifecycle transition and contains previous
 * state, new state and any payload data. Payload data can be set up by overriding method {@link #getPayload()}.
 *
 * @param <S> state type
 */
public abstract class EventLifecycle<S> implements Lifecycle<S> {

    private final List<TransitionEventListener<S>> transitionListeners = new ArrayList<>();

    public void addTransitionEventListener(TransitionEventListener<S> callable) {
        transitionListeners.add(callable);
    }

    protected abstract void transitInternal(S newState);

    protected Object getPayload() {
        return null;
    }

    @Override
    public final void transit(S newState) {
        S currentState = this.getState();

        transitInternal(newState);

        Object payload = getPayload();
        TransitionContext<S> context = new TransitionContext<>(currentState, newState, payload);

        transitionListeners.forEach(listener -> listener.call(context));
    }
}
