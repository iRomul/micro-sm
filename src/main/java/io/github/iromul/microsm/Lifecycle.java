package io.github.iromul.microsm;

/**
 * Interface indicates that object is lifecycle of stateful object.
 *
 * @param <S> state type
 */
public interface Lifecycle<S> {

    /**
     * Change object state to another.
     *
     * @param newState new state
     */
    void transit(S newState);

    /**
     * Returns state of underlying stateful object.
     *
     * @return state of underlying stateful object
     */
    S getState();
}
