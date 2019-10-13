package io.github.iromul.microsm;

public class IllFormedStateMachine extends RuntimeException {

    public IllFormedStateMachine() {
    }

    public IllFormedStateMachine(String message) {
        super(message);
    }

    public IllFormedStateMachine(String message, Throwable cause) {
        super(message, cause);
    }

    public IllFormedStateMachine(Throwable cause) {
        super(cause);
    }
}
