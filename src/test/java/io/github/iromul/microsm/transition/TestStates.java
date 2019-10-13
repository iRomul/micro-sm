package io.github.iromul.microsm.transition;

public enum TestStates {

    STATE_1,
    STATE_2,
    STATE_3,
    STATE_4;

    public boolean isComplexState() {
        return this == STATE_1 || this == STATE_2;
    }
}
