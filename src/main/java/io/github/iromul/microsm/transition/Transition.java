package io.github.iromul.microsm.transition;

public interface Transition<S> {

    S getTarget();
    boolean isTransitionAllowed(S from);
}
