package io.github.iromul.microsm;

/**
 * Interface indicates that object has internal state.
 *
 * @param <S> state type
 */
public interface Stateful<S> {

    S getState();
    void setState(S state);
}
