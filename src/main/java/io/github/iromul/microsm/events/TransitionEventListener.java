package io.github.iromul.microsm.events;

@FunctionalInterface
public interface TransitionEventListener<S> {

    void call(TransitionContext<S> context);
}
